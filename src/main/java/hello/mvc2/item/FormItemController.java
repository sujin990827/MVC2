package hello.mvc2.item;

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

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class FormItemController {

	private final ItemRepository itemRepository;

	@ModelAttribute("regions")
	public Map<String, String> regions(){
		Map<String, String> regions = new LinkedHashMap<>(); //순서가 보장됨
		regions.put("SEOUL", "서울");
		regions.put("BUSAN", "부산");
		regions.put("JEJU", "제주");
		return regions;
	}

	@ModelAttribute("deliveryCodes")
	public List<DeliveryCode> deliveryCodes() {
		List<DeliveryCode> deliveryCodes = new ArrayList<>();
		deliveryCodes.add(new DeliveryCode("FAST", "빠른 배송"));
		deliveryCodes.add(new DeliveryCode("NORMAL", "일반 배송"));
		deliveryCodes.add(new DeliveryCode("SLOW", "느린 배송"));

		return deliveryCodes;
	}

	@ModelAttribute("itemTypes")
	public ItemType[] itemTypes(){
		ItemType[] values = ItemType.values();
		return values;
	}

	@GetMapping
	public String items(Model model){
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		return "form/items";
	}

	//테스트용 아이템 추가
	@PostConstruct
	public void init(){
		itemRepository.save(new Item("itemaA", 1000, 10));
		itemRepository.save(new Item("itemaB", 2000, 20));

	}

	//상품 상세
	@GetMapping("/{itemId}")
	public String item(@PathVariable long itemId, Model model){
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "form/item";
	}

	// @GetMapping("/add")
	// public String addForm(){
	// 	return "form/addForm";
	// }

	@GetMapping("/add")
	public String addForm(Model model){
		model.addAttribute("item", new Item());
		return "form/addForm";
	}

	@PostMapping("/add")
	public String addItemV4(Item item, RedirectAttributes redirectAttributes) {
		Item savedItem = itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", savedItem.getId());
		redirectAttributes.addAttribute("status", true);
		return "redirect:/basic/items/{itemId}";
	}

	//상품 수정 -> 상품 조회랑 비슷
	@GetMapping("/{itemId}/edit")
	public String editForm(@PathVariable Long itemId, Model model){
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "form/editForm";
	}

	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
		itemRepository.update(itemId,item);
		return "redirect:/basic/items/{itemId}";
	}

}
