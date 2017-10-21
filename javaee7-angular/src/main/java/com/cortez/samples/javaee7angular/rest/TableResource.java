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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cortez.samples.javaee7angular.data.Person;
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
	public TableResto getTable(@PathParam("id") Long id) {
		TableResto t = new TableResto();
		try {
			return entityManager.find(TableResto.class, id);
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			t.setLog(e.getLocalizedMessage()+"/n"+errors.toString());
			//throw new WebApplicationException(e.getLocalizedMessage() + "/n" + errors.toString());
		}
		return t;
	}

	@POST
	public TableResto saveTable(TableResto table) {
		TableResto existingTable = getTable(table.getId());
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
    			tableToSave.setLog(e.getLocalizedMessage()+"/n"+errors.toString());
            }
            return tableToSave;
            
        } else { // Modif
        	existingTable.setAvailable_places(table.getAvailable_places());
        	existingTable.setMovable(table.isMovable());
        	existingTable.setNumber(table.getNumber());   
            return entityManager.merge(existingTable);
        }
	}

	@DELETE
	@Path("{id}")
	public void deleteTable(@PathParam("id") Long id) {
		entityManager.remove(getTable(id));
	}
}
