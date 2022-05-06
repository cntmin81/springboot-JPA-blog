package io.github.cntmin81.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.cntmin81.blog.model.RoleType;
import io.github.cntmin81.blog.model.User;
import io.github.cntmin81.blog.repository.UserRepository;

//spring 컴포넌트스캔을 해서 bean에 등록해준다. IoC를 해줌
//서비스레이어가 필요한 이유 
//1. 트랜젝션 관리 
//2. 서비스의 의미떄문
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;


	@Transactional
	public void joinMember(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}

	@Transactional
    public void updateMember(User user) {
		// 유저를 셀렉트 하는 이유는 영속화 하기 위해서
		// 영속화 된 객체를 변경해주면 자동으로 디비에 업데이트된다.
		User persistence = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원정보 찾기 실패");
		});
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistence.setPassword(encPassword);
		persistence.setEmail(user.getEmail());
    }

//	@Transactional(readOnly = true) // select 할태 트랜젝션시작, 서비스 종료시에 트랜젝션 종료 (정합성)
//	public User login(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}

}
