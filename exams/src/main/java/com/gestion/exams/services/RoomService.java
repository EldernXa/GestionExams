package com.gestion.exams.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Room;
import com.gestion.exams.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private PeriodService periodService;

	public Room getAvailableRoom(Date beginDate, Date endDate, long idPeriod) {
		// TODO use all exam of a period in place of all exam.
		for(Room room : roomRepository.findAll()) {
			int indExam;
			List<Exam> listExam = periodService.getPeriod(idPeriod).getExams();
			for(indExam = 0; indExam<listExam.size(); indExam++) {
				if(listExam.get(indExam).getRoom()!=null &&
						room.getName().compareTo(listExam.get(indExam).getRoom().getName())==0 &&
						(DateService.isBetweenDate(listExam.get(indExam).getBeginDateExam(), listExam.get(indExam).getEndDateExam(), beginDate) ||
								DateService.isBetweenDate(listExam.get(indExam).getBeginDateExam(), listExam.get(indExam).getEndDateExam(), endDate))) {
					break;
				}
			}

			if(indExam == listExam.size()) {
				return room;
			}
		}
		return null;
	}

}
