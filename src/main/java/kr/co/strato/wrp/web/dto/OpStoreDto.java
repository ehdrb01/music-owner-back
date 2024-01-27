package kr.co.strato.wrp.web.dto;

import kr.co.strato.wrp.model.OpStore;
import kr.co.strato.wrp.model.OpUser;
import kr.co.strato.wrp.util.DataMap;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OpStoreDto {
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

    public OpStore toEntity() {
        return OpStore.builder()
        		.storeNo(storeNo)
        		.storeId(storeId)
        		.storePassword(storePassword)
        		.storeQrcodeAuth(storeQrcodeAuth)
        		.storeQrcode(storeQrcode)
        		.storeNm(storeNm)
        		.storePhone(storePhone)
        		.storeAddr1(storeAddr1)
        		.storeAddr2(storeAddr2)
        		.storeOwnerNm(storeOwnerNm)
        		.regDate(regDate)
                .build();
    }

    @Getter
	public static class OpStoreInfo {
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

        @Builder
        public OpStoreInfo(String storeNo ,
        		String storeId ,
        		String storePassword ,
        		String storeQrcodeAuth ,
        		String storeQrcode ,
        		String storeNm ,
        		String storePhone ,
        		String storeAddr1 ,
        		String storeAddr2 ,
        		String storeOwnerNm ,
        		String regDate ) {
        	this.storeNo = storeNo;
        	this.storeId = storeId;
        	this.storePassword = storePassword;
        	this.storeQrcodeAuth = storeQrcodeAuth;
        	this.storeQrcode = storeQrcode;
        	this.storeNm = storeNm;
        	this.storePhone = storePhone;
        	this.storeAddr1 = storeAddr1;
        	this.storeAddr2 = storeAddr2;
        	this.storeOwnerNm = storeOwnerNm;
        	this.regDate = regDate;

        }

        public static OpStoreInfo toDto(OpStore opStore) {
            return OpStoreInfo.builder()
            		.storeNo(opStore.getStoreNo())
            		.storeId(opStore.getStoreId())
            		.storePassword(opStore.getStorePassword())
            		.storeQrcodeAuth(opStore.getStoreQrcodeAuth())
            		.storeQrcode(opStore.getStoreQrcode())
            		.storeNm(opStore.getStoreNm())
            		.storePhone(opStore.getStorePhone())
            		.storeAddr1(opStore.getStoreAddr1())
            		.storeAddr2(opStore.getStoreAddr2())
            		.storeOwnerNm(opStore.getStoreOwnerNm())
            		.regDate(opStore.getRegDate())
                    .build();
        }

        public OpStore toEntity() {
            return OpStore.builder()
            		.storeNo(this.storeNo)
            		.storeId(this.storeId)
            		.storePassword(this.storePassword)
            		.storeQrcodeAuth(this.storeQrcodeAuth)
            		.storeQrcode(this.storeQrcode)
            		.storeNm(this.storeNm)
            		.storePhone(this.storePhone)
            		.storeAddr1(this.storeAddr1)
            		.storeAddr2(this.storeAddr2)
            		.storeOwnerNm(this.storeOwnerNm)
            		.regDate(this.regDate)
                    .build();
        }
    }

    @Getter
    public static class OpStoreInfoList {
        private List<OpStoreInfo> list;
        @Setter
        private long totalCnt;
        public OpStoreInfoList(List<OpStoreInfo> list) {
            this.list = list;
        }
    }

    @Getter
    @Setter
    public static class OpStoreListReq {
    	private String storeNo;
    	private String storeNm;
    	private String storeId;
        private Integer size;
        private Integer offset;

        public int getPage(){
            return (this.offset/this.size) + 1;
        }
    }

}
