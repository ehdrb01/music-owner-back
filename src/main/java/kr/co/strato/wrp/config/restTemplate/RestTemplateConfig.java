package kr.co.strato.wrp.config.restTemplate;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
public class RestTemplateConfig {

    /**
     * restTemplate 생성 / SSL 인증서 무시
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     */
    @Bean
    public RestTemplate restTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

        // for SKIP SSL CERTIFICATE VERIFICATION IN SPRING REST TEMPLATE
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                // for 403 redirect
                .setRedirectStrategy(new LaxRedirectStrategy())
                .disableCookieManagement()
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        // for 403 redirect
        HttpComponentsClientHttpRequestFactory factory  = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClient);
        restTemplate.setRequestFactory(factory);

        // api가 응답 메시지가 xml 포멧(application/xml)인데,
        // 실제 HTTP Response 해더의 Media Type은 "text/html"로 오기 때문에
        // Media Type 설정이 필요하다.
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setSupportedMediaTypes(
                Arrays.asList(new MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8"))));

        restTemplate.getMessageConverters().add(stringConverter);

        // for debugging
        List<ClientHttpRequestInterceptor> list = new ArrayList<ClientHttpRequestInterceptor>();
        list.add(new LoggingRequestInterceptor());

        restTemplate.setInterceptors(list);

        return restTemplate;
    }
}

