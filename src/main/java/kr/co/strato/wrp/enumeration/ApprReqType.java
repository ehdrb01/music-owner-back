package kr.co.strato.wrp.enumeration;

import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum ApprReqType {
    WR_APPROVE("WR_APPROVE", "업무요청서승인요청"),
    DP_APPROVE("DP_APPROVE", "개발계획서승인요청"),
    //WR_REVIEW_REQ("WR_REVIEW_REQ", "업무요건검토요청"),
    SP_APPROVE("SP_APPROVE", "소스반출승인요청"),
    //DEPLOY_DEV_APPROVE("DEPLOY_DEV_APPROVE", "개발배포승인요청"),
    DEPLOY_PROD_APPROVE("DEPLOY_PROD_APPROVE", "운영배포승인요청"),
    WR_NEGO_APPROVE("WR_NEGO_APPROVE", "업무협의완료요청"),
    WR_COMPLIANCE_APPROVE("WR_COMPLIANCE_APPROVE", "컴플라이언스검수승인요청"),
    WR_QA_APPROVE("WR_QA_APPROVE", "QA검수승인요청"),
    AUTH_REQ_APPROVE("AUTH_REQ_APPROVE", "권한신청승인요청"),
    SP_DISCARD_APPROVE("SP_DISCARD_APPROVE", "소스반출취소승인요청"),
    WR_URGENT_REPORT("WR_URGENT_REPORT", "긴급사후보고승인요청"),
    UAT_APPROVE("UAT_APPROVE","UAT 승인요청"),
    RELATED_UAT_APPROVE("RELATED_UAT_APPROVE","유관부서 UAT 승인요청"),
    DISCARD_INSP_APPROVE("DISCARD_INSP_APPROVE","폐기검토승인요청"),
    WR_ROLLBACK_REPORT("WR_ROLLBACK_REPORT", "원복사후보고승인요청"),
    EXTEND_DUE_DATE_APPROVE("EXTEND_DUE_DATE_APPROVE", "업무마감일변경승인요청");

    private String code;
    private String text;

    ApprReqType(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private static final Map<String, ApprReqType> map =
            Stream.of(values()).collect(Collectors.toMap(ApprReqType::name, e -> e));

    public static ApprReqType findByCode(String code) throws GeneralException {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new GeneralException(ErrorCode.CODE_NOT_EXISTS));
    }
}
