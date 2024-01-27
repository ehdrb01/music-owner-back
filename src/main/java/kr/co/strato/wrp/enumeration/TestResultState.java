package kr.co.strato.wrp.enumeration;

import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum TestResultState {
	PASS("PASS", "정상"),
	FAIL("FAIL", "오류");

    private String code;
    private String text;

    TestResultState(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private static final Map<String, TestResultState> map =
            Stream.of(values()).collect(Collectors.toMap(TestResultState::name, e -> e));

    public static TestResultState findByCode(String code) throws GeneralException {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new GeneralException(ErrorCode.CODE_NOT_EXISTS));
    }
}
