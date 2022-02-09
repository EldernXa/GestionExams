package com.gestion.exams.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.exams.entity.Authentification;
import com.gestion.exams.repository.AuthentificationRepository;

@Service
public class AuthentificationService {

	@Autowired
	private AuthentificationRepository authentificationRepo;

	public String getRole(String username, String mdp) {

		try {
			Authentification auth = authentificationRepo.getById(username);
			return new ArrayList<>(auth.getRoles()).get(0).getName();
		}catch(Exception exception) {
			return null;
		}
	}

}
