package kr.co.strato.wrp.enumeration;

public enum StatisticsSearchType {
	DBRN_NM("DBRN_NM", "부서명"),
	USER_ID("USER_ID", "사용자 아이디"),
	NAME("NAME", "사용자명"),
	PRJ_NAME("PRJ_NAME", "프로젝트명"),
	SVC_NAME("SVC_NAME", "서비스명"),
	WR_CODE("WR_CODE", "문서번호"),
	WR_APPROVAL_REQ_USER_ID("WR_APPROVAL_REQ_USER_ID", "요청자 아이디"),
	WR_APPROVAL_REQ_USER_NAME("WR_APPROVAL_REQ_USER_NAME", "요청자명"),
	WR_APPROVAL_USER_ID("WR_APPROVAL_USER_ID", "결재자 아이디"),
	WR_APPROVAL_USER_NAME("WR_APPROVAL_USER_NAME", "결재자명");

    private String code;
    private String text;

    StatisticsSearchType(String code, String text) {
        this.code = code;
        this.text = text;
    }
}
