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
       
    @Basic
	@Convert( converter=PeriodeConverter.class )
    private Periode periode;
    
    public enum Periode {
        MORNING, MIDDAY, EVENING
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
	
	@Converter(autoApply=true)
	public static class PeriodeConverter
			implements AttributeConverter<Periode,String> {

		@Override
		public String convertToDatabaseColumn(Periode value) {
			if ( value == null ) {
				return null;
			}

			return value.toString();
		}

		@Override
		public Periode convertToEntityAttribute(String value) {
			if ( value == null ) {
				return null;
			}

			return Periode.valueOf(value);
		}
	}
	
}