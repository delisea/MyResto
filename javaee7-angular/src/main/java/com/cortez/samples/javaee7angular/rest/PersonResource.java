package com.cortez.samples.javaee7angular.rest;

import com.cortez.samples.javaee7angular.data.Person;
import com.cortez.samples.javaee7angular.pagination.PaginatedListWrapper;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * REST Service to expose the data to display in the UI grid.
 *
 * @author Roberto Cortez
 */
@Stateless
@ApplicationPath("/resources")
@Path("persons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource extends Application {
    @PersistenceContext
    private EntityManager entityManager;

    private Integer countPersons() {
        Query query = entityManager.createQuery("SELECT COUNT(p.id) FROM Person p");
        return ((Long) query.getSingleResult()).intValue();
    }

    @SuppressWarnings("unchecked")
    private List<Person> findPersons(int startPosition, int maxResults, String sortFields, String sortDirections) {
        Query query =
                entityManager.createQuery("SELECT p FROM Person p ORDER BY p." + sortFields + " " + sortDirections);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }

    private PaginatedListWrapper findPersons(PaginatedListWrapper wrapper) {
        wrapper.setTotalResults(countPersons());
        int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
        wrapper.setList(findPersons(start,
                                    wrapper.getPageSize(),
                                    wrapper.getSortFields(),
                                    wrapper.getSortDirections()));
        return wrapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedListWrapper listPersons(@DefaultValue("1")
                                            @QueryParam("page")
                                            Integer page,
                                            @DefaultValue("id")
                                            @QueryParam("sortFields")
                                            String sortFields,
                                            @DefaultValue("asc")
                                            @QueryParam("sortDirections")
                                            String sortDirections) {
        PaginatedListWrapper paginatedListWrapper = new PaginatedListWrapper();
        paginatedListWrapper.setCurrentPage(page);
        paginatedListWrapper.setSortFields(sortFields);
        paginatedListWrapper.setSortDirections(sortDirections);
        paginatedListWrapper.setPageSize(10);
        return findPersons(paginatedListWrapper);
    }

    @GET
    @Path("{id}")
    public Person getPerson(@PathParam("id") Long id) {
    	Person p = null;
    	try{	
    		return entityManager.find(Person.class, id);
    	}catch(Exception e){
    		p = new Person();
    		StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			p.setLog(e.getLocalizedMessage()+"/n"+errors.toString());
    	}
    	return p;
    }
    
    @GET
    @Path("{name}")
    public Person getPersonByName(@PathParam("name") String name) {
    	Person p = new Person();
    	try{	
    		return entityManager.find(Person.class, name);
    	}catch(Exception e){
    		StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			p.setLog(e.getLocalizedMessage()+"/n"+errors.toString());
    	}
    	return p;
    }

    @POST
    public Person savePerson(Person person) {  
    	Person existingPerson = getPerson(person.getId());
    	// Ajout
        if (existingPerson == null) {
            Person personToSave = new Person();
            personToSave.setName(person.getName());
            personToSave.setDescription(person.getDescription());
            personToSave.setImageUrl(person.getImageUrl());
            
            try{
            	entityManager.persist(personToSave);
            }catch(Exception e){
            	StringWriter errors = new StringWriter();
    			e.printStackTrace(new PrintWriter(errors));
    			personToSave.setLog(e.getLocalizedMessage()+"/n"+errors.toString());
            }
            return personToSave;
            
        } else { // Modif
        	existingPerson.setName(person.getName());
        	existingPerson.setDescription(person.getDescription());
        	existingPerson.setImageUrl(person.getImageUrl());
            return entityManager.merge(existingPerson);
        }
    }

    @DELETE
    @Path("{id}")
    public void deletePerson(@PathParam("id") Long id) {
        entityManager.remove(getPerson(id));
    }
}
