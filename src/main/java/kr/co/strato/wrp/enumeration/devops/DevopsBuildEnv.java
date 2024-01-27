package kr.co.strato.wrp.enumeration.devops;

import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DevopsBuildEnv {
    DEV(0),
    PROD(1);

    private int code;

    DevopsBuildEnv(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    private static final Map<Integer, DevopsBuildEnv> map =
            Stream.of(values()).collect(Collectors.toMap(DevopsBuildEnv::getCode, e -> e));

    public static DevopsBuildEnv findByCode(int code) throws GeneralException {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new GeneralException(ErrorCode.CODE_NOT_EXISTS));
    }
}
