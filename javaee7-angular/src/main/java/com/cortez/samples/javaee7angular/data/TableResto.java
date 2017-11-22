package com.cortez.samples.javaee7angular.data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity implementation class for Entity: Table
 *
 */
@Entity
/*@JsonIgnoreProperties(value = { "restaurant" })*/
public class TableResto{

	/* Attributs */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	// Le numéro de table
	private int number;

	// Nombre de couverts de la table
	private int places;
	
	// true si la table est déplacable 
	private boolean movable;
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;
	
	/* Constructeurs */
	
	public TableResto() {
		super();
	}
	
	/* Getters/Setters */
   	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPlaces() {
		return places;
	}

	public void setPlaces(int places) {
		this.places = places;
	}

	public boolean isMovable() {
		return movable;
	}

	public void setMovable(boolean movable) {
		this.movable = movable;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
		if(!restaurant.getTables().contains(this)){
			restaurant.addTable(this);
		}
	}
}
