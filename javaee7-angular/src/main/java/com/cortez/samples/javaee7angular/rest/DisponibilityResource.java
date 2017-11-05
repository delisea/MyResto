package com.cortez.samples.javaee7angular.rest;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cortez.samples.javaee7angular.data.Disponibility;
import com.cortez.samples.javaee7angular.data.Restaurant;

@Stateless
@ApplicationPath("/resources")
@Path("disponibilities")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DisponibilityResource extends Application{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@GET
	@Path("{id}")
	public Response getDisponibility(@PathParam("id") Long id) {
		Disponibility dispo = null;
		try {
			dispo = entityManager.find(Disponibility.class, id);
			if(dispo == null){
				return Response.status(Response.Status.NOT_FOUND)
						.entity("la disponibilité d'id : "+id+" est introuvable.")
						.build();
			}
		} catch (Exception e) {			
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(getExceptionMessage(e))
					.build();
		}
		return Response.ok(dispo).build();
	}
	
	@POST
	public Response saveDisponibility(@HeaderParam("restaurant_id") Long restaurant_id, Disponibility dispo) {
		Restaurant existingRestaurant = entityManager.find(Restaurant.class, restaurant_id);
		if (existingRestaurant == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("le restaurant d'id : "+restaurant_id+" est introuvable.").build();
		}
		
		Response response = getDisponibility(dispo.getId());		
		Disponibility existingDispo = (response.getStatus() == Response.Status.OK.getStatusCode()) ? (Disponibility) response.getEntity() : null;
        if (existingDispo == null) { // Ajout
            Disponibility dispoToSave = new Disponibility();
            dispoToSave.setPeriode(dispo.getPeriode());
            dispoToSave.setRestaurant(existingRestaurant);
            try{
            	entityManager.persist(dispoToSave);
            }catch(Exception e){            
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    					.entity(getExceptionMessage(e))
    					.build();
            }
            return Response.ok(dispoToSave).build();
            
        } else { // Modif
        	existingDispo.setPeriode(dispo.getPeriode());  
        	existingDispo.setRestaurant(existingRestaurant);
        	try{
        		return Response.ok(entityManager.merge(existingDispo)).build();
        	}catch(Exception e){
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    					.entity(getExceptionMessage(e))
    					.build();
        	}        
        }
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteDisponibility(@PathParam("id") Long id) {
		Response response = getDisponibility(id);
		if(response.getStatus() != Response.Status.OK.getStatusCode()){
			return response;
		}
		try{
			entityManager.remove(response.getEntity());
		}catch(Exception e){			
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(getExceptionMessage(e))
					.build();
		}
		return Response.status(Response.Status.NO_CONTENT)
				.build();
	}
	
	
	private String getExceptionMessage(Exception e){
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		return e.getLocalizedMessage() + "/n" + errors.toString();
	}
}
