package kr.co.strato.wrp.service.impl;

import kr.co.strato.wrp.enumeration.RoleCode;
import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import kr.co.strato.wrp.mapper.OpStoreMapper;
import kr.co.strato.wrp.mapper.OpUserMapper;
import kr.co.strato.wrp.model.OpStore;
import kr.co.strato.wrp.model.OpUser;
import kr.co.strato.wrp.service.OpStoreService;
import kr.co.strato.wrp.service.OpUserService;
import kr.co.strato.wrp.util.DataMap;
import kr.co.strato.wrp.util.SecurityContextCheck;
import kr.co.strato.wrp.web.dto.Paging;
import kr.co.strato.wrp.web.dto.OpStoreDto;
import kr.co.strato.wrp.web.dto.OpUserDto;
import kr.co.strato.wrp.web.dto.OpUserDto.OpUserInfo;
import kr.co.strato.wrp.web.dto.OpUserDto.OpUserInfoList;
import kr.co.strato.wrp.web.dto.OpUserDto.OpUserLoginReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpStoreServiceImpl implements OpStoreService {
	
	private final OpStoreMapper opStoreMapper;
	 
	@Override
	public List<OpStore> getStoreList() {
		 return opStoreMapper.selectStoreList();
	}
	@Override
	public OpStore getStore(String storeNo) {
		return opStoreMapper.selectStore(storeNo);
	}
	@Override
	public void udpateStoreInfo(OpStoreDto dto) {
		 opStoreMapper.udpateStoreInfo(dto);
	}
	@Override
	public void insertStore(OpStoreDto dto) {
		 opStoreMapper.insertStore(dto);
		
	}
	@Override
	public OpStore getStoreNo(String userId) {
		return opStoreMapper.selectStoreNo(userId);
	}



}
