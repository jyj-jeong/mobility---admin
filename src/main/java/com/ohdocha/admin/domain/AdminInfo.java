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
@Table(name = "CDT_ADMIN_INFO")
@ToString
public class AdminInfo extends EntityExtension{

    @Id
    @Column(nullable = false, unique = true, name = "AD_IDX")
    private String adIdx;
    @Column(name = "ADMIN_ID")
    private String adminId;
    @Column(name = "ADMIN_PASSWORD")
    private String adminPassword;
    @Column(name = "ADMIN_NAME")
    private String adminName;
    @Column(name = "ADMIN_CONTACT1")
    private String adminContact1;
    @Column(name = "ADMIN_CONTACT2")
    private String adminContact2;
    @Column(name = "ADMIN_ROLE")
    private String adminRole;
    @Column(name = "RT_IDX")
    private String rtIdx;
    @Column(name = "REG_DT")
    private LocalDateTime regDt;
    @Column(name = "REG_ID")
    private String regId;
    @Column(name = "MOD_DT")
    private LocalDateTime modDt;
    @Column(name = "MOD_ID")
    private String modId;
    @Column(name = "DEL_YN")
    private boolean delYn;

}
