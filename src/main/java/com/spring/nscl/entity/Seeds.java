package com.spring.nscl.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "tm_seeds")
public class Seeds extends BaseEntity{

    @Serial
    private static final long serialVersionUID = -2591057399240356134L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String seedName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variety_id", referencedColumnName = "ID")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private SeedVarieties varietyId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "class_id", referencedColumnName = "ID")
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    private SeedClasses classId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id", referencedColumnName = "ID")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Crop cropId;
    private String stage;
    private Date notifyDate;
    private String hsnShortName;
}
