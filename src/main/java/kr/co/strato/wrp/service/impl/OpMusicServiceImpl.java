package kr.co.strato.wrp.service.impl;

import kr.co.strato.wrp.mapper.OpMusicMapper;
import kr.co.strato.wrp.model.OpMusic;
import kr.co.strato.wrp.service.OpMusicService;
import kr.co.strato.wrp.web.dto.OpMusicDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpMusicServiceImpl implements OpMusicService {
    private final OpMusicMapper opMusicMapper;

	@Override
	public void insertMusic(OpMusicDto dto) {
		dto.setMusicUrl(dto.getMusicUrl().replace("youtu.be/", "youtube.com/embed/"));
		dto.setMusicUrl(dto.getMusicUrl().replace("/watch?", "/embed/"));
		dto.setMusicUrl(dto.getMusicUrl()+"?autoplay=1");
		OpMusic music = dto.toEntity();
		opMusicMapper.insertMusic(music);
	}

	@Override
	public List<OpMusic> getMusicList(OpMusicDto dto) {
		
		return opMusicMapper.selectMusicList(dto);
	}
	@Override
	public OpMusic getMusic(String reqSongNo) {
		return opMusicMapper.selectMusic(reqSongNo);
	}
	
	
	@Override
	public void udpateMusicInfo(OpMusicDto dto) {
		opMusicMapper.udpateMusicInfo(dto);
	}

	@Override
	public void initMusicInfo(OpMusicDto dto) {
		opMusicMapper.updateInitPlayingN(dto);
	}

	@Override
	public Integer selectLastPlayMusic(OpMusicDto dto) {
		return opMusicMapper.selectLastPlayMusic(dto);
		
	}


}
