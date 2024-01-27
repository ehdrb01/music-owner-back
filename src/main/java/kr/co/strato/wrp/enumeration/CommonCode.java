package kr.co.strato.wrp.enumeration;

import lombok.Getter;

@Getter
public enum CommonCode {
    STATE_TYPE("STATE_TYPE", "스테이트 타입"),
    WR_ACTION_TYPE("WR_ACTION_TYPE", "표준업무요청 액션 구분"),
    APPROVE_TYPE("APPROVE_TYPE", "단계확인구분"),
    REPORT_TYPE("REPORT_TYPE", "보고서구분"),
    DEPLOY_TYPE("DEPLOY_TYPE", "이행구분"),
    APPROVE_RESULT("APPROVE_RESULT", "결재결과"),
    TEST_TYPE("TEST_TYPE", "테스트종류"),
    INSP_TYPE("INSP_TYPE", "검증종류"),
    DEV_TYPE("DEV_TYPE", "업무유형"),
    CHANNEL_TYPE("CHANNEL_TYPE", "채널유형"),
    ACTION_TYPE("ACTION_TYPE", "업무요청 액션 구분"),
    APPROVER_TYPE("APPROVER_TYPE", "결재자 구분"),
    APPR_REQ_TYPE("APPR_REQ_TYPE", "업무요청 승인 구분"),
    REQ_PRIORITY("REQ_PRIORITY", "업무요청 우선순위"),
    REQ_TYPE("REQ_TYPE", "업무요청 타입"),
    SR_ACTION("SR_ACTION", "실루엣 액션 구분"),
    SR_TYPE("SR_TYPE", "실루엣 요청 타입"),
    WF_TYPE("WF_TYPE", "업무요청 플로우"),
    FILE_ATTACH_TYPE("FILE_ATTACH_TYPE", "파일 업로드 구분"),
    COMPLIANCE_SRC("COMPLIANCE_SRC", "컴플라이언스 검수 대상"),
	URGENT_TYPE("URGENT_TYPE", "긴급사유");

    private String code;
    private String text;

    CommonCode(String code, String text) {
        this.code = code;
        this.text = text;
    }
}
