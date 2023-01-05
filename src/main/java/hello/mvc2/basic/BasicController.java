package hello.mvc2.basic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Data;

@Controller
@RequestMapping("/basic")
public class BasicController {

	//text
	@GetMapping("/text-basic")
	public String textBasic(Model model){
		model.addAttribute("data", "hello spring");
		return "basic/text-basic";
	}

	//utext
	@GetMapping("text-unescaped")
	public String textUnescaped(Model model){
		model.addAttribute("data", "hello <b>Spring!!!</b>");
		return "basic/text-unescaped";
	}

	//변수
	@GetMapping("/variable")
	public String variable(Model model){
		User userA = new User("userA", 20);
		User userB = new User("userB", 40);

		List<User> list = new ArrayList<>();
		list.add(userA);
		list.add(userB);

		Map<String, User> map = new HashMap<>();
		map.put("userA", userA);
		map.put("userB", userB);

		model.addAttribute("user", userA);
		model.addAttribute("users", list);
		model.addAttribute("userMap", map);

		return "basic/variable";
	}

	//기본 객체들
	@GetMapping("/basic-objects")
	public String basicObjects(HttpSession session){
		session.setAttribute("sessionData", "Hello Session");
		return "basic/basic-objects";
	}

	@Component("helloBean")
	static class HelloBean{
		public String hello(String data){
			return "Hello " + data;
		}
	}

	//유틸리티 객체와 날짜
	@GetMapping("/date")
	public String date(Model model){
		model.addAttribute("localDateTime", LocalDateTime.now());
		return "basic/date";
	}

	//url
	@GetMapping("/link")
	public String link(Model model){
		model.addAttribute("param1", "data1");
		model.addAttribute("param2", "data2");
		return "basic/link";
	}

	//리터럴
	@GetMapping("/literal")
	public String literal(Model model){
		model.addAttribute("data", "Spring!!");
		return "basic/literal";
	}

	//연산
	@GetMapping("/operation")
	public String operation(Model model){
		model.addAttribute("nummData", null);
		model.addAttribute("data", "Spring!!");
		return "basic/operation";
	}

	//속성 값 설정
	@GetMapping("/attribute")
	public String attribute(){
		return "basic/attribute";
	}

	//반복
	@GetMapping("/each")
	public String each(Model model){
		addUsers(model);
		return "basic/each";
	}

	private void addUsers(Model model){
		List<User> list = new ArrayList<>();
		list.add(new User("UserA", 10));
		list.add(new User("UserB", 20));
		list.add(new User("UserC", 30));
		model.addAttribute("users", list);
	}

	//조건부 평가
	@GetMapping("/condition")
	public String condition(Model model){
		addUsers(model);
		return "basic/condition";
	}

	//주석
	@GetMapping("/comments")
	public String comments(Model model){
		model.addAttribute("data", "Spring!!");
		return "basic/comments";
	}

	//블록
	@GetMapping("/block")
	public String block(Model model){
		addUsers(model);
		return "basic/block";
	}

	//자바스크립트 인라인
	@GetMapping("/javascript")
	public String javascript(Model model) {
		model.addAttribute("user", new User("userA", 10));
		addUsers(model);
		return "basic/javascript";
	}
}
