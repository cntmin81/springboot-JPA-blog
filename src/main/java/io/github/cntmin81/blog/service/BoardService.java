package io.github.cntmin81.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.cntmin81.blog.model.Board;
import io.github.cntmin81.blog.model.User;
import io.github.cntmin81.blog.repository.BoardRepository;

//spring 컴포넌트스캔을 해서 bean에 등록해준다. IoC를 해줌
//서비스레이어가 필요한 이유 
//1. 트랜젝션 관리 
//2. 서비스의 의미떄문
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void writeContent(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	public Page<Board> getAllList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

    public Board getOne(int id) {
        return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
		});
    }

	public void deleteById(int id) {
		boardRepository.deleteById(id);
	}

	// @Transactional(readOnly = true) // select 할태 트랜젝션시작, 서비스 종료시에 트랜젝션 종료 (정합성)
	// public User login(User user) {
	// return userRepository.findByUsernameAndPassword(user.getUsername(),
	// user.getPassword());
	// }

}
