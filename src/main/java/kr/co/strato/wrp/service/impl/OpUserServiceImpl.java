package kr.co.strato.wrp.service.impl;

import kr.co.strato.wrp.enumeration.RoleCode;
import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import kr.co.strato.wrp.mapper.OpUserMapper;
import kr.co.strato.wrp.model.OpStore;
import kr.co.strato.wrp.model.OpUser;
import kr.co.strato.wrp.service.OpUserService;
import kr.co.strato.wrp.util.DataMap;
import kr.co.strato.wrp.util.SecurityContextCheck;
import kr.co.strato.wrp.web.dto.Paging;
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
public class OpUserServiceImpl implements OpUserService {

    private final OpUserMapper OpUserMapper;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public OpUser login(OpUserDto.OpUserLoginReq dto) {
        OpUser OpUser = Optional.ofNullable(OpUserMapper.selectUser(dto.getUserId())).orElseThrow(() -> new GeneralException(ErrorCode.USER_NOT_FOUND));
        log.debug("{} : {}", dto.getPassword(),OpUser.getPassword());
        System.out.println("password1>>>");
        System.out.println(dto.getPassword());
        System.out.println("password2>>>");
        System.out.println(OpUser.getPassword());
        if(!passwordEncoder.matches(dto.getPassword(), OpUser.getPassword())) {
            throw new GeneralException(ErrorCode.PW_INVALID);
        }
        return OpUser;
    }

    /*골드넷 로그인 유저는 패스워드 없이 사용자 정보 리턴*/
	@Override
	public OpUser gnetLogin(OpUserLoginReq dto) {
		//골드넷 사용자는 로그인한 패스워드로 업데이트
		String encodePw = passwordEncoder.encode(dto.getPassword());
		dto.setPassword(encodePw);
		OpUserMapper.updateUserPasswordGnet(dto);

		OpUser OpUser = Optional.ofNullable(OpUserMapper.selectUser(dto.getUserId())).orElseThrow(() -> new GeneralException(ErrorCode.USER_NOT_FOUND));
		return OpUser;
	}

	@Override
	public  List<DataMap> selectMyRoles(String userId){
		List<DataMap> list = OpUserMapper.selectMyRoles(userId);
		log.debug(list.toString());
	return list;
	}

	@Override
    public List<OpUser> getUserList(OpUserDto.OpUserListReq dto) {
        return OpUserMapper.selectUserList(dto);
    }

    @Override
    public long getUserTotalCount(OpUserDto.OpUserListReq dto) {
        return OpUserMapper.selectUserTotalCount(dto);
    }

    @Override
    public OpUser searchUser(String userId) {
        OpUser OpUser = Optional.ofNullable(OpUserMapper.selectUser(userId)).orElseThrow(() -> new GeneralException(ErrorCode.USER_NOT_FOUND));
        return OpUser;
    }

    @Override
    public void insertUser(OpUserDto dto) {
        OpUser existUser = OpUserMapper.selectUser(dto.getUserId());
        if (existUser != null) {
            throw new GeneralException(ErrorCode.USER_EXISTS);
        }
        String encodePw = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encodePw);
        OpUser OpUser = dto.toEntity();
        OpUserMapper.insertUser(OpUser);
    }

    @Override
    public void updateUserPassword(OpUserDto dto) {
        String encodePw = passwordEncoder.encode(dto.getNewPassword());
        dto.setPassword(encodePw);
        OpUser OpUser = dto.toEntity();
        OpUserMapper.updateUserPassword(OpUser);
    }
    @Override
    public void updateUserPasswordReset(OpUserDto dto) {
        String encodePw = passwordEncoder.encode(dto.getNewPassword());
        dto.setPassword(encodePw);
        OpUser OpUser = dto.toEntity();
        OpUserMapper.updateUserPasswordReset(OpUser);
    }

	@Override
	public void udpateUserInfo(OpUserDto OpUser) {
	        OpUserMapper.udpateUserInfo(OpUser);

	}

	@Override
	public int selectUserGroupListCount() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public OpUserInfo getChiefUserByDbrnCode(String dbrnCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpUserInfo getChiefUserByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getUserTypeIdByCode(String OpUserTypeCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserTypeCodeById(Long OpUserTypeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OpUser> getUserListByGroupId(long OpUserGroupId) {
		// TODO Auto-generated method stub
		return null;
	}
}
