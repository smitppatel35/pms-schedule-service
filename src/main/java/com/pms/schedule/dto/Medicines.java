package com.pms.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicines {

    private Integer id;
    private String medicineName;
    private String chemicalComposition;
    private Date dateOfExpiry;
    private Long numbersOfTabletsInStock;
    private String targetAilments;
}
