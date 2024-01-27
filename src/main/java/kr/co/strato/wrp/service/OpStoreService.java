package kr.co.strato.wrp.service;


import java.util.List;

import kr.co.strato.wrp.model.OpStore;
import kr.co.strato.wrp.web.dto.OpStoreDto;

public interface OpStoreService {

	List<OpStore> getStoreList();

	void udpateStoreInfo(OpStoreDto dto);

	OpStore getStore(String storeNo);

	void insertStore(OpStoreDto dto);

	OpStore getStoreNo(String userId);
	
}
