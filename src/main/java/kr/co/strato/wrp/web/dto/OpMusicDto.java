package kr.co.strato.wrp.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import kr.co.strato.wrp.model.OpMusic;

@Getter
public class OpMusicDto {

	private String reqSongNo;
	private String reqUserId;
	private String reqStoreNo;
	private String reqSongNm;
	private String reqSingerNm;
	private String playedYn;
	private String playingYn;
	private String regDate;
	@Setter
	private String musicUrl;

	  @Getter
	  @Builder
	  public static class OpMusicList {
	    private List<OpMusicDto> list;
	  }

	public OpMusic toEntity() {
            return OpMusic.builder()
            		.reqUserId(this.reqUserId)
            		.reqStoreNo(this.reqStoreNo)
            		.reqSongNm(this.reqSongNm)
            		.reqSingerNm(this.reqSingerNm)
            		.playedYn(this.playedYn)
            		.playingYn(this.playingYn)
            		.regDate(this.regDate)
            		.musicUrl(this.musicUrl)
                    .build();
	}
	@Getter
	public static class OpMusicInfo {
	    private Integer reqSongNo;
		private String reqUserId;
		private String reqStoreNo;
		private String reqSongNm;
		private String reqSingerNm;
		private String playedYn;
		private String playingYn;
		private String regDate;
		private String musicUrl;
        @Builder
        public OpMusicInfo(
        		Integer reqSongNo,
        		String reqUserId,
        		String reqStoreNo,
        		String reqSongNm,
        		String reqSingerNm,
        		String playingYn,
        		String playedYn,
        		String regDate,
        		String musicUrl
        		) {
        	this.reqSongNo = reqSongNo;
        	this.reqUserId = reqUserId;
        	this.reqStoreNo = reqStoreNo;
        	this.reqSongNm = reqSongNm;
        	this.reqSingerNm = reqSingerNm;
        	this.playingYn = playingYn;
        	this.playedYn = playedYn;
        	this.regDate = regDate;
        	this.musicUrl = musicUrl;

        }

        public static OpMusicInfo toDto(OpMusic OpMusic) {
            return OpMusicInfo.builder()
            		.reqSongNo(OpMusic.getReqSongNo())
            		.reqUserId(OpMusic.getReqUserId())
            		.reqStoreNo(OpMusic.getReqStoreNo())
            		.reqSongNm(OpMusic.getReqSongNm())
            		.reqSingerNm(OpMusic.getReqSingerNm())
            		.playedYn(OpMusic.getPlayedYn())
            		.playingYn(OpMusic.getPlayingYn())
            		.regDate(OpMusic.getRegDate())
            		.musicUrl(OpMusic.getMusicUrl())
                    .build();
        }

        public OpMusic toEntity() {
            return OpMusic.builder()
            		.reqSongNo(this.reqSongNo)
            		.reqUserId(this.reqUserId)
            		.reqStoreNo(this.reqStoreNo)
            		.reqSongNm(this.reqSongNm)
            		.reqSingerNm(this.reqSingerNm)
            		.playingYn(this.playingYn)
            		.playedYn(this.playedYn)
            		.regDate(this.regDate)
            		.musicUrl(this.musicUrl)
                    .build();
        }
    }

    @Getter
    public static class OpMusicInfoList {
        private List<OpMusicInfo> list;
        @Setter
        private long totalCnt;
        public OpMusicInfoList(List<OpMusicInfo> list) {
            this.list = list;
        }
    }
}
