package kr.co.strato.wrp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpMusic {

    @Schema(description="플레이리스트")
    private Integer reqSongNo;
	private String reqUserId;
	private String reqStoreNo;
	private String reqSongNm;
	private String reqSingerNm;
	private String playedYn;
	private String playingYn;
	private String regDate;
	private String musicUrl;

}
