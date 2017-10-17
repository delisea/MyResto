package com.cortez.samples.javaee7angular.rest;

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

import com.cortez.samples.javaee7angular.data.Table;

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
	public Table getTable(@PathParam("id") Long id) {
		return entityManager.find(Table.class, id);
	}
	
	@POST
    public Table saveTable(Table table) {
        if (table.getId() == null) {
            Table tableToSave = new Table();
            tableToSave.setAvailable_places(table.getAvailable_places());
            tableToSave.setMovable(table.isMovable());
            tableToSave.setNumber(table.getNumber());
            entityManager.persist(table);
        } else {
            Table tableToUpdate = getTable(table.getId());
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
