package com.gestion.exams.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.exams.entity.UE;
import com.gestion.exams.repository.ExamRepository;
import com.gestion.exams.repository.GradeRepository;
import com.gestion.exams.repository.UERepository;

@Service
public class UEService {

	@Autowired
	UERepository ueRepository;
	@Autowired
	ExamRepository examRepository;
	@Autowired
	GradeRepository gradeRepository;

	@Transactional
	public UE createUE(UE ue){
		return ueRepository.save(ue);
	}

	@Transactional
	public UE updateUE(UE ue, String name){
		UE ue1 = ueRepository.getUEByName(name);
		ue1.setName(ue.getName());
		ue1.setCredit(ue.getCredit());
		ue1.setDurationExam(ue.getDurationExam());
		ue1.setDiscipline(ue.getDiscipline());
		return ueRepository.save(ue1);
	}

	@Transactional
	public void deleteUE(String name){
		UE ue = ueRepository.getUEByName(name);
		ueRepository.delete(ue);
	}


	public List<UE> getAllUE(){
		return ueRepository.findAll();
	}

	public List<String> getListUeName(){
		List<String> listUeName = new ArrayList<>();
		for(UE ue : ueRepository.findAll()) {
			listUeName.add(ue.getName());
		}
		return listUeName;
	}

	public UE getUeByName(String name){
		return ueRepository.getUEByName(name);
	}

}
