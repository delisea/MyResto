package com.cortez.samples.javaee7angular.data;

import javax.persistence.*;

/**
 * Simple entity.
 *
 * @author Roberto Cortez
 */
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id") // Attention : generator = name
    @SequenceGenerator(name = "person_id", sequenceName = "id") // Attention : sequenceName = nom de la variable
    private Long id;

    private String name;

    private String description;

    private String imageUrl;
    
    private String log;

    public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String link) {
        this.imageUrl = link;
    }
}
