package com.project.RestApi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.project.RestApi.repository.CartRepository;
import com.project.RestApi.repository.CycleRepository;
import com.project.RestApi.repository.UserRepository;
import com.project.RestApi.service.CartService;
import com.project.RestApi.service.CycleService;
import com.project.RestApi.entity.CartItem;
import com.project.RestApi.entity.Cycle;
import com.project.RestApi.entity.User;

@CrossOrigin
@RestController
@RequestMapping("/cycle")
public class CycleController {

	@Autowired
	private CycleRepository cycleRepository;

	@Autowired
	private CycleService cycleService;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartService cartService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/cycles")
	@ResponseBody
	public List<Cycle> all() {
		return cycleRepository.findAll();
	}

	@PostMapping("/add")
	@ResponseBody
	public List<Cycle> newCycle(@RequestBody Cycle cycle) {
		cycleRepository.save(cycle);
		return cycleRepository.findAll();
	}

	@GetMapping("/borrow/{id}")
	@ResponseBody
	public List<Cycle> borrowCycle(@PathVariable("id") int id) {
		cycleService.borrow(id);
		return cycleRepository.findAll();
	}

	@GetMapping("/return/{id}")
	@ResponseBody
	public List<Cycle> returnCycle(@PathVariable("id") int id) {
		cycleService.returns(id);
		return cycleRepository.findAll();
	}

	@PostMapping("/restock/{id}")
	@ResponseBody
	public List<Cycle> restock(@PathVariable("id") int id, @RequestBody Map<String, Integer> reqBody) {
		int count = reqBody.get("qty");
		var cycle = cycleService.get(id);
		cycle.setStock(cycle.getStock() + count);
		cycleService.save(cycle);
		return cycleRepository.findAll();
	}

	@GetMapping("/delete/{id}")
	public String del(@PathVariable("id") int id) {
		cycleRepository.deleteById(id);
		return "Done";
	}

	@PostMapping("/addToCart/{id}")
	@ResponseBody
	public void addCycleToCart(@PathVariable("id") int id) {
		System.out.println("i reached here!");
		cartService.addToCart(id);
	}

	@GetMapping("/showCart")
	@ResponseBody
	public List<CartItem> listCartItems() {
		return cartRepository.findAll();
	}

	@PostMapping("/checkout")
	@ResponseBody
	public ResponseEntity<List<CartItem>> checkout() {

		return ResponseEntity.ok(cartService.checkout());
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		try {
			if (userRepository.existsByName(user.getName())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
			}
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return ResponseEntity.ok("User registered successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
		}

	}

	@GetMapping("/secured")

	public String secured(@AuthenticationPrincipal OAuth2User user) {

		var username = user.getAttributes().get("login");

		return String.format("Hello, %s, welcome to the secured site!", username);

	}
}
