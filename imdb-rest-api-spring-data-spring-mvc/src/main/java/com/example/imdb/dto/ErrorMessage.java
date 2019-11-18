package com.example.imdb.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@ApiModel(description = "All details about the error message. ")
public class ErrorMessage {
	@ApiModelProperty(notes = "Error code", example = "100")
	private int code;
	@ApiModelProperty(notes = "Error message", example = "Cannot find the movie!")
	private String message;

	public ErrorMessage() {
	}

	public ErrorMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
