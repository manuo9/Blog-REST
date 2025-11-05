package com.spring.nscl.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "crop")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Crop extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private Long cropGroupId;
    private String cropGroupName;
    private String recurrenceOfCheck;
    private String lotSize;
    private String notionValue;

}
