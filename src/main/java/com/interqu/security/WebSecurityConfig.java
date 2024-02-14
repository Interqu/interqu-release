package com.interqu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.interqu.jwt.JwtRequestFilter;
import com.interqu.preflight.PreFlightRequestFilter;
import com.interqu.user.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private PreFlightRequestFilter preFlightRequestFilter;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
	@Bean
	public CorsFilter corsFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedOrigin("http://localhost:4200");
	    config.addAllowedOrigin("https://interqu.com/");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
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
		// TODO configure cors & csrf
		http.csrf().disable();
		http.cors().disable();
		//configure authenticated endpoints
		http.authorizeHttpRequests().requestMatchers("/api/user/authenticate", "/api/user/register", "/dev/**", "/api/user/getInterviewResult").permitAll()
		.anyRequest().authenticated()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(preFlightRequestFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterAfter(jwtRequestFilter, PreFlightRequestFilter.class);
		return http.build();
	}

}
