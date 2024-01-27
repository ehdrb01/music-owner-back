package kr.co.strato.wrp.enumeration;

import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum SrType {
    SR_SILHOUETTE("SR_SILHOUETTE", "실루엣소스반출", "실루엣"),
    SR_GIT("SR_GIT", "깃소스반출", "깃");

    private String code;
    private String text;
    private String desc;

    SrType(String code, String text, String desc) {
        this.code = code;
        this.text = text;
        this.desc = desc;
    }

    private static final Map<String, SrType> map =
            Stream.of(values()).collect(Collectors.toMap(SrType::name, e -> e));

    public static SrType findByCode(String code) throws GeneralException {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new GeneralException(ErrorCode.CODE_NOT_EXISTS));
    }
}
