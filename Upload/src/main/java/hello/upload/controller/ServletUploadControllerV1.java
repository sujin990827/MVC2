package hello.upload.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/servlet/v1")
public class ServletUploadControllerV1 {

	@GetMapping("/upload")
	public String newFile(){
		return "upload-form";
	}

	@PostMapping("/upload")
	public String saveFileV1(HttpServletRequest request) throws ServletException, IOException {
		String itemName = request.getParameter("itemName");
		Collection<Part> parts = request.getParts(); //나눠진 부분
		return "upload-form";
	}

}
