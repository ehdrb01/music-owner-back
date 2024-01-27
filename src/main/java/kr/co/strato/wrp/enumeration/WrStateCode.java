package kr.co.strato.wrp.enumeration;

import lombok.Getter;

@Getter
public enum WrStateCode {
    OPEN("OPEN", "접수"),
    INPROGRESS("INPROGRESS", "진행"),
    CLOSED("CLOSED", "완료");

    private String code;
    private String text;

    WrStateCode(String code, String text) {
        this.code = code;
        this.text = text;
    }
}
