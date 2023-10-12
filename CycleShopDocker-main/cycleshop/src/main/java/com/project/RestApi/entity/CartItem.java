package com.project.RestApi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// @ManyToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "cycle_id")
	// private Cycle cycle;

	private String name;

	private int quantity;

	private int total;

}
