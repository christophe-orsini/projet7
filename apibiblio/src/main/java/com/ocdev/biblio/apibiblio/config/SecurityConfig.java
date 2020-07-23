package com.ocdev.biblio.apibiblio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
    UserDetailsService userDetailsService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
	        .authorizeRequests()
	        	.antMatchers("/swagger-ui.html").permitAll()
		        .antMatchers(HttpMethod.POST, "/api/v1/utilisateurs/**").permitAll()
		        .antMatchers(HttpMethod.GET, "/api/v1/themes/**").hasAnyRole("ABONNE, EMPLOYE, ADMINISTRATEUR") // TODO supprimer ABONNE
		        .antMatchers(HttpMethod.GET, "/api/v1/ouvrages/**").hasAnyRole("ABONNE, EMPLOYE, ADMINISTRATEUR")
		        .antMatchers(HttpMethod.POST, "/api/v1/ouvrages/**").hasAnyRole("ABONNE, EMPLOYE, ADMINISTRATEUR") // TODO supprimer ABONNE
		        .antMatchers(HttpMethod.GET, "/api/v1/prets/**").hasAnyRole("ABONNE, EMPLOYE, ADMINISTRATEUR")
		        .antMatchers(HttpMethod.PUT, "/api/v1/prets/prolonge/**").hasAnyRole("ABONNE, EMPLOYE, ADMINISTRATEUR")
		        .antMatchers(HttpMethod.PUT, "/api/v1/prets/abonne/**").hasAnyRole("ABONNE, EMPLOYE, ADMINISTRATEUR") // TODO supprimer ABONNE
		        .antMatchers(HttpMethod.PUT, "/api/v1/prets/retour/**").hasAnyRole("ABONNE, EMPLOYE, ADMINISTRATEUR") // TODO supprimer ABONNE
		        .anyRequest().hasAnyRole("ADMINISTRATEUR")
	        .and()
	        .httpBasic()
	        .and()
    		.formLogin().disable();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * Création du Manager d'Authentification 
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
