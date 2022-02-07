package com.pms.schedule.config;

import com.pms.schedule.entity.Doctor;
import com.pms.schedule.entity.Representative;
import com.pms.schedule.repository.DoctorsRepository;
import com.pms.schedule.repository.RepresentativeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Component
public class DBInit {

    private static final Logger logger = LoggerFactory.getLogger(DBInit.class);

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private RepresentativeRepository representativeRepository;

    @PostConstruct
    void initRep() {
        logger.info("[schedule-service] [db-init] representative data initialization start");
        Representative rep1 = new Representative();
        rep1.setRepresentativeName("R1");

        Representative rep2 = new Representative();
        rep2.setRepresentativeName("R2");

        representativeRepository.save(rep1);
        representativeRepository.save(rep2);

        logger.info("[schedule-service] [db-init] representative data initialization complete");
    }

    @PostConstruct
    void init() throws FileNotFoundException {
        logger.info("[schedule-service] [db-init] doctors data initialization start");

        File file = ResourceUtils.getFile("classpath:doctors.csv");
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String line = sc.next();
            String[] data = line.split(",");

            try {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(Integer.parseInt(data[0]));
                doctor.setDoctorName(data[1]);
                doctor.setContactNumber(data[2]);
                doctor.setTreatingAilment(data[3]);
                doctor.setSlotTiming(data[4]);

                doctorsRepository.save(doctor);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        logger.info("[schedule-service] [db-init] doctors data initialization complete");
    }
}
