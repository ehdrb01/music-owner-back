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
public enum DeployState {
    STANDBY("STANDBY", "대기중"),
    PROCEEDING("PROCEEDING", "진행중"),
    CANCEL("CANCEL", "취소"),
    FAIL("FAIL", "실패"),
    COMPLETE("COMPLETE", "완료");

    private String code;
    private String text;

    DeployState(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private static final Map<String, DeployState> map =
            Stream.of(values()).collect(Collectors.toMap(DeployState::name, e -> e));

    public static DeployState findByCode(String code) throws GeneralException {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new GeneralException(ErrorCode.CODE_NOT_EXISTS));
    }

    public static DeployState valueOfSil(int SilhouetteStatus) {
        switch(SilhouetteStatus) {
            case 1:
                return PROCEEDING;
            case 2:
                return CANCEL;
            case 4:
                return FAIL;
            case 16:
                return COMPLETE;
            default:
                throw new GeneralException(ErrorCode.CODE_NOT_EXISTS, SilhouetteStatus);
        }
    }
}
