package hello.upload.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hello.upload.domain.Item;
import hello.upload.domain.UploadFile;
import hello.upload.file.FileStore;
import hello.upload.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

	private final ItemRepository itemRepository;
	private final FileStore fileStore;

	@GetMapping("/items/new")
	public String newItem(@ModelAttribute ItemForm form){
		return "item-form";
	}

	@PostMapping("/items/new")
	public String saveItem(@ModelAttribute ItemForm form, RedirectAttributes redirectAttributes) throws IOException {

		MultipartFile attachFile = form.getAttachFile();
		UploadFile uploadFile = fileStore.storeFile(attachFile);

		List<MultipartFile> imageFiles = form.getImageFiles();
		List<UploadFile> uploadFiles = fileStore.storeFiles(imageFiles);

		//데이터베이스에 저장
		Item item = new Item();
		item.setItemName(form.getItemName());
		item.setAttachFile(uploadFile);
		item.setImageFiles(uploadFiles);
		itemRepository.save(item);

		redirectAttributes.addAttribute("itemId", item.getId());
		return "redirect:/items/{itemId}";
	}

	@GetMapping("/items/{id}")
	public String items(@PathVariable Long id, Model model){
		Item item = itemRepository.findById(id);
		model.addAttribute("item", item);
		return "item-view";
	}

	//이미지 파일 생성 -> 파일에 직접적으로 접근
	@ResponseBody
	@GetMapping("/images/{filename}")
	public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
		return new UrlResource("file:"+fileStore.getFullPath(filename));
	}

	//파일 다운로드
	@GetMapping("/attach/{itemId}")
	public ResponseEntity<Resource> downloadAttach(@PathVariable Long itemId) throws MalformedURLException {
		Item item = itemRepository.findById(itemId);
		String storeFileName = item.getAttachFile().getStoreFileName();
		String uploadFileName = item.getAttachFile().getUploadFileName();

		UrlResource urlResource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

		String contentDisposition = "attachment; filename=\"" + uploadFileName + "\"";

		return ResponseEntity.ok()
			.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
			.body(urlResource);
	}

}
