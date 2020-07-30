package com.ohdocha.admin.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class MemberDto extends DtoExtension{

    private String urIdx;
    private String userId;
    private String userPassword;
    private String userStatusCode;
    private String userName;
    private String userBirthday;
    private String userContact1;
    private String userContact2;
    private String userGender;
    private String userZipcode;
    private String userAddress;
    private String userAddressDetail;
    private String userIdentityAuthYn;
    private String userIdentityAuthDate;
    private String userCi;
    private String userDi;
    private String userNationalCode;
    private String userCertType;
    private String userGradeCode;
    private String userRole;
    private String rtIdx;
    private String userGroupCode;
    private String userLicenseOwnYn;
    private String userPayRegisterYn;
    private String corporationIdx;
    private String userPushAgreeYn;
    private String joinChannel;
    private String socialLoginPath;
    private String socialLoginEmail;
    private String userWithDrawDate;
    private String regDt;
    private String regId;
    private String modDt;
    private String modId;
    private String loginFailCount;
}
