package com.crew.messaging.exception;

import lombok.Data;

@Data
public class InternalServerExceptionVo {
	private int errorCode;
	private String errorMsg;
}