package com.pms.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;

@SpringBootApplication
@EnableFeignClients
public class MedicalRepScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalRepScheduleApplication.class, args);
    }

    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("dd-MMM-yyyy");
    }
}
