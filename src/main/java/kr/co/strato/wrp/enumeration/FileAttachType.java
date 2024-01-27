package kr.co.strato.wrp.enumeration;

import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum FileAttachType {
    FILE_ATT_UAT_TEST("FILE_ATT_UAT_TEST", "UAT 테스트 시나리오 파일 업로드"),
    FILE_ATT_UAT_TEST_CASE("FILE_ATT_UAT_TEST_CASE", "UAT 테스트 수행 파일 업로드"),
    FILE_ATT_REQ_INSPECTION("FILE_ATT_REQ_INSPECTION", "검토요청 파일 업로드"),
    FILE_ATT_REQ_REPLY("FILE_ATT_REQ_REPLY", "댓글 파일 업로드"),
    FILE_ATT_DEV_TEST("FILE_ATT_DEV_TEST", "개발 테스트 시나리오 파일 업로드"),
    FILE_ATT_DEV_TEST_CASE("FILE_ATT_DEV_TEST_CASE", "개발 테스트 수행 파일 업로드"),
    FILE_ATT_RELATED_UAT_TEST("FILE_ATT_RELATED_UAT_TEST", "유관부서 UAT 시나리오 파일 업로드"),
    FILE_ATT_RELATED_UAT_TEST_CASE("FILE_ATT_RELATED_UAT_TEST_CASE", "유관부서 UAT 수행 파일 업로드"),
    FILE_ATT_QC_TEST("FILE_ATT_QC_TEST", "제3자 테스트 시나리오 파일 업로드"),
    FILE_ATT_DISCARD("FILE_ATT_DISCARD", "소스 폐기신청 파일 업로드"),
    FILE_ATT_INSP_CODE_REVIEW("FILE_ATT_INSP_CODE_REVIEW", "코드리뷰 파일 업로드"),
    FILE_ATT_INSP_COMPLIANCE("FILE_ATT_INSP_COMPLIANCE", "컴플라이언스 검수 파일 업로드"),
    FILE_ATT_INSP_QA("FILE_ATT_INSP_QA", "QA 검수 파일 업로드"),
    FILE_ATT_DEPLOY_PROD("FILE_ATT_DEPLOY_PROD", "운영배포 요청 파일 업로드"),
	FILE_ATT_MASTER("FILE_ATT_MASTER","업무개발 요청 파일 업로드"),
	FILE_ATT_TASK("FILE_ATT_TASK","개발계획 요청 파일 업로드"),
	FILE_ATT_INSP_DISCARD("FILE_ATT_INSP_DISCARD","폐기 검토 결과 파일 업로드"),
	FILE_ATT_REG_DISCARD("FILE_ATT_REG_DISCARD","폐기 결과 등록 파일 업로드");

    private String code;
    private String text;

    FileAttachType(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private static final Map<String, FileAttachType> map =
            Stream.of(values()).collect(Collectors.toMap(FileAttachType::name, e -> e));

    public static FileAttachType findByCode(String code) throws GeneralException {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new GeneralException(ErrorCode.CODE_NOT_EXISTS));
    }
}
