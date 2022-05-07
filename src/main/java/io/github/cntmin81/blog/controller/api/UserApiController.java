package io.github.cntmin81.blog.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.cntmin81.blog.dto.ResponseDto;
import io.github.cntmin81.blog.model.RoleType;
import io.github.cntmin81.blog.model.User;
import io.github.cntmin81.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		userService.joinMember(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@PostMapping("/user/update")
	public ResponseDto<Integer> update(@RequestBody User user) {
		userService.updateMember(user);
		// service를 실행시켜서 트랜잭션 완료후 디비 값은 갱신되지만 세션은 변경되지 않은 상태이기때문에
		// 여기서 세션을 직접 변경해줌
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//		System.out.println("UserAPIController login called");
//		User principal = userService.login(user); // principal란? 접근주체
//		if (principal != null) {
//			session.setAttribute("principal", principal);
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}
}
