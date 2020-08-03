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
@Table(name = "CDT_USER_ACTION_HIS")
@ToString
public class MemberActionHis extends EntityExtension {

    @Id
    @Column(nullable = false, unique = true, name = "SEQ")
    private int seq;
    @Column(name = "UR_IDX")
    private String UrIdx;
    @Column(name = "PAGE_URL")
    private String PageUrl;
    @Column(name = "LAT")
    private int Lat;
    @Column(name = "LNG")
    private int Lng;
    @Column(name = "REG_DT")
    private LocalDateTime RegDt;
}
