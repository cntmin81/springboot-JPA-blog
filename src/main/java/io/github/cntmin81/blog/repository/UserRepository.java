package io.github.cntmin81.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.cntmin81.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
