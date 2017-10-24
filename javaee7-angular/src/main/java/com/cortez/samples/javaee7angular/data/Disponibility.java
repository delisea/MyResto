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
    private Integer id;
    
    public Disponibility(){}
    
    public enum Periode {
        MORNING, MIDDAY, EVENING
    }
    
    private Periode periode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    public Periode getPeriode() {
        return periode;
    }
    
    public void setPeriode(Periode periode){
    	this.periode = periode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        Disponibility disponibility = (Disponibility) o;

        return id == disponibility.id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
