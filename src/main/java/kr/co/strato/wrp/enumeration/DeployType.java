package kr.co.strato.wrp.enumeration;

import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum DeployType {
	DEPLOY_INSTANT("DEPLOY_INSTANT", "즉시배포"), //긴급배포프로세스
    DEPLOY_RESERVE("DEPLOY_RESERVE", "예약배포"), //8시부터 5시를 제외하고 예약
    DEPLOY_URGENT("DEPLOY_URGENT", "긴급배포"); //이행담당자배포

    private String code;
    private String text;

    DeployType(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private static final Map<String, DeployType> map =
            Stream.of(values()).collect(Collectors.toMap(DeployType::name, e -> e));

    public static DeployType findByCode(String code) throws GeneralException {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new GeneralException(ErrorCode.CODE_NOT_EXISTS));
    }
}
