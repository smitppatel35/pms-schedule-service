package com.pms.schedule.repository;

import com.pms.schedule.entity.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorsRepository extends CrudRepository<Doctor, Integer> {
}
