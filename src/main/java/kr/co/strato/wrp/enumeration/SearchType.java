package kr.co.strato.wrp.enumeration;

import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import lombok.Getter;
import sil.ict.api.ICTSrCloseType;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum SearchType {
	WR_REG_ID("wrRegId", "등록자 아이디"),
	WR_REG_NAME("wrRegName", "등록자명"),
    WR_SUBJECT("wrSubject", "제목"),
    WR_CODE("wrCode", "문서번호"),
    WR_ACT_TYPE("actType", "액션 타입");

    private String code;
    private String text;

    SearchType(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private static final Map<String, SearchType> map =
            Stream.of(values()).collect(Collectors.toMap(SearchType::name, e -> e));

    public static SearchType findByCode(String code) throws GeneralException {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new GeneralException(ErrorCode.CODE_NOT_EXISTS));
    }

  
}
