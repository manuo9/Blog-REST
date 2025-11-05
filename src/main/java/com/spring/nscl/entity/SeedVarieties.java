package com.spring.nscl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "tm_seed_varieties")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class SeedVarieties extends BaseEntity{

    @Serial
    private static final long serialVersionUID = -2591057399240356134L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String varietyShortName;
    private String seedVarietyName;
    private String hybrid;
    private double optimumYield;
    private double averageYield;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id", referencedColumnName = "ID")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Crop cropId;
    private Date notifyDt;
    private String notifyNo;
    private Date obseledDt;
    @OneToMany(mappedBy = "varietyId")
    @JsonIgnore
    private List<Seeds> seeds;
    @ManyToMany
    @JoinTable(
            name = "tl_seed_variety_states",
            joinColumns = @JoinColumn(name = "seed_variety_id"),
            inverseJoinColumns = @JoinColumn(name = "state_id")
    )
    //private Set<NscStates> availableStates = new HashSet<>();
    private Long maleVarietyId;
    private String maleVarietyName;
    private Long femaleVarietyId;
    private String femaleVarietyName;
    private Long restorerVarietyId;
    private String restorerVarietyName;
    private Long mouId;
    private String mouName;


}
