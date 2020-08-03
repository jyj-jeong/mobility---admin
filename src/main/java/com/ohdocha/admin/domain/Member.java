package com.ohdocha.admin.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "CDT_USER_INFO")
@ToString
public class Member extends EntityExtension{

    @Id
    @Column(nullable = false, unique = true, name = "UR_IDX")
    private String urIdx;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "USER_PASSWORD")
    private String userPassword;
    @Column(name = "USER_STATUS_CODE")
    private String userStatusCode;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "USER_BIRTHDAY")
    private String userBirthday;
    @Column(name = "USER_CONTACT1")
    private String userContact1;
    @Column(name = "USER_CONTACT2")
    private String userContact2;
    @Column(name = "USER_GENDER")
    private String userGender;
    @Column(name = "USER_ZIPCODE")
    private String userZipcode;
    @Column(name = "USER_ADDRESS")
    private String userAddress;
    @Column(name = "USER_ADDRESS_DETAIL")
    private String userAddressDetail;
    @Column(name = "USER_IDENTITY_AUTH_YN")
    private String userIdentityAuthYn;
    @Column(name = "USER_IDENTITY_AUTH_DATE")
    private String userIdentityAuthDate;
    @Column(name = "USER_CI")
    private String userCi;
    @Column(name = "USER_DI")
    private String userDi;
    @Column(name = "USER_NATIONAL_CODE")
    private String userNationalCode;
    @Column(name = "USER_CERT_TYPE")
    private String userCertType;
    @Column(name = "USER_GRADE_CODE")
    private String userGradeCode;
    @Column(name = "USER_ROLE")
    private String userRole;
    @Column(name = "RT_IDX")
    private String rtIdx;
    @Column(name = "USER_GROUP_CODE")
    private String userGroupCode;
    @Column(name = "USER_LICENSE_OWN_YN")
    private boolean userLicenseOwnYn;
    @Column(name = "USER_PAY_REGISTER_YN")
    private boolean userPayRegisterYn;
    @Column(name = "CORPORATION_IDX")
    private String corporationIdx;
    @Column(name = "USER_PUSH_AGREE_YN")
    private boolean userPushAgreeYn;
    @Column(name = "JOIN_CHANNEL")
    private String joinChannel;
    @Column(name = "SOCIAL_LOGIN_PATH")
    private String socialLoginPath;
    @Column(name = "SOCIAL_LOGIN_EMAIL")
    private String socialLoginEmail;
    @Column(name = "USER_WITHDRAW_DATE")
    private String userWithDrawDate;
    @Column(name = "REG_DT")
    private LocalDateTime regDt;
    @Column(name = "REG_ID")
    private String regId;
    @Column(name = "MOD_DT")
    private LocalDateTime modDt;
    @Column(name = "MOD_ID")
    private String modId;
    @Column(name = "LOGIN_FAIL_COUNT")
    private int loginFailCount;
}
