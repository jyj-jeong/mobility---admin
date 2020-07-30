package com.ohdocha.admin.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "CDT_USER_CARD_INFO")
@ToString
public class MemberCard extends EntityExtension {

    @Id
    @Column(nullable = false, unique = true, name = "PM_IDX")
    private String PM_IDX;
    @Column(name = "UR_IDX")
    private String UR_IDX;
    @Column(name = "PG_CODE")
    private String PG_CODE;
    @Column(name = "CARD_NUMBER")
    private String CARD_NUMBER;
    @Column(name = "BILLING_KEY")
    private String BILLING_KEY;
    @Column(name = "REG_DT")
    private String REG_DT;
    @Column(name = "REG_ID")
    private String REG_ID;
    @Column(name = "MOD_DT")
    private String MOD_DT;
    @Column(name = "MOD_ID")
    private String MOD_ID;
    @Column(name = "USE_YN")
    private String USE_YN;

}
