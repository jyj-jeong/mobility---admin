package com.ohdocha.admin.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class MemberCardDto extends DtoExtension {

    private String pmIdx;
    private String UrIdx;
    private String PgCode;
    private String CardNumber;
    private String BillingKey;
    private String RegDt;
    private String RegId;
    private String ModDt;
    private String ModId;
    private String UseYn;
}

