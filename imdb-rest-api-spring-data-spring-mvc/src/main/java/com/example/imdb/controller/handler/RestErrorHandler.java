package com.example.imdb.controller.handler;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.imdb.dto.ErrorMessage;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@RestControllerAdvice
public class RestErrorHandler {
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleIllegalArgumentException(IllegalArgumentException e) {
		return new ErrorMessage(200, e.getMessage());
	}

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleOtherExceptions(Throwable t) {
		return new ErrorMessage(300, t.getMessage());
	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<ErrorMessage> handleValidationException(ConstraintViolationException e) {
		return e.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
				.map(msg -> new ErrorMessage(410, msg)).collect(Collectors.toList());
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.map(msg -> new ErrorMessage(400, msg)).collect(Collectors.toList());
	}

}
