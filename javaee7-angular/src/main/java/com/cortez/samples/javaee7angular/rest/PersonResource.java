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

import com.cortez.samples.javaee7angular.data.Person;

@Stateless
@ApplicationPath("/resources")
@Path("Person")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource extends Application {
	
	@PersistenceContext
	private EntityManager entityManager;

	@GET
	@Path("{id}")
	public Response getPerson(@PathParam("id") Long id) {
		Person person = null;
		try {
			person = entityManager.find(Person.class, id);
			if(person == null){
				return Response.status(Response.Status.NOT_FOUND)
						.entity("la table d'id : "+id+" est introuvable.")
						.build();
			}
		} catch (Exception e) {			
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(getExceptionMessage(e))
					.build();
		}
		return Response.ok(person).build();
	}
	
	@POST
	public Response savePerson(Person person) {
		Response response = getPerson(person.getId());
		Person existingPerson = (response.getStatus() == Response.Status.OK.getStatusCode()) ? (Person) response.getEntity() : null;
        if (existingPerson == null) { // Ajout
            Person personToSave = new Person();
            personToSave.setFirst_name(person.getFirst_name());
            personToSave.setLast_name(person.getLast_name());
            personToSave.setEmail(person.getEmail());   
            personToSave.setTel_number(person.getTel_number());
            try{
            	entityManager.persist(personToSave);
            }catch(Exception e){            
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    					.entity(getExceptionMessage(e))
    					.build();
            }
            return Response.ok(personToSave).build();
            
        } else { // Modif
        	existingPerson.setFirst_name(person.getFirst_name());
        	existingPerson.setLast_name(person.getLast_name());
        	existingPerson.setEmail(person.getEmail());   
        	existingPerson.setTel_number(person.getTel_number());
        	try{
        		return Response.ok(entityManager.merge(existingPerson)).build();
        	}catch(Exception e){
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    					.entity(getExceptionMessage(e))
    					.build();
        	}        
        }
	}
	
	@DELETE
	@Path("{id}")
	public Response deletePerson(@PathParam("id") Long id) {
		Response response = getPerson(id);
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
