package kr.co.strato.wrp.config.security;

import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthFailEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(	HttpServletRequest request, 
									HttpServletResponse response,
									AuthenticationException authException) throws IOException, ServletException {
		
		// 토큰 인증 실패시 처리 부분		
		//response.sendError(response.SC_UNAUTHORIZED, "토큰인증 실패");   //401 리턴
		
		
		/*실패 응답 쓰기*/
		response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);   //http 401 status 코드 넘길때
        
        JSONObject responseJson = new JSONObject();
        responseJson.put("code", 401);
        responseJson.put("message", "[Security Filter] Authentication Fail");
        
	    response.getWriter().print(responseJson);
	}
	

}
