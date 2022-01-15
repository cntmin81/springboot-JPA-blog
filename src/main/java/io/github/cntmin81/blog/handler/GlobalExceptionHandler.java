package io.github.cntmin81.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 어플리케이션내 어디서든 발생한 것을 다 처리한다.
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public String HandleArgumentException(IllegalArgumentException e) {
		return e.getMessage();
	}
}
