package kr.co.strato.wrp.enumeration;

import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum WfType {
	WF_WORKREQ("WF_WORKREQ", "일반개발요청"),
	WF_DEVPLAN("WF_DEVPLAN", "일반개발계획서"),
	WF_ROLLBACK("WF_ROLLBACK", "원복요청"),
	WF_DISCARD("WF_DISCARD", "폐기요청"),
	WF_MDEPLOY("WF_MDEPLOY", "수동배포"),
	WF_WR_URGENT("WF_WR_URGENT", "긴급반영"),
	WF_DP_URGENT("WF_DP_URGENT", "긴급반영계획서"),
	WF_AUTHREQ("WF_AUTHREQ", "권한신청서");

    private String code;
    private String text;

    WfType(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private static final Map<String, WfType> map =
            Stream.of(values()).collect(Collectors.toMap(WfType::name, e -> e));

    public static WfType findByCode(String code) throws GeneralException {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new GeneralException(ErrorCode.CODE_NOT_EXISTS));
    }
}
