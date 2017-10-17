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

import com.cortez.samples.javaee7angular.data.TableResto;

@Stateless
@ApplicationPath("/resources")
@Path("tables")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TableResource extends Application{
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@GET
	@Path("{id}")
	public TableResto getTable(@PathParam("id") Long id) {
		TableResto t = new TableResto();
		try{
			return entityManager.find(TableResto.class, id);
		}catch(Exception e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			t.setLog(e.getLocalizedMessage()+"/n"+errors.toString());
		}
		return t;
	}
	
	@POST
    public TableResto saveTable(TableResto table) {
        if (table.getId() == null) {
            TableResto tableToSave = new TableResto();
            tableToSave.setAvailable_places(table.getAvailable_places());
            tableToSave.setMovable(table.isMovable());
            tableToSave.setNumber(table.getNumber());
            entityManager.persist(table);
        } else {
            TableResto tableToUpdate = getTable(table.getId());
            tableToUpdate.setAvailable_places(table.getAvailable_places());
            tableToUpdate.setMovable(table.isMovable());
            tableToUpdate.setNumber(table.getNumber());
            table = entityManager.merge(tableToUpdate);
        }
        return table;
    }
	
	@DELETE
    @Path("{id}")
    public void deleteTable(@PathParam("id") Long id) {
        entityManager.remove(getTable(id));
    }
}
