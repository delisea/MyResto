package com.cortez.samples.javaee7angular.data;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Speciality
 *
 */
@Entity
public class Speciality {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_speciality")
	@SequenceGenerator(name = "id_speciality", sequenceName = "id")
	private Long id;

	public Speciality() {}

    public enum Speciality_label {
        ITALIAN, CHINESE, JAPANESE
    }
    
    private Speciality_label speciality_label;

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
    public Speciality_label getSpeciality_label() {
		return speciality_label;
	}

	public void setSpeciality_label(Speciality_label speciality_label) {
		this.speciality_label = speciality_label;
	}

    
    public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
		if(!restaurant.getTables().contains(this)){
			restaurant.addSpeciality(this);
		}
	}
}
