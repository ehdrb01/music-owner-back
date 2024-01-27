package kr.co.strato.wrp.mapper;

import java.util.List;

import kr.co.strato.wrp.web.dto.OpStoreDto;

import org.apache.ibatis.annotations.Mapper;

import kr.co.strato.wrp.model.OpStore;
@Mapper
public interface OpStoreMapper {
	List<OpStore> selectStoreList();

	void udpateStoreInfo(OpStoreDto dto);

	OpStore selectStore(String storeNo);

	OpStore selectStoreNo(String userId);
	
	void insertStore(OpStoreDto dto);

}
