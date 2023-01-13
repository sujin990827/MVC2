package hello.exception.servlet;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ErrorPageController {

	@RequestMapping("/error-page/404")
	public String errorPage404(HttpServletResponse response){
		return "error-page/404";
	}

	@RequestMapping("/error-page/500")
	public String errorPage500(HttpServletResponse response){
		return "error-page/500";
	}

}
