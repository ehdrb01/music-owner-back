package kr.co.strato.wrp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigProperties {

    public static String aesKey;

    public static String wrpApiToken;

    @Value("${aes.key}")
    public void setAesKey(String value) {
        aesKey = value;
    }

    public static String getAesKey() {
        return aesKey;
    }

    @Value("${wrp.api.token}")
    public void setWrpApiToken(String value) {
        wrpApiToken = value;
    }

    public static String getWrpApiToken() {
        return wrpApiToken;
    }

}
