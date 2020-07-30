package com.ohdocha.admin.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AdminInfoDto extends DtoExtension {

    private String adIdx;
    private String adminId;
    private String adminPassword;
    private String adminName;
    private String adminContact1;
    private String adminContact2;
    private String adminRole;
    private String rtIdx;
    private String regDt;
    private String regId;
    private String modDt;
    private String modId;
    private String delYn;

}
