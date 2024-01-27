package kr.co.strato.wrp.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {
	private int code;	
	private String message;
	private Object data;

	public ErrorResponse() {
	}
	
	public ErrorResponse(ErrorCode errCode) {
		this.code = errCode.getCode();
		this.message = errCode.getMessage();
	}
	
	public ErrorResponse(ErrorCode errCode, String message) {
		this.code = errCode.getCode();
		this.message = message;
	}
	
	public ErrorResponse(ErrorCode errCode, Object data) {
		this.code = errCode.getCode();
		this.message = errCode.getMessage();
		this.data = data;
	}
	
	public ErrorResponse(ErrorCode errCode, String message, Object data) {
		this.code = errCode.getCode();
		this.message = message;
		this.data = data;
	}
	
	
	public ErrorResponse(int code) {
		this.code = code;
	}
	
	public ErrorResponse(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public ErrorResponse(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	
	
}
