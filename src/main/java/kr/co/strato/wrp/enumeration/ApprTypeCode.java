package kr.co.strato.wrp.enumeration;

import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum ApprTypeCode {
	APPROVE_SERIAL("APPROVE_SERIAL", "순차결재"),
	APPROVE_PARRALLEL("APPROVE_PARRALLEL", "병렬결재"),
	CONFIRM("CONFIRM", "확인")
    ;

    private String code;
    private String text;

    ApprTypeCode(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private static final Map<String, ApprTypeCode> map =
            Stream.of(values()).collect(Collectors.toMap(ApprTypeCode::name, e -> e));

    public static ApprTypeCode findByCode(String code) throws GeneralException {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new GeneralException(ErrorCode.CODE_NOT_EXISTS));
    }
}
