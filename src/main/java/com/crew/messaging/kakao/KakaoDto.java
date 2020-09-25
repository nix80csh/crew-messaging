package com.crew.messaging.kakao;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoDto {

  @Getter
  @Setter
  public static class ScenarioDto {
    public String name;
    @JsonProperty("flow") public List<FlowDto> flowList;
  }

  @Getter
  @Setter
  public static class FlowDto {
    public String from;
    public String channel;
  }

  @Getter
  @Setter
  public static class SendMessageDto {
    public String messageCode;
    public String toMobile;
    public String recipient;
    public String remitter;
    public Integer amount;
    public String deliveredTime;
    public String memo;
  }

}
