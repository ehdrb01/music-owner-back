package kr.co.strato.wrp.enumeration;

import lombok.Getter;

@Getter
public enum ApproveResult {
    HOLD("HOLD", "대기"),
    APPROVE("APPROVE", "결재"),
    DENY("DENY", "반려");

    private String code;
    private String text;

    ApproveResult(String code, String text) {
        this.code = code;
        this.text = text;
    }
}
