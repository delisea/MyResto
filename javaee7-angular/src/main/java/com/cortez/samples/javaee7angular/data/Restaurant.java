package com.cortez.samples.javaee7angular.data;

import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Restaurant
 *
 */
@Entity
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_id")
	@SequenceGenerator(name = "restaurant_id", sequenceName = "id")
	private Long id;

	private String name;
	private String address;
	private double latitude;
	private double longitude;
	private String speciality;
	private String url_img;
	private String tel_number;
	private String email;

	@OneToMany(mappedBy = "restaurant")
	private List<TableResto> tables;

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

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getSpeciality() {
		return this.speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
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

	public void setTables(List<TableResto> tables) {
		this.tables = tables;
	}

}
