package com.pms.schedule.repository;

import com.pms.schedule.entity.Representative;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentativeRepository extends CrudRepository<Representative, Integer> {
}
