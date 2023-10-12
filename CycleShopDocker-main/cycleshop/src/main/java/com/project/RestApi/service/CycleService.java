package com.project.RestApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.RestApi.entity.Cycle;
import com.project.RestApi.repository.CycleRepository;


@Service
public class CycleService {
	
		@Autowired
		private CycleRepository repo;
		
		public List<Cycle> listAll(){
			return repo.findAll();
		}
		
		public void save(Cycle cycle) {
			repo.save(cycle);
		}
		
		public Cycle get(int id) {
			return repo.findById(id).get();
		}
		
		public void delete(int id) {
			repo.deleteById(id);
		}
		
		public void borrow(int id) {
			var currentCycleOptional = repo.findById(id);
			if (currentCycleOptional.isEmpty()) {
				//TODO: deal with the cycle not being found for whatever reason
				System.out.println("cycle could not be found");
			}
			var currentCycle = currentCycleOptional.get();
			int currentstock = repo.findById(id).get().getStock();
			currentCycle.setStock(currentstock - 1);
			repo.save(currentCycle);
		}
		
		public void returns(int id) {
			var currentCycleOptional = repo.findById(id);
			if (currentCycleOptional.isEmpty()) {
				//TODO: deal with the cycle not being found for whatever reason
				System.out.println("cycle could not be found");
			}
			var currentCycle = currentCycleOptional.get();
			int currentstock = repo.findById(id).get().getStock();
			currentCycle.setStock(currentstock + 1);
			repo.save(currentCycle);
		}
}
