package io.github.cntmin81.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.cntmin81.blog.model.User;
import io.github.cntmin81.blog.repository.UserRepository;

@Service // bean등록
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	// 스프링이 로그인을 요청을 가로챌때 유저네임 패스워드 변수 2개를 가로해는데
	// 패스워드 부분은 알아서 처리함
	// 유저네임이 디비에 있는지 확인해주면됨
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User princial = userRepository.findByUsername(username)
				.orElseThrow(() -> {
					return new UsernameNotFoundException("해당사용자를 찾을수 없습니다." + username);
				}); 
		return new PrincipalDetail(princial); //시큐리티의 세션에 유저정보가 저장된다.
	}

}
