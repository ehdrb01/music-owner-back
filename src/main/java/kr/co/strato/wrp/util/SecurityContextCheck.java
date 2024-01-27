package kr.co.strato.wrp.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityContextCheck {
	
	/**
	 * 넘겨받은 유저 아이디가 SecurityContext에 등록된 유저아읻가 맞는지 검사
	 * @param userId
	 * @return Boolean
	 */
	public static Boolean AuthCheck(String userId) {
		 //스프링 시큐리티 필터 (JwtAuthFilter) 에서 set 한 SecurityContextHolder 에서 username 을 꺼내서 비교함  
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String username = authentication.getName();
		
		if(userId.equals(username)) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 현재 인증된 사용자(jwt)의 아이디 을 반환한다.
	 * @return String userId
	 */
	public static String getTokenId() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		return authentication.getName();
	}
	
	/**
	 * 현재 인증된 사용자(jwt)의 role 을 반환한다.
	 * @return [ROLE_ADMIN] , [ROLE_ORG], [ROLE_PRJ], [ROLE_SVC], [ROLE_USER]
	 */
	public static String getRole() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		//리스트로 변환후 
		List<GrantedAuthority> listAuth = new ArrayList<GrantedAuthority>();
		listAuth.addAll(authorities);		
		//첫번째 것만 리턴
		String role = listAuth.get(0).getAuthority();
		return role;
	}
}
