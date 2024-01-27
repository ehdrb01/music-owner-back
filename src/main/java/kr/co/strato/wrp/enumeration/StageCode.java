package kr.co.strato.wrp.enumeration;

import lombok.Getter;

@Getter
public enum StageCode {
    STANDBY("STANDBY", "할일"),
    PROCEEDING("PROCEEDING", "진행"),
    COMPLETE("COMPLETE", "완료");

    private String code;
    private String text;

    StageCode(String code, String text) {
        this.code = code;
        this.text = text;
    }
}
