package com.ohdocha.admin.domain;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class MemberActionHisDto extends DtoExtension {

    private int seq;
    private String UrIdx;
    private String PageUrl;
    private int Lat;
    private int Lng;
    private LocalDateTime RegDt;

}
