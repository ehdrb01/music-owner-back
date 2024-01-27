package kr.co.strato.wrp.enumeration;

import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum BranchType {
    DEV("dev"),
    RELEASE("release"),
    MAIN("main");

    private String code;

    BranchType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    private static final Map<String, BranchType> map =
            Stream.of(values()).collect(Collectors.toMap(BranchType::getCode, e -> e));

    public static BranchType findByCode(String code) throws GeneralException {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new GeneralException(ErrorCode.UNKNOWN_ERROR));
    }
}
