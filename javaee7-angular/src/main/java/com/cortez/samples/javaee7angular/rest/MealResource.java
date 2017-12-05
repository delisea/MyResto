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

import com.cortez.samples.javaee7angular.data.Meal;
import com.cortez.samples.javaee7angular.data.Menu;
@Stateless
@ApplicationPath("/resources")
@Path("/meal")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MealResource extends Application{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@GET
	@Path("{id}")
	public Response getMeal(@PathParam("id") Long id) {
		Meal meal = null;
		try {
			meal = entityManager.find(Meal.class, id);
			if(meal == null){
				return Response.status(Response.Status.NOT_FOUND)
						.entity("le plat d'id : "+id+" est introuvable.")
						.build();
			}
		} catch (Exception e) {			
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(getExceptionMessage(e))
					.build();
		}
		return Response.ok(meal).build();
	}
	
	@GET
	public Response getMeals() {
		String queryString = "SELECT distinct m.id, m.name FROM Meal m";
		Query query = entityManager.createQuery(queryString);
		return Response.ok(query.getResultList()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	}
	
	
	@POST
	public Response saveMeal(@HeaderParam("Menu_id") Long menu_id, Meal meal) {
		Menu existingMenu = entityManager.find(Menu.class, menu_id);
		if (existingMenu == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("le Menu d'id : "+menu_id+" est introuvable.").build();
		}
		
		Response response = getMeal(meal.getId());		
		Meal existingMeal = (response.getStatus() == Response.Status.OK.getStatusCode()) ? (Meal) response.getEntity() : null;
        if (existingMeal == null) { // Ajout
        	Meal mealToSave = new Meal();
        	mealToSave.setType(meal.getType());  
        	mealToSave.setMenu(existingMenu);
        	mealToSave.setPrice(meal.getPrice());
        	mealToSave.setName(meal.getName());
        	
            try{
            	entityManager.persist(mealToSave);
            }catch(Exception e){            
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    					.entity(getExceptionMessage(e))
    					.build();
            }
            return Response.ok(mealToSave).build();
            
        } else { // Modif
        	existingMeal.setType(meal.getType());  
        	existingMeal.setMenu(existingMenu);
        	existingMeal.setPrice(meal.getPrice());
        	existingMeal.setName(meal.getName());
        	try{
        		return Response.ok(entityManager.merge(existingMeal)).build();
        	}catch(Exception e){
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    					.entity(getExceptionMessage(e))
    					.build();
        	}        
        }
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteMeal(@PathParam("id") Long id) {
		Response response = getMeal(id);
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