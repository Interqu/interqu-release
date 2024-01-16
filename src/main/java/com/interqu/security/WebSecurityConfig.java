package com.interqu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.interqu.user.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(){
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            String username = authentication.getName();
            String password = authentication.getCredentials().toString();
            System.out.println("Username entered by the user: " + username);
            System.out.println("Password entered by the user: " + password);

            UserDetails userDetails = userDetailsService().loadUserByUsername(username);
            if (!passwordEncoder().matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("Invalid password");
            }

            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        }
    };
		
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// TODO configure cors & secure the /api
		http.cors().disable();
		http.authorizeHttpRequests().requestMatchers("/**").permitAll();
//		http.headers().xssProtection();
//		 http
//            .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and().httpBasic().and().formLogin()
//      .loginPage("/login")
//      .defaultSuccessUrl("/user/interview-selection", false)
//      .failureUrl("/login?error=true")
//      .and()
//      .logout()
//      .logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/login")
//      .deleteCookies("JSESSIONID").and().authorizeHttpRequests().
//		requestMatchers("/api/**","register/**","/css/**","/js/**", "/img/**", "/dev/**", "/login","/").permitAll()
//		.and().authorizeHttpRequests().
//		requestMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
//		.and().authorizeHttpRequests()
//		.requestMatchers("/admin/**").hasAuthority("ADMIN");
		return http.build();
	}

}
