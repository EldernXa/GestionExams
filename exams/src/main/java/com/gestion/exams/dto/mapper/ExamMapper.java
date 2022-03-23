package com.gestion.exams.dto.mapper;
import com.gestion.exams.dto.ExamDTO;
import com.gestion.exams.entity.Exam;
import org.modelmapper.ModelMapper;

public class ExamMapper {

	private static final ModelMapper modelMapper = new ModelMapper() ;

	private ExamMapper(){}

	public static ExamDTO examToExamDTO(Exam exam) {
		String mstNotRoomYet = "Pas de salle pour l'instant";
		ExamDTO examDTO = modelMapper.map(exam, ExamDTO.class);
		examDTO.setUe(exam.getUe().getName());
		if(examDTO.getNameRoom() == null) {
			examDTO.setNameRoom(mstNotRoomYet);
		}
		return examDTO;
	}

}
