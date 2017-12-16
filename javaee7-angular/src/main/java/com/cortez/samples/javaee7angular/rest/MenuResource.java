package com.cortez.samples.javaee7angular.rest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cortez.samples.javaee7angular.data.Menu;
import com.cortez.samples.javaee7angular.data.Restaurant;
@Stateless
@ApplicationPath("/resources")
@Path("menu")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MenuResource extends Application{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@GET
	@Path("{id}")
	public Response getMenu(@PathParam("id") Long id) {
		Menu menu = null;
		try {
			menu = entityManager.find(Menu.class, id);
			if(menu == null){
				return Response.status(Response.Status.NOT_FOUND)
						.entity("le Menu d'id : "+id+" est introuvable.")
						.build();
			}
		} catch (Exception e) {			
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(getExceptionMessage(e))
					.build();
		}
		return Response.ok(menu).build();
	}
	
	@GET
	public Response getMenus() {
		String queryString = "SELECT distinct m.name FROM Menu m";
		Query query = entityManager.createQuery(queryString);
		return Response.ok(query.getResultList()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	}
	
	@GET
	@Path("/getMenusByRestaurantId")
	public Response getMenusByRestaurantId(@QueryParam("restaurant_id") Long restaurant_id){
		Restaurant existingRestaurant = entityManager.find(Restaurant.class, restaurant_id);
		if (existingRestaurant == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("le Restaurant d'id : "+restaurant_id+" est introuvable.").build();
		}
		
		List<Menu> menus = null;
		String queryString = ("SELECT distinct m FROM Menu m WHERE m.restaurant.id =" +  restaurant_id);
		Query query = entityManager.createQuery(queryString);
		menus = query.getResultList();
		return Response.status(Response.Status.OK).entity(menus).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();		
	}
	
	
	@POST
	public Response saveMenu(@HeaderParam("restaurant_id") Long restaurant_id, Menu menu) {
		Restaurant existingRestaurant = entityManager.find(Restaurant.class, restaurant_id);
		if (existingRestaurant == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("le Restaurant d'id : "+restaurant_id+" est introuvable.").build();
		}
		
		Response response = getMenu(menu.getId());		
		Menu existingMenu = (response.getStatus() == Response.Status.OK.getStatusCode()) ? (Menu) response.getEntity() : null;
        if (existingMenu == null) { // Ajout
        	Menu menuToSave = new Menu();
        	menuToSave.setName(menu.getName());  
        	menuToSave.setRestaurant(existingRestaurant);
        	menuToSave.setPrice(menu.getPrice());
            try{
            	entityManager.persist(menuToSave);
            }catch(Exception e){            
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    					.entity(getExceptionMessage(e))
    					.build();
            }
            return Response.ok(menuToSave).build();
            
        } else { // Modif
        	existingMenu.setName(menu.getName());  
        	existingMenu.setRestaurant(existingRestaurant);
        	existingMenu.setPrice(menu.getPrice());
        	try{
        		return Response.ok(entityManager.merge(existingMenu)).build();
        	}catch(Exception e){
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    					.entity(getExceptionMessage(e))
    					.build();
        	}        
        }
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteMenu(@PathParam("id") Long id) {
		Response response = getMenu(id);
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