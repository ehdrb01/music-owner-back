package kr.co.strato.wrp.web.dto;

import kr.co.strato.wrp.model.OpUser;
import kr.co.strato.wrp.util.DataMap;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
public class OpUserDto {
	@Setter
    private String userId;
    @Setter
    private String password;
    @Setter
    private String newPassword;
    private String userType;
    private String name;
    private String email;
    private String regDate;

    public OpUser toEntity() {
        return OpUser.builder()
                .userId(this.userId)
                .name(this.name)
                .password(this.password)
                .userType(this.userType)
                .email(this.email)
                .regDate(this.regDate)
                .build();
    }

    @Getter
    public static class OpUserInfo {
        private String userId;
        private String name;
        private String email;
        private String userType;
        @Setter
        private String token;

        @Builder
        public OpUserInfo(String userId, String name, String email,String userType) {
            this.userId = userId;
            this.name = name;
            this.email = email;
            this.userType = userType;
        }
        public static OpUserInfo toDto(OpUser OpUser) {
            return OpUserInfo.builder()
                    .userId(OpUser.getUserId())
                    .name(OpUser.getName())
                    .email(OpUser.getEmail())
                    .userType(OpUser.getUserType())
                    .build();
        }

        public OpUser toEntity() {
            return OpUser.builder()
                    .userId(this.userId)
                    .name(this.name)
                    .email(this.email)
                    .userType(this.userType)
                    .build();
        }
    }

    @Getter
    public static class OpUserInfoList {
        private List<OpUserInfo> list;
        @Setter
        private long totalCnt;
        public OpUserInfoList(List<OpUserInfo> list) {
            this.list = list;
        }
    }

    @Getter
    @Setter
    public static class OpUserLoginReq {
        private String userId;
        private String password;
        private String userType;
    }

    @Getter
    @Setter
    public static class OpUserListReq {
        private String userId;
        private String name;
        private String userType;
        private Integer size;
        private Integer offset;

        public int getPage(){
            return (this.offset/this.size) + 1;
        }
    }

    @Getter
    @Builder
    public static class OpUserInfoSimple {
        private String userId;
        private String name;
        private String email;
        private String userType;
        @Setter
        private String token;

        public static OpUserInfoSimple from(OpUser OpUser) {
            return OpUserInfoSimple.builder()
                    .userId(OpUser.getUserId())
                    .name(OpUser.getName())
                    .email(OpUser.getEmail())
                    .userType(OpUser.getUserType())
                    .build();
        }
    }
}
