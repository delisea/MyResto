package com.cortez.samples.javaee7angular.rest;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

import com.cortez.samples.javaee7angular.data.Restaurant;
import com.cortez.samples.javaee7angular.data.Speciality;

@Stateless
@ApplicationPath("/resources")
@Path("specialities")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SpecialityResource extends Application{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@GET
	@Path("{id}")
	public Response getSpeciality(@PathParam("id") Long id) {
		Speciality speciality = null;
		try {
			speciality = entityManager.find(Speciality.class, id);
			if(speciality == null){
				return Response.status(Response.Status.NOT_FOUND)
						.entity("la spécialité d'id : "+id+" est introuvable.")
						.build();
			}
		} catch (Exception e) {			
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(getExceptionMessage(e))
					.build();
		}
		return Response.ok(speciality).build();
	}
	
	@GET
	public Response getSpecialities() {
		String queryString = "SELECT distinct s.speciality_label FROM Speciality s";
		Query query = entityManager.createQuery(queryString);
		return Response.ok(query.getResultList()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	}
	
	
	@POST
	public Response saveSpeciality(@HeaderParam("restaurant_id") Long restaurant_id, Speciality speciality) {
		Restaurant existingRestaurant = entityManager.find(Restaurant.class, restaurant_id);
		if (existingRestaurant == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("le restaurant d'id : "+restaurant_id+" est introuvable.").build();
		}
		
		Response response = getSpeciality(speciality.getId());		
		Speciality existingSpeciality = (response.getStatus() == Response.Status.OK.getStatusCode()) ? (Speciality) response.getEntity() : null;
        if (existingSpeciality == null) { // Ajout
        	Speciality specialityToSave = new Speciality();
            specialityToSave.setSpeciality_label(speciality.getSpeciality_label());
            specialityToSave.setRestaurant(existingRestaurant);
            try{
            	entityManager.persist(specialityToSave);
            }catch(Exception e){            
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    					.entity(getExceptionMessage(e))
    					.build();
            }
            return Response.ok(specialityToSave).build();
            
        } else { // Modif
        	existingSpeciality.setSpeciality_label(speciality.getSpeciality_label());  
        	existingSpeciality.setRestaurant(existingRestaurant);
        	try{
        		return Response.ok(entityManager.merge(existingSpeciality)).build();
        	}catch(Exception e){
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    					.entity(getExceptionMessage(e))
    					.build();
        	}        
        }
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteSpeciality(@PathParam("id") Long id) {
		Response response = getSpeciality(id);
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
