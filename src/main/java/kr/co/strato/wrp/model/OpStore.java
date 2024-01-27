package kr.co.strato.wrp.model;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class OpStore {
    private String storeNo;
    private String storeId;
    private String storePassword;
    private String storeQrcodeAuth;
    private String storeQrcode;
    private String storeNm;
    private String storePhone;
    private String storeAddr1;
    private String storeAddr2;
    private String storeOwnerNm;
    private String regDate;
}
