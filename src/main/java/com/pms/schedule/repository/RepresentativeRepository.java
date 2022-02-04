package com.pms.schedule.repository;

import com.pms.schedule.dto.RepresentativeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "http://localhost:8085/auth", name = "auth-service")
public interface RepresentativeRepository {

    @GetMapping("/users")
    List<RepresentativeResponse> getAllRepresentatives();
}
