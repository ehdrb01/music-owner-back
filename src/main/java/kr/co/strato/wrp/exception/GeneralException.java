package kr.co.strato.wrp.exception;

import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {
	private int code;
    private String message;
    private Object detail;

    public GeneralException() {
    	this.code=ErrorCode.UNKNOWN_ERROR.getCode();
    	this.message=ErrorCode.UNKNOWN_ERROR.getMessage();
    }

    public GeneralException(ErrorCode errCode) {
    	this.code = errCode.getCode();
        this.message = errCode.getMessage();
    }

    public GeneralException(ErrorCode errCode, Object detail) {
        this.code = errCode.getCode();
        this.message = errCode.getMessage();
        this.detail = detail;
    }
    
}
