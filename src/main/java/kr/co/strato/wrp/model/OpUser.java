package kr.co.strato.wrp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpUser {

    @Schema(description="사용자 ID")
    private int idx;
    private String userId;
    private String password;
    private String name;
    private String userType;
    private String email;
    private String regDate;
}
