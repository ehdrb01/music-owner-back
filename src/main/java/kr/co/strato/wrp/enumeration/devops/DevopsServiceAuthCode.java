package kr.co.strato.wrp.enumeration.devops;

import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import org.gitlab4j.api.models.AccessLevel;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DevopsServiceAuthCode {
    GUEST(0, "Guest", AccessLevel.GUEST),
    DEVELOPER(1, "Developer", AccessLevel.DEVELOPER),
    MAINTAINER(2, "Maintainer", AccessLevel.MAINTAINER);

    private Integer code;
    private String text;
    private AccessLevel accessLevel;

    DevopsServiceAuthCode(Integer code, String text, AccessLevel accessLevel) {
        this.code = code;
        this.text = text;
        this.accessLevel= accessLevel;
    }

    public Integer getCode() {
        return code;
    }
    public String getText() {
        return text;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    private static final Map<Integer, DevopsServiceAuthCode> map =
            Stream.of(values()).collect(Collectors.toMap(DevopsServiceAuthCode::getCode, e -> e));

    public static DevopsServiceAuthCode findByCode(int code) throws GeneralException {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new GeneralException(ErrorCode.CODE_NOT_EXISTS));
    }
}
