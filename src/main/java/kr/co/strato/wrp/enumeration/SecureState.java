package kr.co.strato.wrp.enumeration;

import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import lombok.Getter;
import sil.ict.api.ICTSrCloseType;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum SecureState {
    STANDBY("STANDBY", "대기중"),
    PROCEEDING("PROCEEDING", "진행중"),
    FAIL("FAIL", "실패"),
    COMPLETE("COMPLETE", "완료");

    private String code;
    private String text;

    SecureState(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private static final Map<String, SecureState> map =
            Stream.of(values()).collect(Collectors.toMap(SecureState::name, e -> e));

    public static SecureState findByCode(String code) throws GeneralException {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new GeneralException(ErrorCode.CODE_NOT_EXISTS));
    }
}
