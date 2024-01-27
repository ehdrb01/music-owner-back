package kr.co.strato.wrp.config;

import java.util.Arrays;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.strato.wrp.config.filter.XssFilter;

@Configuration
public class FilterConfig{

	/*
	 * 정보보안 요건으로 XSS 필터를 추가함.
	 */
	@Bean
	public FilterRegistrationBean filterBean(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new XssFilter());
        registrationBean.setOrder(1);                                                  //필터 여러개 적용 시 순번
        registrationBean.setUrlPatterns(Arrays.asList(INCLUDE_PATHS)); //필터 적용 url 패턴 등록

        return registrationBean;
	}
	
	/*
	 * 필터 대상 URL 지정
	 */
	private static final String[] INCLUDE_PATHS = {
            "/*",
            "/wrp/api/*"
    };
}
