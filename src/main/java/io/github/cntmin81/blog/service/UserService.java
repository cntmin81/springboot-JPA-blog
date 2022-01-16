package io.github.cntmin81.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public int joinMember(User user) {
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UserService: joinMember(): " + e.getMessage());
		}
		return -1;
	}

}

