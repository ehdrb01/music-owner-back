package kr.co.strato.wrp.config.interceptor;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import kr.co.strato.wrp.util.SecurityContextCheck;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HeaderCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)	throws Exception {
		String userId = Optional.ofNullable(request.getHeader("userId"))
								.orElseThrow(() -> new GeneralException(ErrorCode.HEADER_USERID_NOT_FOUND,"HeaderCheckInterceptor Error"));
		System.out.println("HeaderCheckInterceptor userId///");
		System.out.println(userId);
		//헤더의 userId 와 security filter 에서 등록한 아이디(토큰의 아이디)가 다를 경우
		if(!SecurityContextCheck.AuthCheck(userId)) {
			log.debug("[HeaderCheckInterceptor] : 헤더 userId 인증 실패");
			throw new GeneralException(ErrorCode.AUTH_FAIL,"HeaderCheckInterceptor Error");
		}

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,	ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)	throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
