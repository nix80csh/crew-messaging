package com.crew.messaging.kakao;

import java.text.NumberFormat;
import java.util.Locale;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.crew.messaging.dao.LogMessage;
import com.crew.messaging.dao.LogMessageRepo;
import com.crew.messaging.dao.TemplateKakao;
import com.crew.messaging.dao.TemplateKakaoRepo;
import com.crew.messaging.exception.LogicErrorList;
import com.crew.messaging.exception.LogicException;
import com.crew.messaging.kakao.KakaoDto.SendMessageDto;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KakaoApiModule {

  @Value("${info-bip.url}") public String url;
  @Value("${info-bip.sender-key}") public String senderKey;
  @Value("${info-bip.scenario-key}") public String scenarioKey;
  @Value("${info-bip.auth}") public String auth;
  @Value("${info-bip.from}") public String from;


  @Autowired TemplateKakaoRepo templateKakaoRepo;
  @Autowired LogMessageRepo logMessageRepo;

  // 카카오 알림톡 보내기
  public Boolean sendMessage(SendMessageDto sendMessageDto) {
    try {
      log.info(sendMessageDto.getToMobile());
      TemplateKakao templateKakao = templateKakaoRepo.findByCode(sendMessageDto.getMessageCode())
          .orElseThrow(() -> new LogicException(LogicErrorList.DoesNotExist_TemplateKakao));

      Double amount = sendMessageDto.getAmount().doubleValue();
      NumberFormat korFormat = NumberFormat.getCurrencyInstance(Locale.KOREA);
      String korAmount = korFormat.format(amount);
      log.info("currency in korea : " + korAmount);

      String content = templateKakao.getContent().replace("#{Sender}", sendMessageDto.getRemitter())
          .replace("#{Recipient}", sendMessageDto.getRecipient())
          .replace("#{Receiving Amount}", korAmount.toString())
          .replace("#{Delivered Time}", sendMessageDto.getDeliveredTime())
          .replace("#{Memo}", sendMessageDto.getMemo());

      String jsonBody = "{\"scenarioKey\":\"" + scenarioKey + "\","
          + "\"destinations\":[{\"to\":{\"phoneNumber\":\"" + sendMessageDto.getToMobile()
          + "\"}}]," + "\"kakaoAlim\":{\"text\":\"" + content + "\"," + "\"templateCode\":\""
          + templateKakao.getCode() + "\",\"validityPeriod\":15,"
          + "\"buttons\":[{\"buttonText\":\"" + templateKakao.getButtonName() + "\","
          + "\"buttonUrl\":\"" + templateKakao.getUrl() + "\"}]}," + "\"sms\":{\"text\":\""
          + content + "\"}}";

      log.info(jsonBody);
      HttpResponse<JsonNode> response =
          Unirest.post(url + "/omni/1/advanced").header("Content-Type", "application/json")
              .header("Authorization", auth).body(jsonBody).asJson();

      Integer httpStatus = (Integer) response.getStatus();

      LogMessage logMessage = new LogMessage();
      logMessage.setRequestBody(jsonBody);
      logMessage.setStatus(httpStatus.toString());
      logMessage.setTemplateKakao(templateKakao);
      logMessage.setType("K");
      logMessageRepo.save(logMessage);

      log.info("httpStatusCode: " + httpStatus);
      if (httpStatus == 200)
        return true;

      return false;
    } catch (JSONException | UnirestException e) {
      return null;
    }
  }

}
