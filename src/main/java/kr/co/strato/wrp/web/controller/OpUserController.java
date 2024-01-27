package kr.co.strato.wrp.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import kr.co.strato.wrp.model.OpStore;
import kr.co.strato.wrp.model.OpUser;
import kr.co.strato.wrp.service.OpStoreService;
import kr.co.strato.wrp.service.OpUserService;
import kr.co.strato.wrp.util.DataMap;
import kr.co.strato.wrp.util.JwtUtils;
import kr.co.strato.wrp.web.dto.*;
import kr.co.strato.wrp.web.dto.OpStoreDto.OpStoreInfoList;
import kr.co.strato.wrp.web.dto.OpUserDto;
import kr.co.strato.wrp.web.dto.OpUserDto.OpUserInfo;
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

@RequestMapping("/ourplay/user")
@RestController
@Slf4j
@Tag(name = "OpUserController - 사용자 정보 처리 및 연동 구성")
@RequiredArgsConstructor
public class OpUserController {
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final OpUserService OpUserService;
    private final OpStoreService opStoreService;

	@Autowired
    private Environment env;

    @Operation(summary = "로그인", description = "로그인 처리")
    @PostMapping("/login")
    public ApiResponse<OpUserDto.OpUserInfo> login(HttpServletRequest req, @RequestBody OpUserDto.OpUserLoginReq dto) throws URISyntaxException {
    	log.info("========"+req.getRemoteHost());
         	if (StringUtils.isEmpty(dto.getUserId()) || StringUtils.isEmpty(dto.getPassword())) throw new GeneralException(ErrorCode.LOGIN_FAIL);
         	OpUser OpUser = OpUserService.login(dto);
         	String pw = new String(Base64.decodeBase64(dto.getPassword()));
         	dto.setPassword(pw);
        	String token = JwtUtils.createToken(OpUser);
        	OpUserDto.OpUserInfo loginInfo = OpUserDto.OpUserInfo.toDto(OpUser);
        	loginInfo.setToken(token);
        	System.out.println("OpUser.getUserType()");
        	System.out.println(OpUser.getUserType());
        	System.out.println("loginInfo.getUserType()");
        	System.out.println(loginInfo.getUserType());
        	
        	return ApiResponse.success(loginInfo);
    }

    @Operation(summary = "사용자 목록 조회", description = "사용자 목록 조회 API")
    @PostMapping("/list")
    public ApiResponse<OpUserDto.OpUserInfoList> getUserList(@ModelAttribute  OpUserDto.OpUserListReq  dto) {
        List<OpUser> list = OpUserService.getUserList(dto);
        OpUserDto.OpUserInfoList infoList = new OpUserDto.OpUserInfoList(list.stream().map(OpUserDto.OpUserInfo::toDto).collect(Collectors.toList()));
        infoList.setTotalCnt(OpUserService.getUserTotalCount(dto));
        return ApiResponse.success(infoList);
    }
    
    
    
    @Operation(summary = "사용자 정보 조회", description = "사용자 정보 조회 API")
    @GetMapping("/{userId}")
    public ApiResponse<OpUserDto.OpUserInfo> searchUserInfo(@PathVariable String userId) {
        OpUser OpUser = OpUserService.searchUser(userId);
        OpUserDto.OpUserInfo OpUserInfo = OpUserDto.OpUserInfo.toDto(OpUser);
        return ApiResponse.success(OpUserInfo);
    }

    @Operation(summary = "사용자 생성", description = "사용자 생성 API")
    @PostMapping("/create")
    public ApiResponse insertUserInfo(@RequestBody OpUserDto dto) {
        OpUserService.insertUser(dto);
        return ApiResponse.success();
    }

    @Operation(summary = "유저 패스워드 변경")
    @PostMapping("/changePassword")
    public ApiResponse updateUserPassword(@RequestBody OpUserDto dto){
    	OpUser OpUser = Optional.ofNullable(OpUserService.searchUser(dto.getUserId())).orElseThrow(() -> new GeneralException(ErrorCode.USER_NOT_FOUND));
//        String pw = new String(Base64.decodeBase64(dto.getNewPassword()));
        String pw = dto.getNewPassword().trim();          //초기패스워드는 shi@ + 사번으로 함
        dto.setNewPassword(pw);
        OpUserService.updateUserPassword(dto);
        return ApiResponse.success();
    }

    @Operation(summary = "유저 패스워드 초기화")
    @PutMapping("/resetPassword")
    public ApiResponse updateResetPassword(@RequestBody OpUserDto dto){
    	OpUser OpUser = Optional.ofNullable(OpUserService.searchUser(dto.getUserId())).orElseThrow(() -> new GeneralException(ErrorCode.USER_NOT_FOUND));
        String pw = dto.getUserId().trim();          //초기패스워드는 shi@ + 사번으로 함
        dto.setNewPassword(pw);
        OpUserService.updateUserPasswordReset(dto);
        return ApiResponse.success();
    }

    @Operation(summary = "유저 패스워드 변경")
    @PutMapping("/udpateUserInfo")
    public ApiResponse udpateUserInfo(@RequestBody OpUserDto dto){
    	OpUser OpUser = Optional.ofNullable(OpUserService.searchUser(dto.getUserId())).orElseThrow(() -> new GeneralException(ErrorCode.USER_NOT_FOUND));
    	OpUserService.udpateUserInfo(dto);
        return ApiResponse.success();
    }
    
}
