package com.primeton.ygq.demo.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * 异常统一处理
 * 
 * @author 杨功强
 *
 */
@RestControllerAdvice
@Slf4j
public class DemoExceptionHandler {
	private static Logger log = LoggerFactory.getLogger(DemoExceptionHandler.class);
	//捕获的异常
	@ExceptionHandler(value = DemoException.class)
	public ResponseEntity processException(DemoException ex, HttpServletRequest request, HttpServletResponse response) {
		log.error(ex.getMessage(), ex);
		return new ResponseEntity(new Response<>(ex.getErrCode(), ex.getMessage()), ex.getHttpStatus());
	}

	// 其他异常
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity handleServerException(Exception exception) {
		log.error("系统异常：", exception);

		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity(new Response<>(500, exception.getMessage()), httpStatus);
	}
}