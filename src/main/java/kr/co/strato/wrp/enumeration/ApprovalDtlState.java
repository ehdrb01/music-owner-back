package kr.co.strato.wrp.enumeration;

import lombok.Getter;

@Getter
public enum ApprovalDtlState {
    APPLY("APPLY", "접수"),
    HOLD("HOLD", "대기"),
    APPROVE("APPROVE", "승인"),
    REJECT("REJECT", "반려");

    private String code;
    private String text;

    ApprovalDtlState(String code, String text) {
        this.code = code;
        this.text = text;
    }
}
