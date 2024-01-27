package kr.co.strato.wrp.service;


import java.util.List;

import kr.co.strato.wrp.model.OpMusic;
import kr.co.strato.wrp.web.dto.OpMusicDto;

public interface OpMusicService {
	void insertMusic(OpMusicDto dto);

	List<OpMusic> getMusicList(OpMusicDto dto);
	
	OpMusic getMusic(String reqSongNo);

	void udpateMusicInfo(OpMusicDto dto);

	void initMusicInfo(OpMusicDto dto);

	Integer selectLastPlayMusic(OpMusicDto dto);
}
