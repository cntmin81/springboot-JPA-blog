package io.github.cntmin81.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.cntmin81.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 컴트롤러에서 세션을 어떻게 찾는지?
	// public String index(@AuthenticationPrincipal PrincipalDetail principal) {
	@GetMapping({ "", "/" })
	public String index(Model model) {
		// System.out.println("로그인 사용자 아이디 : " + principal.getUsername());
		model.addAttribute("boards", boardService.getAllList());
		return "index";
	}

	// USER권한이필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}

}
