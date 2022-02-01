package com.gestion.exams.repository;

import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Role;
import com.gestion.exams.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role getRoleByName(String name);
}
