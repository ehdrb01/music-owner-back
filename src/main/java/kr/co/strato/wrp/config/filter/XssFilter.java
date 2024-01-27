package kr.co.strato.wrp.config.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XssFilter implements Filter{
	
	/*
	 * 전/후처리
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;
	
	    String requestURI = req.getRequestURI();
	
	    log.debug("---Request(" + requestURI + ") 필터---");
	    
	    chain.doFilter(new RequestXssWrapper((HttpServletRequest) request), response);
	    
	    log.debug("---Response(" + requestURI + ") 필터---");
	}

}
