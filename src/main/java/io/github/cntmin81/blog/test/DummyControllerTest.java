package io.github.cntmin81.blog.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.cntmin81.blog.model.RoleType;
import io.github.cntmin81.blog.model.User;
import io.github.cntmin81.blog.repository.UserRepository;

// html파일이 아니라 data를 리턴해주는 컨트롤러
@RestController
public class DummyControllerTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/dummy/user/page")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) org.springframework.data.domain.Pageable pageable) {
		return userRepository.findAll(pageable).getContent();
	}
	
	@GetMapping("/dummy/user")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
				"해당유저는 없습니다 id : " + id));
//		User user = userRepository.findById(id).orElseGet(() -> new User());
		return user;
	}

	@PostMapping("/dummy/join")
	public String join(User user) {
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
