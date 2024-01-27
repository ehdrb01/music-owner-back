package kr.co.strato.wrp.enumeration;

import lombok.Getter;

@Getter
public enum AlertMessage {
    ALERT_APPR_REQ("ALERT_APPR_REQ", "결재요청알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}] {stage} 결재요청이 접수되었습니다."),
    ALERT_APPR_RESULT_APPROVED("ALERT_APPR_RESULT_APPROVED", "결재 결과 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage} 결재요청이 승인되었습니다."),
    ALERT_APPR_RESULT_REJECT("ALERT_APPR_RESULT_REJECT", "결재 결과 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage} 결재요청이 반려되었습니다."),
    ALERT_FIN_NEGO("ALERT_FIN_NEGO", "요건협의완료알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[완료] 요건협의가 완료되었습니다."),
    ALERT_REQ_NEGO_REVIEW("ALERT_REQ_NEGO_REVIEW", "요건검토 요청 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[요청] 요건검토 요청이 접수되었습니다."),
    ALERT_FIN_NEGO_REVIEW("ALERT_FIN_NEGO_REVIEW", "요건검토 완료 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[완료] 요건검토가 완료되었습니다."),
    ALERT_REQ_CODE_REVIEW("ALERT_REQ_CODE_REVIEW", "코드리뷰 요청 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[요청] 코드리뷰 요청이 접수되었습니다."),
    ALERT_REG_CODE_REVIEW("ALERT_REG_CODE_REVIEW", "코드리뷰 등록 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[등록] 코드리뷰가 등록되었습니다."),
    ALERT_REQ_INSP_COMPLIANCE("ALERT_REQ_INSP_COMPLIANCE", "컴플라이언스검수 요청 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[요청] 컴플라이언스검수 요청이 접수되었습니다."),
    ALERT_REG_INSP_COMPLIANCE("ALERT_REG_INSP_COMPLIANCE", "컴플라이언스검수 결과 등록 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[요청] 컴플라이언스검수 결과가 등록되었습니다."),
    ALERT_REQ_FIN_COMPLIANCE("ALERT_REQ_FIN_COMPLIANCE", "컴플라이언스검수 완료 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[완료] 컴플라이언스검수 요청이 완료되었습니다."),
    ALERT_REQ_INSP_QA("ALERT_REQ_INSP_QA", "QA검수 요청 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[요청] QA검수 요청이 접수되었습니다."),
    ALERT_REG_INSP_QA("ALERT_REG_INSP_QA", "QA검수 결과 등록 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[요청] QA검수 결과가 등록되었습니다."),
    ALERT_REQ_FIN_QA("ALERT_REQ_FIN_QA", "QA검수 완료 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[완료] QA검수가 완료되었습니다."),
    ALERT_REQ_DEPLOY_DEV("ALERT_REQ_DEPLOY_DEV", "개발배포 요청 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[요청] 개발배포 요청이 접수되었습니다."),
    ALERT_FIN_DEPLOY_PROD("ALERT_FIN_DEPLOY_PROD", "운영배포 완료 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[완료] 운영배포가 완료되었습니다."),
    ALERT_REQ_DEPLOY_CONFIRM("ALERT_REQ_DEPLOY_CONFIRM", "운영배포확인 일정 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[완료] 운영배포확인 일정 알림입니다."),
    ALERT_FIN_DEPLOY_CONFIRM_DEV("ALERT_FIN_DEPLOY_CONFIRM_DEV", "ICT담당자 운영배포확인 완료 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[완료] ICT담당자 운영배포확인이 완료되었습니다."),
    ALERT_FIN_DEPLOY_CONFIRM_REQ("ALERT_FIN_DEPLOY_CONFIRM_REQ", "업무요청자 운영배포확인 완료 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[완료] 업무요청자 운영배포확인이 완료되었습니다."),
	ALERT_REQ_INSP_DISCARD("ALERT_REQ_INSP_DISCARD", "폐기검토 요청 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[요청] 폐기검토 요청이 접수되었습니다."),
	ALERT_REG_INSP_DISCARD("ALERT_REG_INSP_DISCARD", "폐기검토 등록 완료 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[요청] 폐기검토 등록이 완료되었습니다."),
	ALERT_FIN_INSP_DISCARD("ALERT_FIN_INSP_DISCARD", "폐기검토 등록 완료 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[요청] 폐기검토 등록이 완료되었습니다."),
    ALERT_FIN_CONFIRM_DEV_INSTANT("ALERT_FIN_CONFIRM_DEV_INSTANT", "ICT담당자 확인 완료 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[완료] ICT담당자 확인이 완료되었습니다."),
    ALERT_FIN_CONFIRM_REQ_INSTANT("ALERT_FIN_CONFIRM_REQ_INSTANT", "업무요청자 확인 완료 알림",
            "[{wrCode}]{title}-{stage} {subject} \n [{dt}]{stage}[완료] 업무요청자 확인이 완료되었습니다.");

    private String code;
    private String subject;
    private String text;

    AlertMessage(String code,String subject, String text) {
        this.code = code;
        this.subject = subject;
        this.text = text;
    }
}
