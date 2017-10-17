package com.cortez.samples.javaee7angular.data;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Table
 *
 */
@Entity
public class Table{

	/* Attributs */
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "table_id")
    @SequenceGenerator(name = "table_id", sequenceName = "id")
    private Long id;
	
	private static final long serialVersionUID = 1L;
	
	// Le numéro de table
	private int number;
	
	// Nombre de couverts restants disponibles
	private int available_places;
	
	// true si la table est déplacable 
	private boolean movable;
	
	
	/* Constructeurs */
	
	public Table() {
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

	public int getAvailable_places() {
		return available_places;
	}

	public void setAvailable_places(int available_places) {
		this.available_places = available_places;
	}

	public boolean isMovable() {
		return movable;
	}

	public void setMovable(boolean movable) {
		this.movable = movable;
	}
}
