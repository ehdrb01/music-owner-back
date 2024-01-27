package kr.co.strato.wrp.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import kr.co.strato.wrp.model.OpUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JwtUtils {
	private static final String secretString = "Shinhan_DevopsProject_Secret_Text";   //  256 bits 이상
	private static final Key key = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));

	/*token 발생*/
	public static String createToken(OpUser opUser){
		String jws = Jwts.builder()
			    .setIssuer("Portal-Core")       //발급자 클레임
			    .setSubject("API TOKEN")    //주제 클레임
			    .setAudience("DevOps-ui")  //대상 클레임
			    .setExpiration(new Date(System.currentTimeMillis()+(24 * 60 * 60 * 1000))) //만료일은 일단 1일
			    .setIssuedAt(new Date())   // 발급 시점
			    .setHeaderParam("typ","JWT")   //헤더
			    .signWith(key)                 //서명키
			    .claim("userId", opUser.getUserId()) //맞춤 클레임 설정
			    .compact();
		return jws;
	}

	/*token 검증*/
	public static boolean isUsable(String jws) {
		try {
			    Jwts.parserBuilder()
			    				.setSigningKey(key)
			    				.build()
			    				.parseClaimsJws(jws);
			    //OK, trust this jws
			    return true;
			} catch (JwtException e) {
				//don't trust this jws
			    return false;
			}
	}

	/*클레임 꺼내기*/
	public static Map<String,Object> getClaims(String jws) {
		Map<String, Object> map = null;

		//파싱한다.
		Jws<Claims> jwtClaims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(jws);

		//파싱한거에서 꺼낸다.
		Claims claims = jwtClaims.getBody();

		//클레임정보를 Map으로 담아서 리턴
		map = claims;

		log.debug(map.toString());
		return map;
	}

}
