package com.gestion.exams.dto.mapper;

import com.gestion.exams.dto.ExamDTO;
import com.gestion.exams.dto.GradeDTO;
import com.gestion.exams.entity.Exam;
import com.gestion.exams.entity.Grade;
import com.gestion.exams.repository.UERepository;
import com.gestion.exams.services.ExamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ExamMapper {

    private static final ModelMapper modelMapper = new ModelMapper() ;

    public static ExamDTO examToExamDTO(Exam exam) {
        String mstNotRoomYet = "Pas de salle pour l'instant";
        ExamDTO examDTO = modelMapper.map(exam, ExamDTO.class);
        examDTO.setUe(exam.getUe().getName());
        if(examDTO.getNameRoom() == null) {
            examDTO.setNameRoom(mstNotRoomYet);
        }
        return examDTO;
    }

    public Exam examDTOToExam(ExamDTO examDTO, ExamService examService) {
        return examService.getExamById(examDTO.getIdExam()).get();
    }
}
