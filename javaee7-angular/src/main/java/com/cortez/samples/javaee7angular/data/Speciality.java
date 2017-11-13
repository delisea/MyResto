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
    
	@Basic
	@Convert( converter=SpecialityConverter.class )
    private Speciality_label speciality_label;

    public enum Speciality_label {
        ITALIAN, CHINESE, JAPANESE
    }
    
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
	

	/*** CLASSES STATIQUES DE CONVERSION ENUM <---> STRING ***/

	/*
	 * Classe statique qui convertit l'enum Speciality en string et inversement 
	 * suivant si on fait un POST ou un GET
	 */
	@Converter(autoApply=true)
	public static class SpecialityConverter
			implements AttributeConverter<Speciality_label,String> {

		@Override
		public String convertToDatabaseColumn(Speciality_label value) {
			if ( value == null ) {
				return null;
			}

			return value.toString();
		}

		@Override
		public Speciality_label convertToEntityAttribute(String value) {
			if ( value == null ) {
				return null;
			}
			return Speciality_label.valueOf(value);
		}
	}
}
