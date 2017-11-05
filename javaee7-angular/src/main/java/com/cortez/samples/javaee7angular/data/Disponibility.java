package com.cortez.samples.javaee7angular.data;

import javax.persistence.*;

/**
 * Simple entity.
 *
 * @author Lucas Guerry
 */
@Entity
public class Disponibility {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_dispo")
    @SequenceGenerator(name = "id_dispo", sequenceName = "id")
    private Long id;
    
    public Disponibility(){}
    
    public enum Periode {
        MORNING, MIDDAY, EVENING
    }
    
    private Periode periode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;
    
    @Enumerated(EnumType.STRING)
    public Periode getPeriode() {
        return periode;
    }
    
    public void setPeriode(Periode periode){
    	this.periode = periode;
    }
    
    public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
		if(!restaurant.getTables().contains(this)){
			restaurant.addDisponibility(this);
		}
	}
}