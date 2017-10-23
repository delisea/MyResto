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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
						.entity(table)
						.build();
			}
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getLocalizedMessage()+"/n"+errors.toString())
					.build();
		}
		return Response.ok(table).build();
	}

	/**
	 * @param table
	 * @return
	 */
	@POST
	public Response saveTable(TableResto table) {
		TableResto existingTable = (TableResto) getTable(table.getId()).getEntity();
        if (existingTable == null) { // Ajout
            TableResto tableToSave = new TableResto();
            tableToSave.setAvailable_places(table.getAvailable_places());
            tableToSave.setMovable(table.isMovable());
            tableToSave.setNumber(table.getNumber());         
            try{
            	entityManager.persist(tableToSave);
            }catch(Exception e){
            	StringWriter errors = new StringWriter();
    			e.printStackTrace(new PrintWriter(errors));
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    					.entity(e.getLocalizedMessage()+"/n"+errors.toString())
    					.build();
            }
            return Response.ok(tableToSave).build();
            
        } else { // Modif
        	existingTable.setAvailable_places(table.getAvailable_places());
        	existingTable.setMovable(table.isMovable());
        	existingTable.setNumber(table.getNumber());  
        	try{
        		return Response.ok(entityManager.merge(existingTable)).build();
        	}catch(Exception e){
        		StringWriter errors = new StringWriter();
    			e.printStackTrace(new PrintWriter(errors));
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    					.entity(e.getLocalizedMessage()+"/n"+errors.toString())
    					.build();
        	}        
        }
	}

	@DELETE
	@Path("{id}")
	public Response deleteTable(@PathParam("id") Long id) {
		try{
			entityManager.remove(getTable(id).getEntity());
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getLocalizedMessage()+"/n"+errors.toString())
					.build();
		}
		return Response.status(Response.Status.OK)
				.build();
	}
}
