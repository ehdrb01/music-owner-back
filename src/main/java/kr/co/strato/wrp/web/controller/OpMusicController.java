package kr.co.strato.wrp.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import kr.co.strato.wrp.model.OpMusic;
import kr.co.strato.wrp.model.OpStore;
import kr.co.strato.wrp.model.OpUser;
import kr.co.strato.wrp.service.OpMusicService;
import kr.co.strato.wrp.service.OpUserService;
import kr.co.strato.wrp.util.DataMap;
import kr.co.strato.wrp.util.JwtUtils;
import kr.co.strato.wrp.web.dto.*;
import kr.co.strato.wrp.web.dto.OpMusicDto.OpMusicInfoList;
import kr.co.strato.wrp.web.dto.OpStoreDto.OpStoreInfoList;
import kr.co.strato.wrp.web.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/ourplay/music")
@RestController
@Slf4j
@Tag(name = "OpUserController - 사용자 정보 처리 및 연동 구성")
@RequiredArgsConstructor
public class OpMusicController {

	private final OpMusicService musicService;


    @Operation(summary = "신청곡 생성", description = "사용자 생성 API")
    @PostMapping("/regist")
    public ApiResponse<String> insertUserInfo(@RequestBody OpMusicDto dto) {
    	musicService.insertMusic(dto);
        return ApiResponse.success();
    }


    @Operation(summary = "신청곡 목록 조회", description = "스토어 목록 조회 API")
    @PutMapping("/list")
    public ApiResponse<OpMusicInfoList> getMusicList(@RequestBody OpMusicDto dto) {
        List<OpMusic> list = musicService.getMusicList(dto);
        OpMusicDto.OpMusicInfoList infoList = new OpMusicDto.OpMusicInfoList(list.stream().map(OpMusicDto.OpMusicInfo::toDto).collect(Collectors.toList()));
        return ApiResponse.success(infoList);
    }

    @Operation(summary = "신청곡 정보 변경")
    @PutMapping("/update")
    public ApiResponse<String> udpateMusicInfo(@RequestBody OpMusicDto dto){
    	OpMusic opMusic = Optional.ofNullable(musicService.getMusic(dto.getReqSongNo())).orElseThrow(() -> new GeneralException(ErrorCode.USER_NOT_FOUND));
    	musicService.udpateMusicInfo(dto);
        return ApiResponse.success();
    }
    
    @Operation(summary = "신청곡 정보 초기화(플레이중)")
    @PutMapping("/init")
    public ApiResponse<String> initMusicInfo(@RequestBody OpMusicDto dto){
    	musicService.initMusicInfo(dto);
        return ApiResponse.success();
    }
    
    @Operation(summary = "신청곡 재생 마지막곡")
    @PutMapping("/lastPlayed")
    public ApiResponse<Integer> selectLastPlayMusic(@RequestBody OpMusicDto dto){
        return ApiResponse.success(musicService.selectLastPlayMusic(dto));
    }
    
}
