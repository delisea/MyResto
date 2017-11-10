package com.cortez.samples.javaee7angular.data;

import javax.persistence.*;

/**
 * Simple entity.
 *
 * @author Lucas Guerry
 */
@Entity
public class Disponibility {
	
    public enum Periode {
        MORNING, MIDDAY, EVENING
    }
    
    public enum Day {
    	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_dispo")
    @SequenceGenerator(name = "id_dispo", sequenceName = "id")
    private Long id;
    
    @Basic
	@Convert( converter=DayConverter.class )
    private Day day;
    
    @Basic
	@Convert( converter=PeriodeConverter.class )
    private Periode periode;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Disponibility(){}
    
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
	
	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	@Converter(autoApply=true)
	public static class DayConverter
			implements AttributeConverter<Day,String> {

		@Override
		public String convertToDatabaseColumn(Day value) {
			if ( value == null ) {
				return null;
			}

			return value.toString();
		}

		@Override
		public Day convertToEntityAttribute(String value) {
			if ( value == null ) {
				return null;
			}

			return Day.valueOf(value);
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