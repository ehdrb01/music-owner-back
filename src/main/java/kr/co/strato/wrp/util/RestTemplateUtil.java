package kr.co.strato.wrp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class RestTemplateUtil {

    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * HttpHeaders 설정 - bearer token 방식
     * @param token
     * @return
     * */
    private HttpHeaders httpHeaderByBearerAuth(String token) {
        // header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        headers.setBearerAuth(token);

        return headers;
    }

    /**
     * HttpHeaders 설정 - Basic auth 방식
     * @param username
     * @param password
     * @return
     * */
    private HttpHeaders httpHeaderByBasicAuth(String username, String password) {

        // header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        headers.setBasicAuth(Base64Utils.base64Encoding(username+":"+password));

        return headers;
    }

    /**
     * HttpHeaders 설정 - bearer token 방식
     * @param token
     * @return
     * */
    private HttpHeaders httpHeaderByDevops(String token) {
        // header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        headers.set("devops-token", AES256Utils.encrypt(token));

        return headers;
    }

    /**
     * Bearer 인증 방식 API 호출
     * @param apiUrl
     * @param token
     * @param httpMethod
     * @param body
     * @param clazz
     * @param <T>
     * @param <U>
     * @return
     */
    public <T, U> ResponseEntity<T> callAPIByBearerAuth(String apiUrl, String token, HttpMethod httpMethod, U body, Class<T> clazz) {

        HttpHeaders headers = this.httpHeaderByBearerAuth(token);

        HttpEntity<U> entity = new HttpEntity(body, headers);

        ResponseEntity<T> response = this.callAPI(apiUrl, entity, httpMethod, clazz);

        return response;
    }

    /**
     * Basic 인증 방식 API 호출
     * @param apiUrl
     * @param username, password
     * @param httpMethod
     * @param body
     * @param clazz
     * @param <T>
     * @param <U>
     * @return
     */
    public <T, U> ResponseEntity<T> callAPIByBasicAuth(String apiUrl, String username, String password, HttpMethod httpMethod, U body, Class<T> clazz) {

        HttpHeaders headers = this.httpHeaderByBasicAuth(username, password);

        HttpEntity<U> entity = new HttpEntity(body, headers);

        ResponseEntity<T> response = this.callAPI(apiUrl, entity, httpMethod, clazz);

        return response;
    }

    /**
     * Devops API 호출
     * @param apiUrl
     * @param token
     * @param httpMethod
     * @param body
     * @param clazz
     * @param <T>
     * @param <U>
     * @return
     */
    public <T, U> ResponseEntity<T> callAPIByDevops(String apiUrl, String token, HttpMethod httpMethod, U body, Class<T> clazz) {

        HttpHeaders headers = this.httpHeaderByDevops(token);

        HttpEntity<U> entity = new HttpEntity(body, headers);

        ResponseEntity<T> response = this.callAPI(apiUrl, entity, httpMethod, clazz);

        return response;
    }

    /**
     * 메신저 API 호출
     * @param apiUrl
     * @param String body
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> ResponseEntity<T> callAPIByMessenger(String apiUrl, String body, Class<T> clazz) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "x-www-form-urlencoded", Charset.forName("EUC-KR")));
        
    	HttpEntity<String> entity = new HttpEntity<String>(body,headers);	
    	
        ResponseEntity<T> response = this.callAPI(apiUrl, entity, HttpMethod.POST, clazz);
        return response;
    }
    
    /**
     * API 호출 (공통)
     * @param apiUrl
     * @param httpMethod
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> ResponseEntity<T> callAPI(String apiUrl, HttpEntity httpEntity, HttpMethod httpMethod, Class<T> clazz) {

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(apiUrl).build();

        ResponseEntity<T> response = null;

        try {
            response = restTemplate.exchange(uriComponents.toString(), httpMethod, httpEntity, clazz);
        } catch (HttpStatusCodeException e) {

            log.error("[callAPI] HttpStatusCodeException ", e);

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = new HashMap<>();

            try {
                map = objectMapper.readValue(e.getResponseBodyAsString(), Map.class);
            } catch (JsonProcessingException ex) {
                log.error(ex.getMessage(), ex);
            }

            log.error("## Response All : {} ", e.getMessage());
            log.error("## Response Code :  {} ", e.getStatusCode().value());
            log.error("## Response StatusMessage : {} ", e.getStatusText());
            log.error("## Response error : {} ", map.get("error"));
            log.error("## Response message : {} ", map.get("message"));

            if(e.getRawStatusCode() == ErrorCode.BAD_REQUEST.getCode()) {
                throw new GeneralException(ErrorCode.BAD_REQUEST, (String) map.get("message"));
            }
            else if(e.getRawStatusCode() == ErrorCode.NOT_FOUND.getCode()) {
                throw new GeneralException(ErrorCode.NOT_FOUND, map.get("message"));
            }
            else if(e.getRawStatusCode() == ErrorCode.UNAUTHORIZED.getCode()) {
                throw new GeneralException(ErrorCode.UNAUTHORIZED, map.get("message"));
            }
            else if(e.getRawStatusCode() == ErrorCode.INTERNAL_SERVER_ERROR.getCode()) {
                throw new GeneralException(ErrorCode.INTERNAL_SERVER_ERROR, map.get("message"));
            }
            else {
                throw new GeneralException(ErrorCode.UNKNOWN_ERROR, map.get("message"));
            }

        } catch (Exception e) {
            log.error("[callAPI] Exception ", e);
            throw new GeneralException(ErrorCode.UNKNOWN_ERROR, e.getMessage());
        }

        log.debug("[callAPI] Success");

        return response;
    }
}
