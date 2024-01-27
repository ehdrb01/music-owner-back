package kr.co.strato.wrp.enumeration;

import lombok.Getter;

@Getter
public enum ReqType {
	REQ_SI("REQ_SI", "신규개발"),
	REQ_SM("REQ_SM", "변경개발"),
	REQ_RULE_CHANGE("REQ_RULE_CHANGE", "제도변경"),
	REQ_URGENT("REQ_URGENT","긴급개발");

    private String code;
    private String text;

    ReqType(String code, String text) {
        this.code = code;
        this.text = text;
    }
}
