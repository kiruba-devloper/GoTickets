package com.gotickets.GoTickets.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gotickets.GoTickets.Security.Jwt.JwtAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	MyUserDetailsService myUserDetailsService;
	 
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		return httpSecurity
				.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable() )
				//.authorizeHttpRequests(p -> p.anyRequest().permitAll())
				.authorizeHttpRequests(registry -> {
					registry.requestMatchers("/gotickets/user/**" ).permitAll();
					
					registry.requestMatchers("/admin/**").hasRole("ADMIN");
					
					registry.anyRequest().authenticated();
				})
			
				//.formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
//				 .logout(logout -> logout
//			                .logoutUrl("/logout")  // Define the logout URL
//			                .logoutSuccessUrl("/login?logout=true")  // Redirect to login page with logout=true on successful logout
//			                .invalidateHttpSession(true)  // Invalidate the session on logout
//			                .clearAuthentication(true)  // Clear authentication information
//			                .permitAll()  // Allow everyone to access the logout URL
//			            )
					.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)//.formLogin().disable()
					.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(myUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(authenticationProvider());
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return myUserDetailsService ;
	}
}
