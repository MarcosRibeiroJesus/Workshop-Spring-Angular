package com.mrj.opportunity.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Entity
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Min(0)
    private BigDecimal value;

    @Column
    @NotEmpty
    @Size(max = 80)
    private String prospectName;

    @Column

    @NotEmpty
    @Size(max = 200)
    private String description;
}
