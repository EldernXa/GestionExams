package com.gestion.exams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.exams.entity.UE;

@Repository
public interface UERepository extends JpaRepository<UE, String>{
    public UE getUEByName(String name);
    public UE deleteByName(String name);
}
