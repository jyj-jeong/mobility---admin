package com.ohdocha.admin.domain.user;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Data
@Getter
@Setter
@Alias("dcUserInfoResponse")
public class DochaAdminDcUserInfoResponse {
	
	
    private String urIdx;						// 회원idx
    private String userId;						// 회원ID
    private String userPassword;				// 비밀번호
    private String userStatusCode;				// 회원상태코드
    private String userName;					// 이름
    private String userBirthday;				// 생년월일
    private String userContact1;				// 연락처1
    private String userContact2;				// 연락처2
    private String userGender;					// 성별CODE
    private String userZipcode;					// 우편번호
    private String userAddress;					// 주소
    private String userAddressDetail;			// 상세주소
    private String userIdentityAuthYn;			// 본인인증여부
    private String userIdentityAuthDate;		// 인증일자
    private String userCi;						// CI
    private String userDi;						// DI
    private String userNationalCode;			// 내/외국인구분
    private String userCertType;				// 인증서유형
    private String userGradeCode;				// 회원등급
    private String userRole;					// 멤버역할(ROLE)
    private String rtIdx;						// 회원사idx
    private String userGroupCode;				// 회원분류코드
    private String userLicenseOwnYn;			// 면허소유여부
    private String userPayRegisterYn;			// 결제수단등록여부
    private String corporationIdx;				// 법인idx
    private String userPushAgreeYn;				// PUSH동의여부
    private String joinChannel;    				// 가입경로
    private String socialLoginPath;				// 연동로그인경로
    private String socialLoginEmail;			// 연동로그인메일
    private String userWithdrawDate;			// 회원탈퇴일시
    private int loginFailCount;					// 로그인시도횟수
    private String regDt;						// 등록일시
    private String regId;						// 등록자
    private String modDt;						// 수정일시
    private String modId;						// 수정자
    private String accessYn;				    // 회원사 접속허용 여부
    private int useYn;				        // 회원 접속허용 여부

    private String companyName;					//회원사이름
    private String branchName;					//지점명

}
