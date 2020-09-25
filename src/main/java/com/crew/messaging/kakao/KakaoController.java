package com.crew.messaging.kakao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crew.messaging.kakao.KakaoDto.SendMessageDto;

@RestController
@RequestMapping("/kakao")
public class KakaoController {

  @Autowired KakaoService kakaoService;

  @PostMapping("/sendMessage")
  public Map<String, Boolean> sendMessage(@RequestBody SendMessageDto sendMessageDto) {
    return kakaoService.sendMessage(sendMessageDto);
  }
}
