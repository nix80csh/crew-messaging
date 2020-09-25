package com.crew.messaging.kakao;

import java.util.Collections;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crew.messaging.kakao.KakaoDto.SendMessageDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class KakaoService {

  @Autowired KakaoApiModule kakaoApiModule;

  public Map<String, Boolean> sendMessage(SendMessageDto sendMessageDto) {
    Boolean isSend = kakaoApiModule.sendMessage(sendMessageDto);
    return Collections.singletonMap("isSend", isSend);
  }
}
