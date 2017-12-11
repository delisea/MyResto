package com.cortez.samples.javaee7angular.data;

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

@Entity
public class Meal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	private float price;
	
//	@OneToOne
//	@JoinColumn(name = "typemeal_id")
//	private TypeMeal type;
	
	@Basic
	@Convert( converter=TypeMealConverter.class )
	private TypeMeal typeMeal;
	
	@ManyToOne
	@JoinColumn(name = "menu_id")
	private Menu menu;
	
	public Meal() {}
	
	public long getId() {
		return id;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Enumerated(EnumType.STRING)
	public TypeMeal getType() {
		return typeMeal;
	}

	public void setType(TypeMeal type) {
		this.typeMeal = type;
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
	
	public enum TypeMeal {
        STARTER, MAINCOURSE, DESERT
    }
	
	@Converter(autoApply=true)
	public static class TypeMealConverter
			implements AttributeConverter<TypeMeal ,String> {

		@Override
		public String convertToDatabaseColumn(TypeMeal value) {
			if ( value == null ) {
				return null;
			}

			return value.toString();
		}

		@Override
		public TypeMeal convertToEntityAttribute(String value) {
			if ( value == null ) {
				return null;
			}
			return TypeMeal.valueOf(value);
		}
	}
	
}
