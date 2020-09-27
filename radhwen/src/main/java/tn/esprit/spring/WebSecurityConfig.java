/*package tn.esprit.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tn.esprit.spring.entities.Membre;
import tn.esprit.spring.repository.MembreRepository;
import tn.esprit.spring.services.MembreService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	MembreService MB;
	@Autowired
	MembreRepository mr;

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	// TODO Auto-generated method stub
	List<Membre> mm=mr.findAll();
	for(Membre i:mm ){
	auth.inMemoryAuthentication().withUser(i.getMail()).password(i.getPassword()).roles("ADMIN");
	}
}
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST,"http://localhost:8081/SpringMVC/servlet/loginsession").permitAll();
    	 http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST,"http://localhost:8081/SpringMVC/servlet/upload1").permitAll();

    	 http
       
                .authorizeRequests()
                   
                    .antMatchers("/loginsignup").permitAll()
                    .antMatchers("/servlet/loginsession").permitAll()
                    .antMatchers("/servlet/upload1").permitAll();
    	 http
         .sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
         .sessionFixation().migrateSession()
      ;
    	 http
         .sessionManagement()
             .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
             .enableSessionUrlRewriting(true);
        
        
    }

   
}*/