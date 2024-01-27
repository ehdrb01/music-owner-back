package kr.co.strato.wrp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * Exception 발생시
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    protected ErrorResponse handleException(Exception e) {
        log.error("Exception", e);
        return new ErrorResponse(ErrorCode.UNKNOWN_ERROR);
    }
    
    /*ErrorCode 로 Exception 발생시 */
    @ExceptionHandler(value = {GeneralException.class})
    protected ErrorResponse handleGeneralException(GeneralException e) {
    	return new ErrorResponse(e.getCode(), e.getMessage(), e.getDetail());
    }
    
    
    /*Exception 메시지를 직접 넘겨 줄때*/
	@ExceptionHandler(value = {CutomException.class})
    protected ErrorResponse handleGeneralException(CutomException e) {
        return new ErrorResponse(ErrorCode.UNKNOWN_ERROR, e.getMessage());
    }
    
    
    /**
     *  javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     *  HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     *  주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    	log.error("handleMethodArgumentNotValidException", e);
        return new ErrorResponse(ErrorCode.BAD_REQUEST);
    }

    /**
     * @ModelAttribut 으로 binding error 발생시 BindException 발생한다.
     * ref https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-modelattrib-method-args
     */
    @ExceptionHandler(BindException.class)
    protected ErrorResponse handleBindException(BindException e) {
        log.error("handleBindException", e);
        return new ErrorResponse(ErrorCode.BAD_REQUEST);
    }

    /**
     * enum type 일치하지 않아 binding 못할 경우 발생
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("handleMethodArgumentTypeMismatchException", e);
        return new ErrorResponse(ErrorCode.BAD_REQUEST);
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        return new ErrorResponse(ErrorCode.METHOD_NOT_ALLOWED);
    }
}
