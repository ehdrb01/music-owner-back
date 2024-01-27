package kr.co.strato.wrp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.strato.wrp.config.interceptor.HeaderCheckInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	@Autowired
	HeaderCheckInterceptor headercheckinterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(headercheckinterceptor)					
								//.addPathPatterns("/**")								
								//.addPathPatterns("/wrp/api/**")
				.excludePathPatterns("/wrp/api/uploads/*")
				.excludePathPatterns("/**/login")
				.excludePathPatterns("/wrp/api/silhouette/deploy/result")
				.excludePathPatterns("/wrp/api/test/**")
				.addPathPatterns("/wrp/api/**");

		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
	/*
	 * 외부 정적 자원 추가
	 * http://localhost:8090/uploads/1.jpg  -> /uploads/editor/1.jpg 매핑
	 */
	  @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/uploads/editor/**")
	                .addResourceLocations("file:///uploads/editor/");
	    }
}
