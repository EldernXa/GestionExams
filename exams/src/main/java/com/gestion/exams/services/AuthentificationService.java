package com.gestion.exams.services;

import com.gestion.exams.entity.Authentification;
import com.gestion.exams.repository.AuthentificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
@Slf4j
public class AuthentificationService implements UserDetailsService {

    @Autowired
    AuthentificationRepository authentificationRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Authentification authentification = authentificationRepository.findByEmail(email);
        if(authentification == null){
            throw new UsernameNotFoundException("user not found");
        }else {
            log.info("zebi is founf");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authentification.getRoles().forEach(role ->{authorities.add(new SimpleGrantedAuthority(role.getName()));});
        return new org.springframework.security.core.userdetails.User(authentification.getEmail(), authentification.getPassword(), authorities);
    }
}
