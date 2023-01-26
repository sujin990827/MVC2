package hello.upload.controller;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

//상품 저장용 폼
@Data
public class ItemForm {

	private Long itemId;
	private String itemName;
	private List<MultipartFile> imageFiles;
	private MultipartFile attachFile;

}
