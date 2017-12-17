package com.cortez.samples.javaee7angular.data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = { "meals" })
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private float price;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;
	
	@OneToMany(mappedBy = "menu")
	private List<Meal> meals;
	
	
	public Menu() {}

	public long getId(){
		return this.id;
	}
	public List<Meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}

	public Restaurant getRestaurant(){
		return this.restaurant;
	}
	
	public void setRestaurant(Restaurant r){
		restaurant = r;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
