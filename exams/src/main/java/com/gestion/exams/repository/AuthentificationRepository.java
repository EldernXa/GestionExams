package com.gestion.exams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.exams.entity.Authentification;

@Repository
public interface AuthentificationRepository extends JpaRepository<Authentification, String>{
    Authentification findByEmail(String email);
}
