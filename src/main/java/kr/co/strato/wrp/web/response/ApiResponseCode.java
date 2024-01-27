package kr.co.strato.wrp.web.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiResponseCode {
    OK(200,"요청이 성공하였습니다."),
    FAIL(0000,"요청이 실패하였습니다.");

	private final int code;
	private final String message;
    
}
