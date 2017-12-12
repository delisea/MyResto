package com.cortez.samples.javaee7angular.data;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PERSON_TYPE", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("RESTAURANT_MANAGER")
public class RestaurantManager extends Person{

	public RestaurantManager(){
		super();
	}
	
}
