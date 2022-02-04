package com.pms.schedule.service;

import com.pms.schedule.dto.ScheduleResponse;
import com.pms.schedule.exception.EmptyDateException;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ScheduleService {

    List<ScheduleResponse> getSchedule(String scheduleStartDate) throws ParseException, EmptyDateException;
}
