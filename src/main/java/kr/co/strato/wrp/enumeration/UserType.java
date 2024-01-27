package kr.co.strato.wrp.enumeration;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import lombok.Getter;

@Getter
public enum UserType {
    WR_WRITTER("WR_WRITTER", "업무요청자", "업무요청서 작성자, 업무요청시 지정"),
    WR_DEPT_CHIEF("WR_DEPT_CHIEF", "업무요청부서장", "업무요청서 승인자"),
    ICT_DEVELOPER("ICT_DEVELOPER", "ICT 담당자", "개발계획서 작성자, 개발 계획 작성시 지정"),
    ICT_REQ_OFFICER("ICT_REQ_OFFICER", "ICT 메인 담당자", "개발계획서 작성시 지정"),
    WR_OBSERVER("WR_OBSERVER", "관계자", ""),
    WR_REVIEWER("WR_REVIEWER", "업무요청검토자", "사용자 그룹 권한 조회"),
    CHANGE_MGMT_OFFICER("CHANGE_MGMT_OFFICER", "변경관리위원회 담당자", ""),
    RELATED_DEPT_CHIEF("RELATED_DEPT_CHIEF", "유관부서장", "개발 계획 작성시 지정"),
    CODE_REVIEWER("CODE_REVIEWER", "코드리뷰담당자", "코드리뷰 요청시 지정"),
    COMPLIANCE_INSPECTOR("COMPLIANCE_INSPECTOR", "컴플라이언스검수 담당자", "사용자 그룹 권한 조회"),
    QC_TESTER("QC_TESTER", "제3자 테스트 담당자", "사용자 그룹 권한 조회"),
    QA_INSPECTOR("QA_INSPECTOR", "QA 검수 담당자", "사용자 그룹 권한 조회"),
    ICT_DEPT_CHIEF("ICT_DEPT_CHIEF", "ICT 부서장", ""),
    WR_PROCESS_REVIEWER("WR_PROCESS_REVIEWER", "프로세스 검토 담당자", "프로세스 검톹 담당자"),
    RELATED_DEPT_OFFICER("RELATED_DEPT_OFFICER", "유관부서담당자", ""),
    SHIN_BIZ_GROUP("SHIN_BIZ_GROUP", "신BIZ그룹 담당자", "신BIZ그룹 담당자"),
    ICT_TEAM_LEADER("ICT_TEAM_LEADER", "ICT 팀장", "ICT 팀장"),
    TASK_OWNER("TASK_OWNER", "개발계획 담당자", "개발계획 담당자"),
    SVC_MAINTAINER("SVC_MAINTAINER","소스폐기 검토자","소스폐기 검토자");
	
    private String code;
    private String text;
    private String desc;

    UserType(String code, String text, String desc) {
        this.code = code;
        this.text = text;
        this.desc= desc;
    }

    private static final Map<String, UserType> map =
            Stream.of(values()).collect(Collectors.toMap(UserType::name, e -> e));
    
    public static UserType findByCode(String code) throws GeneralException {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new GeneralException(ErrorCode.CODE_NOT_EXISTS));
    }
}
