package com.pms.schedule.controller;

import com.pms.schedule.dto.ScheduleResponse;
import com.pms.schedule.exception.EmptyDateException;
import com.pms.schedule.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
public class ScheduleController {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/RepSchedule/{scheduleStartDate}")
    public ResponseEntity<?> getSchedule(@PathVariable String scheduleStartDate) throws ParseException, EmptyDateException {
        logger.info("[schedule-service] [/RepSchedule/{scheduleStartDate}] initiated");

        List<ScheduleResponse> schedule = scheduleService.getSchedule(scheduleStartDate);
        if (schedule.isEmpty()) {
            logger.info("[schedule-service] [/RepSchedule/{scheduleStartDate}] No Schedule Found.");
            return ResponseEntity.ok("No Schedule");
        } else {
            logger.info("[schedule-service] [/RepSchedule/{scheduleStartDate}] process completed");
            return ResponseEntity.ok(schedule);
        }
    }
}
