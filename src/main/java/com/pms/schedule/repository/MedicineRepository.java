package com.pms.schedule.repository;

import com.pms.schedule.dto.Medicines;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "${feign.stock-service-url}", name = "stock-service")
public interface MedicineRepository {

    @GetMapping("/MedicineStockInformation")
    List<Medicines> getAllMedicines();
}
