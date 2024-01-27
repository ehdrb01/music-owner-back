package kr.co.strato.wrp.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	//boot 2.7에서 WebSecurityConfigurerAdapter 가 deprecated 되었다.  https://codejava.net/frameworks/spring-boot/fix-websecurityconfigureradapter-deprecated
	@Bean
	public JwtAuthFilter jwtFilter() {
		return new JwtAuthFilter();
	}

	@Bean
	public AuthFailEntryPoint failEntryPoint() {
		return new AuthFailEntryPoint();
	}

	//web 자원에 대한 예외 처리를 이젠 SecurityFilterChain 으로 통일
	@Bean
	@Order(0)
	public SecurityFilterChain resources(HttpSecurity http) throws Exception {
	    return http.requestMatchers(matchers -> matchers
	            .antMatchers("/favicon.ico",
		   					"/resources/**",
							"/uploads/**",
		   					"/swagger-ui/**",
		   					"/swagger-ui**",
		   					"/swagger-resources/**",
		   					"/v3/api-docs/**",
//		   					"/ourplay/user/**",
//		   					"/ourplay/**",
		   					"/uploads/editor/**"))
	        .authorizeHttpRequests(authorize -> authorize
	            .anyRequest().permitAll())
	        .requestCache(RequestCacheConfigurer::disable)
	        .securityContext(AbstractHttpConfigurer::disable)
	        .sessionManagement(AbstractHttpConfigurer::disable)
	        .build();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// http 리퀘스트별 접근 권한 설정  (위에서부터 아래로 거른다)
		http.cors().configurationSource(corsConfigurationSource())
				.and()
				.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/ourplay/**").permitAll()
				.antMatchers("/ourplay/user/**").permitAll()
				.antMatchers("/ourplay/user/login**").permitAll()
				.antMatchers("/**").authenticated();

		//	기본 로그인 페이지 사용안함
    	http.formLogin().disable();

    	//session 사용안함
    	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    	// csrf 사용안함
    	http.csrf().disable();
    	// cors 사용 안함
    	// http.cors().disable();

    	//	X-Frame-Options : SAMEORIGIN 허용  (혹은 http.headers().disable() )
    	http.headers().frameOptions().sameOrigin();

    	//인증필터 와 exception핸들링
    	http
    		.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
    		.exceptionHandling().authenticationEntryPoint(failEntryPoint());

    	return http.build();
	}

	// CORS 허용 적용
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		configuration.setAllowCredentials(true);
		configuration.setExposedHeaders(Arrays.asList("Content-Disposition","Transfer-Encoding"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	/*
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers(	"/resources/**",
																	"/images/**");
	}
    */
}
