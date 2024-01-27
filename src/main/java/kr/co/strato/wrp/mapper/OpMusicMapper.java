package kr.co.strato.wrp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.strato.wrp.model.OpMusic;
import kr.co.strato.wrp.web.dto.OpMusicDto;

@Mapper
public interface OpMusicMapper {
	void insertMusic(OpMusic music);

	List<OpMusic> selectMusicList(OpMusicDto dto);

	OpMusic selectMusic(String reqSongNo);

	void udpateMusicInfo(OpMusicDto dto);

	void updateInitPlayingN(OpMusicDto dto);

	Integer selectLastPlayMusic(OpMusicDto dto);
}
