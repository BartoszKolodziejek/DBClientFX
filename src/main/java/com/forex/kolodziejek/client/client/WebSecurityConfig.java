package com.forex.kolodziejek.client.client;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    public UserDetailsService userDetailsService;
    
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/user/**", "/user").authenticated()
                .antMatchers("/api/admin/**", "/templates/admin/**", "/admin/**").hasAuthority("ADMIN")
                .anyRequest().permitAll().and()
                .formLogin().usernameParameter("username").passwordParameter("password").loginPage("/login").successHandler(new SimpleUrlAuthenticationSuccessHandler("/user")).permitAll().and()
                .logout().logoutUrl("/logout").deleteCookies("remember-me").logoutSuccessUrl("/").permitAll().and()
                .rememberMe()
                .tokenRepository(new JdbcTokenRepositoryImpl() {
                    {
                        setDataSource(dataSource);
                    }
                })
                .userDetailsService(new JdbcDaoImpl() {
                    {
                        JdbcTemplate template = new JdbcTemplate(dataSource);
                        setJdbcTemplate(template);
                        setUsersByUsernameQuery("select username, pass , enabled from users where username=? ");
                        setAuthoritiesByUsernameQuery("select username, roles.name from users join users_roles on users_roles.users_id=users.id\r\n" + 
                        		"join mydb.role on users_roles.role_id=roles.id where users.username =?");
                    }
                }).key("remember-me")
                .and()
                .authorizeRequests()
                .antMatchers("/user/updatePassword*",
                        "/user/savePassword*",
                        "/updatePassword*")
           .hasAuthority("USER");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select username, pass , enabled from users where username=? ")
                .authoritiesByUsernameQuery("select username, roles.name from users join users_roles on users_roles.users_id=users.id\r\n" + 
                		"join roles on users_roles.role_id=roles.id where users.username =?")
                .rolePrefix("");
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
