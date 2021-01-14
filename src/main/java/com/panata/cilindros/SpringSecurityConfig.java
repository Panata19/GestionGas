package com.panata.cilindros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@EnableWebSecurity 
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 		
	}
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {		
		
		
		 BCryptPasswordEncoder encoder = passwordEncoder();
		
		 UserBuilder users = User.builder().passwordEncoder(password -> {
			 return encoder.encode(password); 
		 });
		
		
		builder.inMemoryAuthentication()
			.withUser(users.username("leonardo").password("leo.1975").roles("ADMIN"))	;		
					
	}
	
	
	@Override //Autorization
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/css/**","/img/**","/js/**","/scss/**","/vendor/**").permitAll()
			.antMatchers("/diario/**","/eventual/**","/prestado/**").hasAnyRole("ADMIN")
			.antMatchers("/h2-console/**").hasAnyRole("ADMIN")
			.anyRequest().authenticated()
			.and().
			formLogin()
			.loginPage("/login")
			.permitAll()
			.and()
			.csrf().ignoringAntMatchers("/h2-console/**")
		.and()
			.headers().frameOptions().sameOrigin();;			
						
		}
	
	
	
	
}
	
	
