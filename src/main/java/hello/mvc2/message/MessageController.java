package hello.mvc2.message;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hello.mvc2.item.DeliveryCode;
import hello.mvc2.item.Item;
import hello.mvc2.item.ItemRepository;
import hello.mvc2.item.ItemType;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/message/items")
@RequiredArgsConstructor
public class MessageController {

	private final ItemRepository itemRepository;


	@GetMapping
	public String items(Model model){
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		return "message/items";
	}

	//상품 상세
	@GetMapping("/{itemId}")
	public String item(@PathVariable long itemId, Model model){
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "message/item";
	}


	//상품 등록
	// @GetMapping("/add")
	// public String addForm(){
	// 	return "message/addForm";
	// }

	@GetMapping("/add")
	public String addForm(Model model){
		model.addAttribute("item", new Item());
		return "message/addForm";
	}

	@PostMapping("/add")
	public String addItemV4(Item item, RedirectAttributes redirectAttributes) {
		Item savedItem = itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", savedItem.getId());
		redirectAttributes.addAttribute("status", true);
		return "redirect:/message/items/{itemId}";
	}

	//상품 수정 -> 상품 조회랑 비슷
	@GetMapping("/{itemId}/edit")
	public String editForm(@PathVariable Long itemId, Model model){
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "message/editForm";
	}

	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
		itemRepository.update(itemId,item);
		return "redirect:/message/items/{itemId}";
	}

}
