package io.github.cntmin81.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


	@Transactional
	public void joinMember(User user) {
		userRepository.save(user);
	}

	@Transactional(readOnly = true) // select 할태 트랜젝션시작, 서비스 종료시에 트랜젝션 종료 (정합성)
	public User login(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}

}
