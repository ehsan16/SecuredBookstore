package hh.swd20.Bookstore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        .authorizeRequests().antMatchers("/css/**").permitAll() 
	        .and()
	        .authorizeRequests()
	        .antMatchers("/", "add", "save", "booklist", "delete/{id}").permitAll()
	          .anyRequest().authenticated()
	          .and()
	      .formLogin()
	          .loginPage("/login")
	          .defaultSuccessUrl("/booklist")
	          .permitAll()
	          .and()
	      .logout()
	          .permitAll();
	    }
	    

	    @Bean
	    @Override
	    public UserDetailsService userDetailsService() {
	        List<UserDetails> users = new ArrayList();
	    	UserDetails user = User.withDefaultPasswordEncoder()
	                .username("ehsan16")
	                .password("password")
	                .roles("USER")
	                .build();

	    	users.add(user);
	    	
	    	user = User.withDefaultPasswordEncoder()
	                   .username("Ehsan Haidari")
	                   .password("password")
	                   .roles("USER", "ADMIN")
	                   .build();
	    	
	    	users.add(user);
	    	
	        return new InMemoryUserDetailsManager(users);
	    }
}
