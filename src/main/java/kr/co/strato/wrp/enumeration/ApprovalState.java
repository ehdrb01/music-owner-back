package kr.co.strato.wrp.enumeration;

import lombok.Getter;

@Getter
public enum ApprovalState {
    APPLY("APPLY", "접수"),
    PARTIAL("PARTIAL", "부분승인"),
    APPROVE("APPROVE", "승인"),
    REJECT("REJECT", "반려"),
    DISCARD("DISCARD", "폐기");

    private String code;
    private String text;

    ApprovalState(String code, String text) {
        this.code = code;
        this.text = text;
    }
}
