package com.gestion.exams.security;


import com.gestion.exams.entity.Authentification;
import com.gestion.exams.repository.AuthentificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class MyUserDetails  {


   /* @Autowired
    private AuthentificationRepository authentificationRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Authentification appUser = authentificationRepository.findById(email).orElseThrow(()->{

            throw new UsernameNotFoundException("User '" + email + "' not found");

        });

        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();


        appUser.getRoles().forEach(role -> {
            list.add(new SimpleGrantedAuthority("ROLE_" +role.getName()));

        });
        return org.springframework.security.core.userdetails.User//
                .withUsername(email)//
                .password(appUser.getPassword())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .authorities(list)//
                .build();
    }*/

}