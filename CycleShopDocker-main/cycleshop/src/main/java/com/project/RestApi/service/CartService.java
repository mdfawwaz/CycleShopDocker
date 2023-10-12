package com.project.RestApi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.RestApi.entity.CartItem;
import com.project.RestApi.entity.Cycle;
import com.project.RestApi.entity.Orders;
import com.project.RestApi.repository.CartRepository;
import com.project.RestApi.repository.CycleRepository;
import com.project.RestApi.repository.OrdersRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CycleRepository repo;

	@Autowired
	private OrdersRepository ordersRepository;

	
	public void addToCart(int id) {
		System.out.println("cartitem received");
		var currentCycleOptional = repo.findById(id);
		if (currentCycleOptional.isEmpty()) {
			System.out.println("Cycle could not be found");
			return; // Return early if the cycle is not found
		}
	
		var currentCycle = currentCycleOptional.get();
		Optional<CartItem> cartItem = cartRepository.findByName(currentCycle.getName());
		if(cartItem.isPresent()){
			cartItem.get().setQuantity(cartItem.get().getQuantity() + 1);
			cartItem.get().setTotal(cartItem.get().getQuantity() * currentCycle.getPrice());
			cartRepository.save(cartItem.get());
		}
		else{
			CartItem newcartItem = new CartItem();
			newcartItem.setName(currentCycle.getName());
			newcartItem.setQuantity(1);
			newcartItem.setTotal(currentCycle.getPrice());
			cartRepository.save(newcartItem);
		}

	}


	public List<CartItem> checkout(){
		List<CartItem> cartItems = cartRepository.findAll();
		
		for (CartItem cartItem : cartItems) {
			
			Optional<Cycle> cycle = repo.findByName(cartItem.getName());
			int quantity = cartItem.getQuantity();
			Orders order = new Orders();
			order.setName(cycle.get().getName());
			order.setQuantity(quantity);
			order.setTotalPrice(cycle.get().getPrice() * quantity);
			order.setOrderedAt(new Date());
			ordersRepository.save(order);
			cycle.get().setStock(cycle.get().getStock() - quantity);
			repo.save(cycle.get());
			cartRepository.delete(cartItem);
		}
		
		return cartRepository.findAll(); 
	}
	
}
	
