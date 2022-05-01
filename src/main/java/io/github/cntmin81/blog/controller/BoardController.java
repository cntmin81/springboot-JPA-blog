package io.github.cntmin81.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.github.cntmin81.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 컴트롤러에서 세션을 어떻게 찾는지?
	// public String index(@AuthenticationPrincipal PrincipalDetail principal) {
	@GetMapping({ "", "/" })
	public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Direction.DESC) org.springframework.data.domain.Pageable pageable) {
		// System.out.println("로그인 사용자 아이디 : " + principal.getUsername());
		model.addAttribute("boards", boardService.getAllList(pageable));
		return "index";
	}

	@GetMapping("/board/{id}")
	public String detail(Model model, @PathVariable("id") int id) {
		model.addAttribute("board", boardService.getOne(id));
		return "board/detail";
	}

	// USER권한이필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}

	@GetMapping("board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.getOne(id));
		return "board/updateForm";
	}

}
