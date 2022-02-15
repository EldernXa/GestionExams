package com.gestion.exams.configuration;

import com.gestion.exams.filter.CustomAuthenticationFilter;
import com.gestion.exams.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;

	private  final BCryptPasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
		customAuthenticationFilter.setFilterProcessesUrl("/login");
		http.csrf().disable();
		http.cors();
		http.sessionManagement().sessionCreationPolicy(STATELESS);
		http.authorizeRequests().antMatchers("/login").permitAll();
		//http.authorizeRequests().antMatchers(GET, "/ue/allUE").hasAnyRole("ROlE_STUDENT");
		//http.authorizeRequests().antMatchers(POST, "/ue/**").hasAnyAuthority("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(customAuthenticationFilter);
		//http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
		http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public AuthenticationManager AuthenticationManagerBean()throws Exception{
		return super.authenticationManagerBean();

	}


}
