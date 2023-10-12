package com.project.RestApi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Cycle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private int stock;
	private int price;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	// @Override
	// public int hashCode() {
	// return Objects.hash(this.id, this.name, this.stock);
	// }
	//
	// @Override
	// public String toString() {
	// return "Cycle{" + "id=" + this.id + ", name='" + this.name + '\'' + ",
	// role='" + this.stock + '\'' + '}';
	// }

}
