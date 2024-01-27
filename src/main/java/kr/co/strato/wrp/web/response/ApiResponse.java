package kr.co.strato.wrp.web.response;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
    public static final ApiResponse<String> DEFAULT_OK = new ApiResponse<>(ApiResponseCode.OK);
    public static final ApiResponse<String> FAIL = new ApiResponse<>(ApiResponseCode.FAIL);

    private int code;
    private String message;
    private T data;

    private ApiResponse(ApiResponseCode status) {
        this.code=status.getCode();
        this.message=status.getMessage();
    }

    private ApiResponse(ApiResponseCode status, T data) {
    	this.code=status.getCode();
        this.message=status.getMessage();
        this.data=data;
    }


    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ApiResponseCode.OK, data);
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(ApiResponseCode.OK);
    }

}
