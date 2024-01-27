package kr.co.strato.wrp.mapper;

import java.util.List;

import kr.co.strato.wrp.web.dto.OpUserDto;
import kr.co.strato.wrp.web.dto.OpUserDto.OpUserListReq;
import kr.co.strato.wrp.web.dto.OpUserDto.OpUserLoginReq;

import org.apache.ibatis.annotations.Mapper;


import kr.co.strato.wrp.model.OpUser;
import kr.co.strato.wrp.util.DataMap;
import kr.co.strato.wrp.web.dto.Paging;

@Mapper
public interface OpUserMapper {

    OpUser selectUser(String userId);

    List<DataMap> selectMyRoles(String userId);

    List<OpUserDto.OpUserInfo> selectUsers(List<String> userIds);

    void insertUser(OpUser wfUser);

    void updateUserPassword(OpUser wfUser);

    void updateUserPasswordReset(OpUser wfUser);
    //골드넷 패스워드로 업데이트
    void updateUserPasswordGnet(OpUserLoginReq dto);


    int selectUserGroupListCount();


    OpUser selectChiefUserByDbrnCode(String dbrnCode);

    OpUser selectChiefUserByUserId(String userId);

    Long selectUserTypeIdByCode(String wfUserTypeCode);

    String selectUserTypeCodeById(Long wfUserTypeId);

	List<OpUser> selectUserListByGroupId(long wfUserGroupId);

	long selectUserTotalCount(OpUserListReq dto);

	List<OpUser> selectUserList(OpUserListReq dto);

	void udpateUserInfo(OpUserDto wfUser);

	List<DataMap> selectInsteadUsers(String userId);
}
