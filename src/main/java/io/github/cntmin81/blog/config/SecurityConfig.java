package io.github.cntmin81.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration // 빈등록
@EnableWebSecurity // 시큐리티필터를 추가하는것 = 스프링 시큐리티가 설정되어 있는데 어떤 설정을 이 파일에서 하겠다 시큐리티 필터가 등록된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을  미리 체크 하겠다는 듯
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean // IoC가 된다. 스프링이 관리한다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable() // csrf토큰 비활성화
		.authorizeRequests()
		.antMatchers("/","/auth/**","/js/**","/css/**","/image/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/auth/loginForm");
	}
}
