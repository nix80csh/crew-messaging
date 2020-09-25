package com.crew.messaging.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogicErrorList {

  Faild_GetScenarioKey(999, "Faild_GetScenarioKey"),

  DoesNotExist_TemplateKakao(100, "DoesNotExist_TemplateKakao");

  private final int errorCode;
  private final String errorMsg;

}
