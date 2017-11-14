package com.cortez.samples.javaee7angular.data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity implementation class for Entity: Restaurant
 *
 */
@Entity
@JsonIgnoreProperties(value = { "tables","disponibilities","specialities" })
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String address;
	private String url_img;
	private String tel_number;
	private String email;

	@OneToMany(mappedBy = "restaurant")	
	private List<TableResto> tables;
	
	@OneToMany(mappedBy = "restaurant")	
	private List<Disponibility> disponibilities;
	
	@OneToMany(mappedBy = "restaurant")	
	private List<Speciality> specialities;

	public Restaurant() {
		super();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUrl_img() {
		return this.url_img;
	}

	public void setUrl_img(String url_img) {
		this.url_img = url_img;
	}

	public String getTel_number() {
		return this.tel_number;
	}

	public void setTel_number(String tel_number) {
		this.tel_number = tel_number;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void addTable(TableResto table) {
		this.tables.add(table);
		if (table.getRestaurant() != this) {
			table.setRestaurant(this);
		}
	}

	public List<TableResto> getTables() {
		return tables;
	}
	
	public boolean hasTables(){
		return !tables.isEmpty();
	}

	public void setTables(List<TableResto> tables) {
		this.tables = tables;
	}

	public void addDisponibility(Disponibility dispo) {
		if(!this.disponibilities.contains(dispo))
			this.disponibilities.add(dispo);
		if (dispo.getRestaurant() != this) {
			dispo.setRestaurant(this);
		}
	}
	
	public List<Disponibility> getDisponibilities() {
		return disponibilities;
	}

	public void setDisponibilities(List<Disponibility> disponibilities) {
		this.disponibilities = disponibilities;
	}
	
	public boolean hasDisponibility(){
		return !disponibilities.isEmpty();
	}

	public List<Speciality> getSpecialities() {
		return specialities;
	}

	public void setSpecialities(List<Speciality> specialities) {
		this.specialities = specialities;
	}

	public boolean hasSpecialities(){
		return !specialities.isEmpty();
	}
	
	public void addSpeciality(Speciality speciality) {
		if(!this.specialities.contains(speciality))
			this.specialities.add(speciality);
		if (speciality.getRestaurant() != this) {
			speciality.setRestaurant(this);
		}
	}
}
