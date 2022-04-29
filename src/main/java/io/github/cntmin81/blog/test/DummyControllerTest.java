package io.github.cntmin81.blog.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.cntmin81.blog.model.RoleType;
import io.github.cntmin81.blog.model.User;
import io.github.cntmin81.blog.repository.UserRepository;

// html파일이 아니라 data를 리턴해주는 컨트롤러
@RestController
public class DummyControllerTest {

	@Autowired
	private UserRepository userRepository;

	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제실패. 해당 id : " + id + "는 존재하지 않습니다.";
		}

		return "삭제되었습니다. id : " + id;
	}

	// save함수는 id를 지정하지 않거나 id가 없으면 insert, id를 지정하면 update를 한다.
	@Transactional // 더티체킹 : 영속성 컨텍스트와 db의 변경을 감지해서 save()를 호출하지 않아도 DB를 update를 해준다.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다 id : " + id));
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		return user;
	}

	@GetMapping("/dummy/user/page")
	public Page<User> pageList(
			@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) org.springframework.data.domain.Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@GetMapping("/dummy/user")
	public List<User> list() {
		return userRepository.findAll();
	}

	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당유저는 없습니다 id : " + id));
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
