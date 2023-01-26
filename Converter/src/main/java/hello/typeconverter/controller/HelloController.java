package hello.typeconverter.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello-v1")
	public String helloV1(HttpServletRequest request){
		String data = request.getParameter("data"); //문자 타입으로 반환됨
		Integer intValue = Integer.valueOf(data); //숫자 타입으로 변환
		return "ok";
	}

	@GetMapping("/hello-v2")
	public String helloV2(@RequestParam Integer data){
		return "ok";
	}

}
