package kr.co.strato.wrp.config.restTemplate;

import io.micrometer.core.instrument.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;


@Slf4j
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    // RestTemplate 요청 '전', '후'에 로깅을 찍기 위한 인터셉터
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        // request log
        traceRequest(request, body);

        // execute
        ClientHttpResponse response = execution.execute(request, body);

        // response log
        response = traceResponse(request, body, response);

        return response;
    }


    private void traceRequest(HttpRequest request, byte[] body) throws IOException {

        log.info("===========================[REQUEST] begin================================================");
        log.debug("URI           : {}", request.getURI());
        log.debug("Method        : {}", request.getMethod());
        log.debug("Headers       : {}", request.getHeaders());
        log.debug("Request Body  : {}", new String(body, "UTF-8"));
        log.info("==========================[REQUEST] end===================================================");

    }

    private ClientHttpResponse traceResponse(final HttpRequest request, final byte[] body, final ClientHttpResponse response) throws IOException {

        final ClientHttpResponse responseCopy = new BufferingClientHttpResponseWrapper(response);

        log.info("============================[RESPONSE] begin==============================================");
        log.debug("Method        : ", request.getMethod());
        log.debug("URI           : ", request.getURI());
        log.debug("Headers       : {}", request.getHeaders());
        log.debug("Request Body  : " + new String(body));
        log.debug("Response status : " +  responseCopy.getStatusCode());
        log.debug("Response body : " +  IOUtils.toString(responseCopy.getBody()));
        log.info("=======================[RESPONSE] end=====================================================");

        return responseCopy;
    }

}
