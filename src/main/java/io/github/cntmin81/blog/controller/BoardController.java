package io.github.cntmin81.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	@GetMapping({ "", "/" })
	public String index() {
		return "index";
	}

}
