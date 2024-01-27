package kr.co.strato.wrp.enumeration;

import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum WfRoleCode {
	ROLE_ADMIN("ROLE_ADMIN", "관리자 권한"),
	ROLE_WR_DISCUSS("ROLE_WR_DISCUSS", "요건협의 권한"),
	ROLE_INSP_REQ("ROLE_INSP_REQ", "요건검토 권한"),
	ROLE_CHANGE_REVIEW("ROLE_CHANGE_REVIEW", "변경관리 검토 권한"),
	ROLE_INSP_COMPLIANCE("ROLE_INSP_COMPLIANCE", "컴플라이언스 검수 권한"),
	ROLE_INSP_QA("ROLE_INSP_QA", "QA 수행 권한"),
	ROLE_DEPLOY("ROLE_DEPLOY", "운영배포수행권한"),
	ROLE_TEST_QC("ROLE_TEST_QC", "제3자테스트권한"),
	ROLE_SRC_AUTH("ROLE_SRC_AUTH", "형상관리 승인 권한"),
	ROLE_MAINTAINER("ROLE_MAINTAINER","운영자그룹권한");

    private String code;
    private String text;

    WfRoleCode(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private static final Map<String, WfRoleCode> map =
            Stream.of(values()).collect(Collectors.toMap(WfRoleCode::name, e -> e));

    public static WfRoleCode findByCode(String code) throws GeneralException {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new GeneralException(ErrorCode.CODE_NOT_EXISTS));
    }
}
