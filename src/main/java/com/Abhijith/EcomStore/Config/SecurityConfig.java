package com.Abhijith.EcomStore.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Abhijith.EcomStore.Exceptions.CustomAccessDeniedHandler;
import com.Abhijith.EcomStore.Exceptions.CustomAuthenticationEntryPoint;
import com.Abhijith.EcomStore.Filter.JwtFilter;
import com.Abhijith.EcomStore.Service.CustomUserDetailService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

	private final CustomUserDetailService userDetailService;
	private final JwtFilter jwtFilter;
	private final CustomAuthenticationEntryPoint authenticationEntrypoint;
	private final CustomAccessDeniedHandler accessDeniedHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.exceptionHandling(e->e
					.accessDeniedHandler(accessDeniedHandler)
					.authenticationEntryPoint(authenticationEntrypoint))
			.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(r->r
					.requestMatchers("api/auth/login").permitAll()
					.requestMatchers("/api/admin/**").hasAuthority("ADMIN")
					.requestMatchers("/api/users/**").hasAuthority("USERS")
					.anyRequest().authenticated())
			
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
			.sessionManagement(s->
									   s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
			
			return http.build();
			
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    AuthenticationManager authenticationManager() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		return new ProviderManager(provider);
	}
	
}
