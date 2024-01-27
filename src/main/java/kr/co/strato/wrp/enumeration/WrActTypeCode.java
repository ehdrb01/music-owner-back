package kr.co.strato.wrp.enumeration;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import lombok.Getter;

@Getter
public enum WrActTypeCode {
	ACT_WR_WRITE ("ACT_WR_WRITE", "업무 요청"),
    ACT_TASK_WRITE ( "ACT_TASK_WRITE", "개발 계획"),
    ACT_UAT ( "ACT_UAT", "요청자 테스트(UAT)"),
    ACT_CODE_REVIEW("ACT_CODE_REVIEW",  "코드 리뷰"),
    ACT_UNIT_TEST_CASE("ACT_UNIT_TEST_CASE",  "단위 테스트 시나리오"),
    ACT_RELATED_UAT_CASE("ACT_RELATED_UAT_CASE",  "유관부서 UAT 시나리오"),
    ACT_INSP_SECURE("ACT_INSP_SECURE",  "시큐어 코드 검수"),
    ACT_INSP_QA( "ACT_INSP_QA",  "QA"),
    ACT_INSP_COMPLIANCE( "ACT_INSP_COMPLIANCE",  "컴플라이언스 검수"),
    ACT_PULL_SOURCE("ACT_PULL_SOURCE",  "소스 반출 요청"),
    ACT_MERGE_SOURCE("ACT_MERGE_SOURCE",  "소스 반입 요청"),
    ACT_DEPLOY_REQUEST("ACT_DEPLOY_REQUEST",  "운영배포요청"),
    ACT_DEVELOPE( "ACT_DEVELOPE",  "개발진행"),
    ACT_REQ_DISCUSS( "ACT_REQ_DISCUSS",  "요건협의"),
    ACT_REQ_INSPECTION("ACT_REQ_INSPECTION",  "요건검토요청"),
    ACT_REQ_PROGRESS("ACT_REQ_PROGRESS",  "업무요청개발시작"),
    ACT_REQ_COMPLETE("ACT_REQ_COMPLETE",  "업무요청완료"),
    ACT_QC_TEST("ACT_QC_TEST",  "제3자테스트"),
    ACT_DEPLOY_CONFIRM_DEV("ACT_DEPLOY_CONFIRM_DEV",  "개발자 배포확인"),
    ACT_DEV_COMPLETE("ACT_DEV_COMPLETE",  "개발종료"),
    ACT_DEPLOY_CONFIRM_REQ("ACT_DEPLOY_CONFIRM_REQ",  "요청자 배포확인"),
    ACT_UAT_CASE("ACT_UAT_CASE",  "UAT 시나리오"),
    ACT_SP_URGENT("ACT_SP_URGENT",  "긴급소스반출"),
    ACT_DEPLOY_URGENT("ACT_DEPLOY_URGENT",  "긴급운영배포"),
    ACT_REPORT_URGENT("ACT_REPORT_URGENT",  "긴급개발사후보고"),
    ACT_WR_URGENT("ACT_WR_URGENT",  "긴급업무요청"),
    ACT_DP_URGENT( "ACT_DP_URGENT",  "긴급개발계획"),
    ACT_UNIT_TEST("ACT_UNIT_TEST",  "단위 테스트 수행"),
    ACT_RELATED_UAT("ACT_RELATED_UAT",  "유관부서 UAT 수행"),
    ACT_AUTH_REQ("ACT_AUTH_REQ",  "권한신청서"),
    ACT_RB_REQ("ACT_RB_REQ",  "원복신청서"),
    ACT_RB_SRC("ACT_RB_SRC",  "원복지점선택"),
    ACT_RB_DEPLOY("ACT_RB_DEPLOY",  "원복배포"),
    ACT_RB_CONFIRM("ACT_RB_CONFIRM",  "원복확인"),
    ACT_RB_COMPLETE("ACT_RB_COMPLETE",  "원복완료"),
    ACT_DC_REQ("ACT_DC_REQ",  "폐기신청서"),
    ACT_DC_SRC("ACT_DC_SRC",  "폐기소스선택"),
    ACT_DC_INSP("ACT_DC_INSP",  "운영자검토"),
    ACT_DC_REG("ACT_DC_REG",  "폐기실행등록"),
    ACT_DC_COMPLETE("ACT_DC_COMPLETE",  "폐기확인등록"),
    ACT_DEPLOY_CONFIRM_DEV_INSTANT("ACT_DEPLOY_CONFIRM_DEV_INSTANT",  "ICT담당자배포확인-긴급,원복,폐기"),
    ACT_DEPLOY_CONFIRM_REQ_INSTANT("ACT_DEPLOY_CONFIRM_REQ_INSTANT",  "요청자배포확인-긴급,원복,폐기");


    private String code;
    private String text;

    WrActTypeCode(String code, String text) {
        this.code = code;
        this.text = text;
    }
    
    private static final Map<String, WrActTypeCode> map =
            Stream.of(values()).collect(Collectors.toMap(WrActTypeCode::name, e -> e));

    public static WrActTypeCode findByCode(String code) throws GeneralException {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new GeneralException(ErrorCode.CODE_NOT_EXISTS));
    }
}
