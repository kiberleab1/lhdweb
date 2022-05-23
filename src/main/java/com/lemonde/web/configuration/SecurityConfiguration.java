package com.lemonde.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/fonts/**", "/images/**", "../css/**", "../js/**",
				"/SpringUploads/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.requestMatcher(EndpointRequest.toAnyEndpoint());
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/lhd/**").permitAll()
			.antMatchers("/Admin/**").permitAll()
				.and()
					.formLogin()
						.loginPage("/login")
							.failureUrl("/login?error=true")
							.defaultSuccessUrl("/default")

				.and()
					.logout()
						.invalidateHttpSession(true)
						.clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/login?logout")
							.permitAll().and().exceptionHandling().accessDeniedPage("/AccessDenied");

	}

	/*
	 * @Bean public BCryptPasswordEncoder passwordEncoder(){ return new
	 * BCryptPasswordEncoder(); }
	 * 
	 * 
	 * @Bean public DaoAuthenticationProvider authenticationProvider(){
	 * DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
	 * auth.setUserDetailsService(userDetailService);
	 * auth.setPasswordEncoder(passwordEncoder()); return auth; }
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder);
	}

}
