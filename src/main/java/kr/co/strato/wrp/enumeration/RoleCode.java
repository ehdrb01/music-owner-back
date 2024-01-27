package kr.co.strato.wrp.enumeration;

import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum RoleCode {
    SYSTEM_ADMIN, GENERAL_USER;

    private static final Map<String, RoleCode> map =
            Stream.of(values()).collect(Collectors.toMap(RoleCode::name, e -> e));

    public static RoleCode findByCode(String code) throws GeneralException {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new GeneralException(ErrorCode.ROLE_CODE_NOT_EXISTS));
    }

}
