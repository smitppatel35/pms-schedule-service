package com.pms.schedule.config;

import com.pms.schedule.entity.Doctor;
import com.pms.schedule.repository.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class DBInit {

    @Autowired
    private DoctorsRepository doctorsRepository;

    @PostConstruct
    void init() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:doctors.csv");
        Scanner sc =  new Scanner(file);

        while(sc.hasNext())
        {
            String line = sc.next();
            String[] data = line.split(",");

            try{
                Doctor doctor = new Doctor();
                doctor.setDoctorId(Integer.parseInt(data[0]));
                doctor.setDoctorName(data[1]);
                doctor.setContactNumber(data[2]);
                doctor.setTreatingAilment(data[3]);
                doctor.setSlotTiming(data[4]);

                doctorsRepository.save(doctor);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }


        }
    }
}
