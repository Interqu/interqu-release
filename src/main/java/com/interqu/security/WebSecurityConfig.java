package com.interqu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.interqu.user.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {

	@Autowired
	private CustomUserDetailsService myUserDetailsService;

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// TODO configure csrf
		http.csrf().disable();
		http.authorizeHttpRequests().requestMatchers("/register", "/login", "/dev/interview", "/css/**", "/js/**").permitAll();
		http.authorizeHttpRequests().requestMatchers("/user/**")
				.authenticated();
		http.httpBasic();
		// http.authorizeHttpRequests().requestMatchers("/dev/interview").permitAll();
		// http.logout().invalidateHttpSession(true).deleteCookies("JSESSION", "jwt");
		// http.authorizeHttpRequests().requestMatchers("/test").permitAll();
		return http.build();
	}

}
