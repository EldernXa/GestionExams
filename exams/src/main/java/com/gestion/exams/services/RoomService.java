package com.gestion.exams.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.exams.entity.Room;
import com.gestion.exams.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private ExamService examService;

	public Room getAvailableRoom(Date beginDate, Date endDate) {
		for(Room room : roomRepository.findAll()) {
			int indExam;
			for(indExam = 0; indExam<examService.getAllExams().size(); indExam++) {
				if(room.getName().compareTo(examService.getAllExams().get(indExam).getRoom().getName())==0) {
					break; // TODO continue here
				}
			}

			if(indExam == examService.getAllExams().size()) {
				return room;
			}
		}

		return null;
	}

}
