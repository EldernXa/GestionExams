package com.gestion.exams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.exams.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, String>{

}
