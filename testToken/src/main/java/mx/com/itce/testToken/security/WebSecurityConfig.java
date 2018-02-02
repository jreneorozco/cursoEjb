package mx.com.itce.testToken.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;// este datasource esta en el archivo application.properties
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	
	
	
	
	@Override
	protected void configure(HttpSecurity http)throws Exception
	{
		http.csrf().disable().authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .anyRequest().authenticated()
        .and()
        // We filter the api/login requests
        .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
        // And filter other requests to check the presence of JWT in header
        .addFilterBefore(new JWTAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
		
	}

	
	 @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    // Create a default account
	    auth.inMemoryAuthentication()
	        .withUser("admin")
	        .password("password")
	        .roles("ADMIN");
	  
	   
	  }
//	 @Override
//	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		 // Create a default account
//		 auth.jdbcAuthentication()
//		 		.usersByUsernameQuery(usersQuery)
//		 		.authoritiesByUsernameQuery(rolesQuery)
//		 		.dataSource(dataSource);
//		 		
//	 }
	
}
