package io.github.cntmin81.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import io.github.cntmin81.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
}
