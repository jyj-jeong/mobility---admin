package com.ohdocha.admin.domain.car.model;

import com.ohdocha.admin.domain.common.CommonRequestDto;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("carModelRequest")
public class DochaAdminCarModelRequest extends CommonRequestDto {

    private static final long serialVersionUID = 1L;

}
