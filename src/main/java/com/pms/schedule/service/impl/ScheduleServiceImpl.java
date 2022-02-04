package com.pms.schedule.service.impl;

import com.pms.schedule.dto.Medicines;
import com.pms.schedule.dto.RepresentativeResponse;
import com.pms.schedule.dto.ScheduleResponse;
import com.pms.schedule.entity.Doctor;
import com.pms.schedule.exception.EmptyDateException;
import com.pms.schedule.repository.DoctorsRepository;
import com.pms.schedule.repository.MedicineRepository;
import com.pms.schedule.repository.RepresentativeRepository;
import com.pms.schedule.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;

    private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Autowired
    private SimpleDateFormat simpleDateFormat;

    @Autowired
    private RepresentativeRepository representativeRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private DoctorsRepository doctorsRepository;

    /**
     * BUGS:
     *
     *  Exception Handler
     *  Swagger (Documentation)
     *  Loggers
     *  Test
     *  Comments
     *
     *
     * JWT-TOKEN Forwarding for Authorization-service as "/auth/users" is secured end-point
     * IF 10 doctors are in the db, current logic displaying only first 5 docs are repeating
     */

    @Override
    public List<ScheduleResponse> getSchedule(String scheduleStartDate) throws ParseException, EmptyDateException {
        logger.info("[schedule-service] [/RepSchedule/{scheduleStartDate}] Schedule start date received");

        if(scheduleStartDate.isEmpty()){
            throw new EmptyDateException("Date  must be not empty");
        }

        List<ScheduleResponse> scheduleList = new ArrayList<>();

        Date startDate = simpleDateFormat.parse(scheduleStartDate);

        logger.debug("[schedule-service] [/RepSchedule/{scheduleStartDate}] Representative retrieval operation started");
        List<RepresentativeResponse> representatives = representativeRepository.getAllRepresentatives();

        logger.debug("[schedule-service] [/RepSchedule/{scheduleStartDate}] Medicines retrieval operation started");
        List<Medicines> allMedicines = medicineRepository.getAllMedicines();

        List<Doctor> doctorList = new ArrayList<>();
        doctorsRepository.findAll().forEach(doctorList::add);

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);

        int days = 0;

        Date tempDate = startDate;

        while (days != 5) {
            if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {

                int repSize = representatives.size();

                ScheduleResponse scheduleResponse = new ScheduleResponse();

                scheduleResponse.setDate(simpleDateFormat.format(tempDate));
                scheduleResponse.setRepresentativeName(representatives.get(days % repSize).getUsername());
                scheduleResponse.setContactNumber(doctorList.get(days).getContactNumber());
                scheduleResponse.setDoctorName(doctorList.get(days).getDoctorName());

                // Static Data need to change
                StringBuilder med = new StringBuilder();
                for (Medicines medicines : allMedicines) {
                    if(medicines.getTargetAilments().equalsIgnoreCase(doctorList.get(days).getTreatingAilment())){
                        med.append(medicines.getMedicineName());
                    }
                }
                scheduleResponse.setMedicines(med.toString());

                scheduleResponse.setSlot(doctorList.get(days).getSlotTiming());
                scheduleResponse.setTreatingAilment(doctorList.get(days).getTreatingAilment());

                scheduleList.add(scheduleResponse);

                days++;
            }
            tempDate = new Date(tempDate.getTime() + MILLIS_IN_A_DAY);
            cal.setTime(tempDate);
        }
        logger.info("[schedule-service] [/RepSchedule/{scheduleStartDate}] schedule generated.");
        return scheduleList;
    }


}
