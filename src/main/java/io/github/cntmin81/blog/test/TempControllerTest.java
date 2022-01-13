package io.github.cntmin81.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	@GetMapping("/temp/jsp")
	public String testJsp() {
		return "test";
	}
}
