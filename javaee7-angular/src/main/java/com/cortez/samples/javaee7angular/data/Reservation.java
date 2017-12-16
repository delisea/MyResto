package com.cortez.samples.javaee7angular.data;

import java.sql.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Basic;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reservation {
	
	public enum Periode {
        MORNING, MIDDAY, EVENING
    }

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;
	
	@Basic
	@Convert( converter=PeriodeConverter.class )
	private Periode periode;
	
	private int nbCouverts;

	public Reservation(){}
	
	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Enumerated(EnumType.STRING)
	public Periode getPeriode() {
		return periode;
	}

	public void setPeriode(Periode periode) {
		this.periode = periode;
	}
	
	public int getNbCouverts() {
		return nbCouverts;
	}

	public void setNbCouverts(int nbCouvert) {
		this.nbCouverts = nbCouvert;
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
