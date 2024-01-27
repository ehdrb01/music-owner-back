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
import uk.org.webcompere.systemstubs.stream.output.NoopStream;

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

@RequestMapping("/ourplay/store")
@RestController
@Slf4j
@Tag(name = "OpUserController - 스토어 정보 처리 및 연동 구성")
@RequiredArgsConstructor
public class OpStoreController {
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final OpUserService OpUserService;
    private final OpStoreService opStoreService;

	@Autowired
    private Environment env;
	
    @Operation(summary = "스토어 목록 조회", description = "스토어 목록 조회 API")
    @GetMapping("/list")
    public ApiResponse<OpStoreInfoList> getStoreList() {
        List<OpStore> list = opStoreService.getStoreList();
        OpStoreDto.OpStoreInfoList infoList = new OpStoreDto.OpStoreInfoList(list.stream().map(OpStoreDto.OpStoreInfo::toDto).collect(Collectors.toList()));
        return ApiResponse.success(infoList);
    }
    
    @Operation(summary = "스토어  조회", description = "스토어 목록 조회 API")
    @GetMapping("/{storeNo}")
    public ApiResponse<OpStore> getStore(@PathVariable String storeNo) {
        OpStore opStore = opStoreService.getStore(storeNo);
        return ApiResponse.success(opStore);
    }
    @Operation(summary = "스토어 조회", description = "스토어 목록 조회 API")
    @GetMapping("/findNo/{userId}")
    public ApiResponse<OpStore> getStoreNo(@PathVariable String userId) {
        OpStore opStore = opStoreService.getStoreNo(userId);
        return ApiResponse.success(opStore);
    }
    
    @Operation(summary = "스토어 정보 변경")
    @PutMapping("/update")
    public ApiResponse udpateStoreInfo(@RequestBody OpStoreDto dto){
    	System.out.println("dto.getStoreNm()"); 
    	System.out.println(dto.getStoreNm()); 
    	OpStore opStore = Optional.ofNullable(opStoreService.getStore(dto.getStoreNo())).orElseThrow(() -> new GeneralException(ErrorCode.USER_NOT_FOUND));
    	System.out.println("opStore.getStoreNm()");
    	System.out.println(opStore.getStoreNm());
    	opStoreService.udpateStoreInfo(dto);
        return ApiResponse.success();
    }
    
    @Operation(summary = "스토어 생성", description = "스토어 생성 API")
    @PostMapping("/create")
    public ApiResponse insertStoreInfo(@RequestBody OpStoreDto dto) {
    	opStoreService.insertStore(dto);
        return ApiResponse.success();
    }

}
