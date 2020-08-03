package com.ohdocha.admin.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "CDT_CAR_MODEL_INFO")
@ToString
public class CarModelInfo extends EntityExtension{

    @Id
    @Column(nullable = false, unique = true)
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
