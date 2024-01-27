package kr.co.strato.wrp.enumeration;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import lombok.Getter;

@Getter
public enum InspectionType {

    INSP_CODE("INSP_CODE", "코드 리뷰"),
    INSP_COMPLIANCE("INSP_COMPLIANCE", "컴플라이언스 검수"),
    INSP_DEPLOY_ICT("INSP_DEPLOY_ICT", "ICT담당자 운영배포 확인"),
    INSP_DEPLOY_WR("INSP_DEPLOY_WR", "업무요청자 운영배포 확인"),
    INSP_QA("INSP_QA", "QA검수"),
    INSP_SECURE("INSP_SECURE", "시큐어코딩검수"),
    INSP_WR("INSP_WR", "요건협의검토"),
	INSP_DISCARD("INSP_DISCARD", "폐기검토"),
	INSP_DISCARD_ICT("INSP_DISCARD_ICT", "소스폐기 ICT담당자 운영배포 확인"),
    INSP_DISCARD_WR("INSP_DISCARD_WR", "폐기 업무요청자 운영배포 확인"),
    INSP_ROLLBACK_QA("INSP_ROLLBACK_QA", "소스원복 QA검수"),
	INSP_ROLLBACK_ICT("INSP_ROLLBACK_ICT", "소스원복 ICT담당자 운영배포 확인"),
    INSP_ROLLBACK_WR("INSP_ROLLBACK_WR", "소스원복 업무요청자 운영배포 확인");

    private String code;
    private String text;

    InspectionType(String code, String text) {
        this.code = code;
        this.text = text;
    }
    
    private static final Map<String, InspectionType> map =
            Stream.of(values()).collect(Collectors.toMap(InspectionType::name, e -> e));

    public static InspectionType findByCode(String code) throws GeneralException {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new GeneralException(ErrorCode.CODE_NOT_EXISTS));
    }
}
