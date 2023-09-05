package com.study.springboot.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public AuthenticationFailureHandler authenticationFailureHandler;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .antMatchers("/guest/**").permitAll()
                .antMatchers("/member/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();
        
        http.formLogin()
        		.loginPage("/loginForm")				// default : /login
        		.loginProcessingUrl("/j_spring_security_check")
//        		.failureUrl("/loginError")				// default : /login?error
        		// .defaultSuccessUrl("/")
        		.failureHandler(authenticationFailureHandler)
        		.usernameParameter("j_username")		// default : j_username
        		.passwordParameter("j_password")		// default : j_password
                .permitAll();
 
        http.logout()
        		.logoutUrl("/logout")					// default
        		.logoutSuccessUrl("/") 
                .permitAll();
        // Spring Security의 메서드 체이닝 기법 메서드 호출의 순서가 중요합니다.
        
        // ssl을 사용하지 않으면 true로 사용
        http.csrf().disable();
    }
 
//  @Autowired
//  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//      auth.inMemoryAuthentication()
//          .withUser("user").password(passwordEncoder().encode("1234")).roles("USER")
//          .and()
//          .withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN");
//  }
  
    @Autowired
    private DataSource dataSource;
  
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	// 비밀번호를 얻기 위해 한 번 실행한 후 암호화된 비밀번호를 얻으면 삭제하거나 주석처리하기
    	System.out.println(passwordEncoder().encode("123"));
    	
    	// 사용자 인증을 하기 위해서 데이터베이스 접속정보를 먼저 이용한다.
    	auth.jdbcAuthentication()
    		.dataSource(dataSource)
    		// usersByUsernameQuery 쿼리로 해당 사용자가 있는지를 먼저 조회한다.
    		.usersByUsernameQuery("SELECT NAME AS USERNAME, PASSWORD, ENABLED"
    							  + " FROM USER_LIST WHERE NAME = ?")
    		// 사용자의 역할을 구해온다 (인증)
    		.authoritiesByUsernameQuery("SELECT NAME AS USERNAME, AUTHORITY "
    							  + " FROM USER_LIST WHERE NAME = ?")
    		// 입력한 비밀번호를 암호화해서 데이터 베이스의 암호와 비교를 해서 올바른 값인지 검증한다.
            .passwordEncoder(new BCryptPasswordEncoder());
    }
  
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
