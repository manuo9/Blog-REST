package com.spring.nscl.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CropDto {


    private Long id;

    @NotBlank(message = "Seed crop name is required")
    private String seedCropName;

    private Character advancePayableFlag;
    private Character gunnyBagsRebateFlag;
    private BigDecimal gunnyBagRebateRate;
    private Character transRebateFlag;
    private Character guaranteeRequired;
    private Character advTagRequired;
    private BigDecimal minSampleQty;
    private BigDecimal testingCharge;
    private BigDecimal gotCharge;
    private Character fieldProcess;
    private String uomShortName;
    private String scheduleNo;
    private BigDecimal mulRatio;
    private BigDecimal wheatBasis;
    private String smr;
    private String hsnShortName;
    private String oldHsnShortName;
    private String inspectionCheckList;
    private String seasonName;

    @NotNull(message = "Crop group ID is required")
    private Long cropGroupId;

    private String cropGroupName;
    private String recurrenceOfCheck;
    private String lotSize;
    private String notionValue;

    private String status;
}

