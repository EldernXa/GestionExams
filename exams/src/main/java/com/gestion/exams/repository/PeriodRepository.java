package com.gestion.exams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.exams.entity.Period;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long>{

}
