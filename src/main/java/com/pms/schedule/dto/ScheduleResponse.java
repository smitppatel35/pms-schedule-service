package com.pms.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponse {

    private String representativeName;
    private String doctorName;
    private String treatingAilment;
    private String medicines;
    private String slot;
    private String date;
    private String contactNumber;
}
