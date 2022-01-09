package com.gestion.exams.services;

import com.gestion.exams.entity.Exam;
import com.gestion.exams.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    ExamRepository examRepository;

    public Optional<Exam> getExamById(long idExam){
        return examRepository.findById(idExam);
    }

}
