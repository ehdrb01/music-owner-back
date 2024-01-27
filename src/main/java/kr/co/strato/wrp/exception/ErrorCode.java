package kr.co.strato.wrp.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
	
	/* 인증관련 2000번대 */
	LOGIN_FAIL(2001, "ID or Password Invalid"),
	PW_INVALID(2002, "Password Invalid"),
	AUTH_FAIL(2003, "Authorize Error"),             //유저 아이디 변조수 토큰값과 불일치
	USER_NOT_FOUND(2004, "User Not Found"),
	USER_EXISTS(2005,"이미 생성된 유저 입니다."),
	ROLE_CODE_NOT_EXISTS(2006,"존재하지 않는 role code 입니다."),
	HEADER_USERID_NOT_FOUND(2007,"헤더값이 존재 하지 않습니다."),	
	/* 골드넷 패스워드 연동 에러*/
	GOLDNET_RETURN_1(2101,"비밀번호 오류 - 골드넷"),
	GOLDNET_RETURN_2(2102,"비밀번호 입력 5회 오류- 골드넷"),
	GOLDNET_RETURN_3(2103,"비밀번호 변경 필요 - 골드넷"),
	GOLDNET_RETURN_4(2104,"ID가 존재하지 않음 - 골드넷"),
	GOLDNET_RETURN_99(21099,"골드넷 로그인 연동 실패"),
	 
	/* devops 서비스 관련 3000번대 */
	/*devops OSS 관련 4000번대*/
	/*devops Build 관련 5000번대*/

	/* common */
	BAD_REQUEST(400, "BAD REQUEST"),
	UNAUTHORIZED(401, "UNAUTHORIZED"),
	FORBIDDEN(403, "FORBIDDEN"),
	NOT_FOUND(404, "NOT FOUND"),
	METHOD_NOT_ALLOWED(405,"METHOD NOT ALLOWED"),
	INTERNAL_SERVER_ERROR(500,"INTERNAL SERVER ERROR"),
	
	READ_DENIED(1001, "조회 권한이 없습니다."),
	CREATE_DENIED(1002, "생성 권한이 없습니다."),
	UPDATE_DENIED(1003, "수정 권한이 없습니다."),
	DELETE_DENIED(1004, "삭제 권한이 없습니다."),
	CODE_NOT_EXISTS(1005, "존재하지 않는 code 입니다."),
	ATTACH_FILE_NOT_FOUND(1006, "첨부파일이 존재하지 않습니다."),

	/*외부 API 관련 6000번대*/
	DEVOPS_API_ERROR(6001, "devops api 요청중 에러가 발생했습니다."),
	GITLAB_API_ERROR(6002, "git lab API 요청 중 에러가 발생했습니다."),
	SILHOUETTE_API_ERROR(6003, "silhouette API 요청 중 에러가 발생했습니다."),
	MERGE_REQUEST_CONFLICTS_ERROR(6004, "병합 충돌이 발생했습니다. 처리 후 재시도 하시기 바랍니다."),
	MERGE_REQUEST_UNKNOWN_ERROR(6005, "생성된 Merge Request에 오류가 있습니다. 처리 후 재시도 하시기 바랍니다."),
	SILHOUETTE_SR_CLOSED(6006, "이미 종료된 silhouette SR 입니다."),
	SILHOUETTE_FILE_NOT_EXIST(6007, "존재하지 않는 파일이 포함되어 있습니다."),
	DEVOPS_BUILD_NOT_EXIST(6008, "존재하지 않는 Build 정보 입니다."),
	MERGE_REQUEST_MERGE_FAIL(6009, "병합 실패하였습니다. 확인 후 재시도 하시기 바랍니다."),
	SILHOUETTE_LOG_NOT_EXIST(6010, "로그 파일이 존재하지 않습니다."),

	/* WF 관련 Error Code */
	WF_GENERAL_ERROR(7000, "WF General Exception."),
	WF_GROUP_EXIST(7001, "사용자 그룹이 이미 존재 합니다."),
	WF_GROUP_NOT_EXIST(7002, "사용자 그룹이 존재하지 않습니다."),
	WF_GROUP_MAPPING_EXIST(7003, "사용자 그룹 매핑정보가 존재 합니다."),
	WF_GROUP_MAPPING_NOT_EXIST(7004, "사용자 그룹 매핑 정보가 존재하지 않습니다."),
	WF_APPROVE_ITEM_NOT_EXIST(7011, "결재 아이템 정보가 존재하지 않습니다."),
	WF_APPROVE_ITEM_EXIST(7011, "결재 아이템 정보가 이미 존재하고 있습니다."),
	WF_MASTER_EXIST(7012, "워크플로우 마스터 정보가 이미 존재하고 있습니다."),
	WF_MASTER_NOT_EXIST(7012, "워크플로우 마스터 정보가 존재하지 않습니다."),
	WF_USER_TYPE_EXIST(7013, "사용자 타입이 존재하고 있습니다."),
	WF_USER_TYPE_NOT_EXIST(7014, "사용자 타입이 존재하지 않습니다."),


	/* WR 관련 Error Code */
	WR_GENERAL_ERROR(8000, "WR General Exception."),
	WR_MASTER_EXIST(8001, "업무요청 정보가 이미 존재하고 있습니다."),
	WR_MASTER_NOT_EXIST(8002, "업무요청 정보가 존재하지 않습니다."),
	WR_TASK_EXIST(8003, "개발계획 정보가 존재하고 있습니다."),
	WR_TASK_NOT_EXIST(8004, "개발계획 정보가 존재하지 않습니다."),
	WR_RELATED_USER_EXIST(8005, "관계자로 이미 등록되어 있습니다."),
	WR_RELATED_USER_NOT_EXIST(8006, "관계자로 등록 되어있지 않습니다."),
	WR_ATTACH_FILE_EXIST(8007, "해당 첨부파일이 존재하고 않습니다."),
	WR_ATTACH_FILE_NOT_EXIST(8008, "해당 첨부파일이 존재하지 않습니다."),
	WR_REPLY_EXIST(8009, "댓글이 존재하고 있습니다."),
	WR_REPLY_NOT_EXIST(8010, "댓글이 존재하지 않습니다."),
	WR_NEED_ICT_DEVELOPER(8011, "ICT 담당자 지정이 필요 합니다."),
	WR_NEGO_EXIST(8012, "업무협의 내용이 이미 존재하고 있습니다."),
	WR_NEGO_NOT_EXIST(8013, "업무협의 내용이 존재하지 않습니다."),
	WR_NEGOREVIEW_NOT_EXIST(8014, "업무검토 내용이 존재하지 않습니다."),
	WR_SR_NOT_EXIST(8015, "소스반출 정보가 존재하지 않습니다."),
	WR_APPROVAL_NOT_EXIST(8016, "결재 정보가 존재하지 않습니다."),
	WF_ACTION_TYPE_NOT_EXIST(8017, "액션 타입이 존재하지 않습니다."),
	WF_INSP_NOT_EXIST(8018, "점검내용이 존재하지 않습니다."),
	WR_INSP_CODE_EXIST(8019, "코드리뷰 정보가 이미 존재합니다."),
	WR_INSP_CODE_NOT_EXIST(8020, "코드리뷰 정보가 존재하지 않습니다."),
	WR_SR_DISCARD_NOT_EXIST(8021, "소스반출 폐기 정보가 존재하지 않습니다."),
	WR_ALERT_NOT_EXIST(8022, "알림이 존재하지 않습니다."),
	WR_INSP_QA_NOT_EXIST(8023, "QA 정보가 존재하지 않습니다."),
	WR_APPROVAL_EXIST(8024, "이미 승인 요청된 건입니다."),
	WR_SR_GIT_EXIST(8025, "깃 소스반출 정보가 이미 존재하고 있습니다."),
	WR_SR_GIT_NOT_EXIST(8026, "깃 소스반출 정보가 존재하지 않습니다."),
	WR_SR_GIT_BUILD_NOT_ALLOWED(8027, "할당받지 않은 소스의 빌드는 실행할 수 없습니다."),
	WR_DEPLOY_NOT_ALLOWED(8028, "배포를 허용할 수 없습니다."),
	WR_NOT_APPROVED(8029, "결재 승인된 건이 아닙니다."),
	WR_APPROVED(8030, "이미 결재 처리된 건입니다."),
	WR_DR_NOT_EXIST(8031, "운영배포 요청정보가 존재하지 않습니다."),
	WR_DR_EXIST(8032, "운영배포 요청정보가 이미 존재하고 있습니다."),
	WR_DR_GIT_NOT_EXIST(8033, "깃 운영배포 정보가 존재하지 않습니다."),
	WR_DR_DEV_NOT_EXIST(8034, "개발배포 요청정보가 존재하지 않습니다."),
	WR_DR_DEV_EXECUTED(8035, "개발배포가 이미 실행된 건입니다."),
	WR_FAVORITE_NOT_EXIST(8036, "즐겨찾기가 존재하지 않습니다."),
	WR_SR_EXIST(8037, "소스반출 정보가 이미 존재하고 있습니다."),
	WR_URGENT_REPORT_NOT_EXIST(8038, "사후보고 정보가 존재하지 않습니다."),
	WR_DR_DEPLOYED(8039, "이미 배포된 건입니다."),
	WR_TASK_COMPLETE_EXIST(8040, "진행중인 개발계획이 존재합니다."),
	WR_ROLLBACK_GIT_NOT_EXIST(8041, "원복 깃 소스 정보가 존재하지 않습니다."),
	WR_DISCARD_RESULT_NOT_EXIST(8042, "폐기 결과 등록 정보가 존재하지 않습니다."),
	
	//테스트(UNIT_TEST, UAT, RELATED_UAT, QC)관련
	WR_IMPORT_TEST_CASE_NOT_EXIST(8022, "가져 올 테스트 시나리오 목록이 존재하지 않습니다."),

	//결재관련
	WR_APPROVE_ITEM_NOT_EXIST(8023, "결재 아이템 정보가 존재하지 않습니다."),
	WR_APPROVAL_DTL_NOT_EXIST(8024, "결재자 목록이 존재하지 않습니다."),
	WR_APPROVAL_ALREADY_REJECTED(8025, "이미 반려된 결재 요청입니다."),
	WR_APPROVAL_ORDER_NOT_ARRIVED(8026, "결재 순서가 아닙니다."),
	WR_APPROVE_LIST_NOT_EXIST(8027, "결재 목록이 존재하지 않습니다."),
	WR_APPROVE_ITEM_WR_MAPPED(8028, "결재아이템이 매핑되어 진행중인 요청서가 존재합니다."),
	WR_APPROVE_ITEM_WF_MAPPED(8029, "결재아이템이 매핑된 워크플로우 액션이 존재합니다."),
	WR_APPROVE_DISCARD_FAIL(8030, "접수 상태가 아닌 건은 회수할 수 없습니다."),

	//인사, 부서 관련
	Dept_NOT_EXISTS(8100,"부서정보가 존재하지 않습니다"),
	Chief_NOT_EXISTS(8101,"부서장정보가 존재하지 않습니다"),

	/* Exception */
	UNKNOWN_ERROR(9999, "Unknown Error.");
	
	
	private final int code;
	private final String message;


	ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
}