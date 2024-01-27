package kr.co.strato.wrp.service;


import java.util.List;

import kr.co.strato.wrp.model.OpStore;
import kr.co.strato.wrp.model.OpUser;
import kr.co.strato.wrp.util.DataMap;
import kr.co.strato.wrp.web.dto.Paging;
import kr.co.strato.wrp.web.dto.OpUserDto;
import kr.co.strato.wrp.web.dto.OpUserDto.OpUserInfo;
import kr.co.strato.wrp.web.dto.OpUserDto.OpUserInfoList;
import kr.co.strato.wrp.web.dto.OpUserDto.OpUserListReq;

public interface OpUserService {

    OpUser login(OpUserDto.OpUserLoginReq dto);
    //pw비교 없음
    OpUser gnetLogin(OpUserDto.OpUserLoginReq dto);

    List<DataMap> selectMyRoles(String userId);

    OpUser searchUser(String userId);

    void insertUser(OpUserDto dto);

    void updateUserPassword(OpUserDto dto);
    void updateUserPasswordReset(OpUserDto dto);


    int selectUserGroupListCount();


    OpUserDto.OpUserInfo getChiefUserByDbrnCode(String dbrnCode);

    OpUserDto.OpUserInfo getChiefUserByUserId(String userId);

    Long getUserTypeIdByCode(String OpUserTypeCode);

    String getUserTypeCodeById(Long OpUserTypeId);

    List<OpUser> getUserListByGroupId(long OpUserGroupId);

	long getUserTotalCount(OpUserListReq dto);

	List<OpUser> getUserList(OpUserListReq dto);

	void udpateUserInfo(OpUserDto dto);
	
	
}
