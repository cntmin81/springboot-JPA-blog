package io.github.cntmin81.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.github.cntmin81.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUsername(String username);

	// JPA naming query전략
	// SELECT * FROM user WHERE username = ?1 AND password = ?2의 SQL이 자동생상된다,
//	User findByUsernameAndPassword(String username, String password);
	
	// 위와 같은 일을 처리한다.
//	@Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//	User login(String username, String password);
}
