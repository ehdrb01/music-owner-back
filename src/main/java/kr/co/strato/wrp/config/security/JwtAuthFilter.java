package kr.co.strato.wrp.config.security;

import kr.co.strato.wrp.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter{


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		if(!request.getRequestURI().endsWith("/api/login")) {
				String authToken = request.getHeader("Authorization");
				if(authToken != null) authToken=authToken.trim();
				log.debug("Authorization token : "+ authToken);

				if(StringUtils.isNotBlank(authToken) && JwtUtils.isUsable(authToken) ) {
					Map<String,Object> claimMap =JwtUtils.getClaims(authToken);
					String userId = StringUtils.defaultString(claimMap.get("userId").toString(), "");
//					String roleCode = StringUtils.defaultString(claimMap.getOrDefault("roleCode","GENERAL_USER").toString(), "");
					log.debug("인증시작 : {} ", claimMap.toString());

					List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

//					switch (roleCode) {
//						case "SYSTEM_ADMIN":	roles.add(new SimpleGrantedAuthority("SYSTEM_ADMIN"));
//							break;
//						case "GENERAL_USER":	roles.add(new SimpleGrantedAuthority("GENERAL_USER"));
//							break;
//						default:roles.add(new SimpleGrantedAuthority("ROLE_USR"));
//							break;
//					}

					/*
					 * SecurityContextHorder 에 Authentication 를 set 함으로써 인가한다
					 * 다른 요청은 spring security 에 의해서 차단된다. AuthFailEntryPoint 로 던질것임
					 */
					UsernamePasswordAuthenticationToken securityToken = new UsernamePasswordAuthenticationToken(userId, null, roles);
					SecurityContext securityContext = SecurityContextHolder.getContext();
					securityContext.setAuthentication(securityToken);
				}else {
					log.info("Authorization token 인증 실패");
				}
		}

		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		log.debug("JwtAuthFilter destroy()");
		super.destroy();
	}
}
