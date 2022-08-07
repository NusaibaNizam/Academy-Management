package com.bjit.nusaiba.final_project.config;

import java.util.Arrays;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@ComponentScan(value = { "com.bjit.nusaiba.backend_project.service" })
public class SecurityConfiguration {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

		@Autowired
		private JwtRequestFilter jwtRequestFilter;
		

		@Autowired
		private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.antMatcher("/api/**")
		.authorizeRequests().antMatchers("/authenticate","/refresh","/trainee_register","/trainer_register").permitAll()

        .antMatchers(HttpMethod.GET,"/api/trainees/**")
        .hasAnyAuthority("student","admin","trainer")
        .antMatchers(HttpMethod.PUT,"/api/trainees/**")
        .hasAnyAuthority("student","admin")
        .antMatchers(HttpMethod.DELETE,"/api/trainees/**")
        .hasAnyAuthority("admin")
        
        .antMatchers(HttpMethod.GET,"/api/trainers/**")
        .hasAnyAuthority("student","admin","trainer")
        .antMatchers(HttpMethod.PUT,"/api/trainers/**")
        .hasAnyAuthority("trainer","admin")
        .antMatchers(HttpMethod.DELETE,"/api/trainers/**")
        .hasAnyAuthority("admin")
        
        .antMatchers(HttpMethod.GET,"/api/course/**")
        .hasAnyAuthority("student","admin","trainer")
        .antMatchers(HttpMethod.POST,"/api/course/**")
        .hasAnyAuthority("admin")
        .antMatchers(HttpMethod.PUT,"/api/course/**")
        .hasAnyAuthority("admin")
        .antMatchers(HttpMethod.DELETE,"/api/course/**")
        .hasAnyAuthority("admin")
        
        .antMatchers(HttpMethod.GET,"/api/batch/**")
        .hasAnyAuthority("student","admin","trainer")
        .antMatchers(HttpMethod.POST,"/api/batch/**")
        .hasAnyAuthority("admin")
        .antMatchers(HttpMethod.PUT,"/api/batch/**")
        .hasAnyAuthority("admin")
        .antMatchers(HttpMethod.DELETE,"/api/batch/**")
        .hasAnyAuthority("admin")
        
        .antMatchers(HttpMethod.GET,"/api/record/**")
        .hasAnyAuthority("student","admin","trainer")
        .antMatchers(HttpMethod.POST,"/api/record/**")
        .hasAnyAuthority("admin")
        .antMatchers(HttpMethod.PUT,"/api/record/**")
        .hasAnyAuthority("admin")
        .antMatchers(HttpMethod.DELETE,"/api/record/**")
        .hasAnyAuthority("admin")
        
        .antMatchers(HttpMethod.GET,"/api/session/**")
        .hasAnyAuthority("student","admin","trainer")
        .antMatchers(HttpMethod.POST,"/api/session/**")
        .hasAnyAuthority("admin")
        .antMatchers(HttpMethod.PUT,"/api/session/**")
        .hasAnyAuthority("admin")
        .antMatchers(HttpMethod.DELETE,"/api/session/**")
        .hasAnyAuthority("admin")
        
        .antMatchers(HttpMethod.GET,"/api/assignment/**")
        .hasAnyAuthority("student","trainer")
        .antMatchers(HttpMethod.POST,"/api/assignment/**")
        .hasAnyAuthority("trainer")
        .antMatchers(HttpMethod.PUT,"/api/assignment/**")
        .hasAnyAuthority("trainer")
        .antMatchers(HttpMethod.DELETE,"/api/assignment/**")
        .hasAnyAuthority("trainer")
        
        .antMatchers(HttpMethod.GET,"/api/answer_copy/**")
        .hasAnyAuthority("student","trainer")
        .antMatchers(HttpMethod.POST,"/api/answer_copy/**")
        .hasAnyAuthority("student")
        .antMatchers(HttpMethod.PUT,"/api/assignment/**")
        .hasAnyAuthority("student","trainer")
        .antMatchers(HttpMethod.DELETE,"/api/assignment/**")
        .hasAnyAuthority("trainer")
        
        .anyRequest().authenticated().and()
        .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);   
		return http.build();
	}

	@Bean
	public FilterRegistrationBean<Filter> corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:3000");
		config.setAllowedMethods(Arrays.asList("POST", "OPTIONS", "GET", "DELETE", "PUT"));
		config.setAllowedHeaders(
				Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<Filter>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
