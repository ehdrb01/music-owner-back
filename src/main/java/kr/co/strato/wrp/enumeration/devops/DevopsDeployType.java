package kr.co.strato.wrp.enumeration.devops;

import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum DevopsDeployType {
    KUBERNETES(0, "DOCKER"),
    SSH(1, "SSH");

    private int code;

    private String jenkinsName;

    private static final Map<Integer, DevopsDeployType> map =
            Stream.of(values()).collect(Collectors.toMap(DevopsDeployType::getCode, e -> e));

    public static DevopsDeployType findByCode(int code) throws GeneralException {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new GeneralException(ErrorCode.CODE_NOT_EXISTS));
    }
}
