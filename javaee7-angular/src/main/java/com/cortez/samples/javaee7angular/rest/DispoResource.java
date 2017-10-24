package com.cortez.samples.javaee7angular.rest;

import com.cortez.samples.javaee7angular.data.Disponibility;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * REST Service to expose the data to display in the UI grid.
 *
 * @author Lucas Guerry
 */
@Stateless
@ApplicationPath("/resources")
@Path("disponibility")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DispoResource extends Application {
    @PersistenceContext
    private EntityManager entityManager;

    @GET
    @Path("{id}")
    public Disponibility getDisponibility(@PathParam("id") Integer id) {
    	return entityManager.find(Disponibility.class, id);
    }

    @POST
    public Disponibility saveDisponibility(Disponibility disponibility) {
        if (disponibility.getId() == null) {
            Disponibility disponibilityToSave = new Disponibility();
            disponibilityToSave.setPeriode(disponibility.getPeriode());
            entityManager.persist(disponibility);
        } else {
            Disponibility DisponibilityToUpdate = getDisponibility(disponibility.getId());
            DisponibilityToUpdate.setPeriode(disponibility.getPeriode());
            entityManager.persist(disponibility);
            disponibility = entityManager.merge(DisponibilityToUpdate);
        }

        return disponibility;
    }

    @DELETE
    @Path("{id}")
    public void deleteDisponibility(@PathParam("id") Integer id) {
        entityManager.remove(getDisponibility(id));
    }
}
