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

import com.cortez.samples.javaee7angular.data.Restaurant;
import com.cortez.samples.javaee7angular.data.TableResto;

@Stateless
@ApplicationPath("/resources")
@Path("tables")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TableResource extends Application {

	@PersistenceContext
	private EntityManager entityManager;

	@GET
	@Path("{id}")
	public Response getTable(@PathParam("id") Long id) {
		TableResto table = null;
		try {
			table = entityManager.find(TableResto.class, id);
			if(table == null){
				return Response.status(Response.Status.NOT_FOUND)
						.entity("la table d'id : "+id+" est introuvable.")
						.build();
			}
		} catch (Exception e) {			
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(getExceptionMessage(e))
					.build();
		}
		return Response.ok(table).build();
	}

	/**
	 * @param table
	 * @return
	 */
	@POST
	public Response saveTable(@HeaderParam("restaurant_id") Long restaurant_id, TableResto table) {
		Restaurant existingRestaurant = entityManager.find(Restaurant.class, restaurant_id);
		if (existingRestaurant == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("le restaurant d'id : "+restaurant_id+" est introuvable.").build();
		}
		
		Response response = getTable(table.getId());
		TableResto existingTable = (response.getStatus() == Response.Status.OK.getStatusCode()) ? (TableResto) response.getEntity() : null;
        if (existingTable == null) { // Ajout
            TableResto tableToSave = new TableResto();
            tableToSave.setPlaces(table.getPlaces());
            tableToSave.setMovable(table.isMovable());
            tableToSave.setNumber(table.getNumber());    
            tableToSave.setRestaurant(existingRestaurant);
            try{
            	entityManager.persist(tableToSave);
            }catch(Exception e){            
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    					.entity(getExceptionMessage(e))
    					.build();
            }
            return Response.ok(tableToSave).build();
            
        } else { // Modif
        	existingTable.setPlaces(table.getPlaces());
        	existingTable.setMovable(table.isMovable());
        	existingTable.setNumber(table.getNumber());  
        	existingTable.setRestaurant(existingRestaurant);
        	try{
        		return Response.ok(entityManager.merge(existingTable)).build();
        	}catch(Exception e){
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    					.entity(getExceptionMessage(e))
    					.build();
        	}        
        }
	}

	@DELETE
	@Path("{id}")
	public Response deleteTable(@PathParam("id") Long id) {
		Response response = getTable(id);
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
