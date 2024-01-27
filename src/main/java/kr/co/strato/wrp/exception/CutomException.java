package kr.co.strato.wrp.exception;

import lombok.Getter;

@Getter
public class CutomException extends RuntimeException {
	private String message;

    public CutomException(String message) {
        this.message = message;
    }
}
