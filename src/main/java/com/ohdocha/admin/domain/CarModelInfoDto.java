package com.ohdocha.admin.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CarModelInfoDto extends DtoExtension{

    private String cdIdx;
    private String cdYear;
    private String modelName;
    private String modelDetailName;
    private String manufacturerCode;
    private String carTypeCode;
    private String fuelCode;
    private String transmissionCode;
    private String driveTypeCode;
    private String driveLicenseCode;
    private String maximumPassenger;
    private String displacement;

}
