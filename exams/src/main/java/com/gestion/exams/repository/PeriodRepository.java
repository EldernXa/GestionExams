package com.gestion.exams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.exams.entity.Period;

import java.util.List;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long>{

        List<Period> getPeriodByName(String name);

}
