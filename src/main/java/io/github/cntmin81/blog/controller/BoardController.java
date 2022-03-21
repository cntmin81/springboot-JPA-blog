package io.github.cntmin81.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.cntmin81.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {

	@GetMapping({ "", "/" })
//	public String index(@AuthenticationPrincipal PrincipalDetail principal) {
	public String index() {
//		System.out.println("로그인 사용자 아이디 : " + principal.getUsername());
		return "index";
	}
	
	//USER권한이필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}

}
