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

import com.cortez.samples.javaee7angular.data.Person;
import com.cortez.samples.javaee7angular.data.Reservation;
import com.cortez.samples.javaee7angular.data.Restaurant;

@Stateless
@ApplicationPath("/resources")
@Path("reservations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource  extends Application{

	@PersistenceContext
	private EntityManager entityManager;
	
	@GET
	public Response getReservations() {
		String queryString = "SELECT distinct r FROM Reservation r";
		Query query = entityManager.createQuery(queryString);
		return Response.ok(query.getResultList()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	}
	
	@GET
	@Path("{id}")
	public Response getReservation(@PathParam("id") Long id) {
		Reservation reservation = null;
		try {
			reservation = entityManager.find(Reservation.class, id);
			if(reservation == null){
				return Response.status(Response.Status.NOT_FOUND)
						.entity("la reservation d'id : "+id+" est introuvable.")
						.build();
			}
		} catch (Exception e) {			
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(getExceptionMessage(e))
					.build();
		}
		return Response.ok(reservation).build();
	}
	
	// Récupérer les reservations d'un restaurant
	@GET
	@Path("/getReservationsByRestaurantId")
	public Response getReservationsByRestaurantId(@QueryParam("restaurant_id") Long restaurant_id){
		Restaurant existingRestaurant = entityManager.find(Restaurant.class, restaurant_id);
		if (existingRestaurant == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("le Restaurant d'id : "+restaurant_id+" est introuvable.").build();
		}
		
		List<Reservation> reservations = null;
		String queryString = ("SELECT distinct r FROM Reservation r WHERE r.restaurant.id =" +  restaurant_id);
		Query query = entityManager.createQuery(queryString);
		reservations = query.getResultList();
		return Response.status(Response.Status.OK).entity(reservations).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();		
	}
	
	@GET
	@Path("/getReservationsByPersonId")
	public Response getReservationsByPersonId(@QueryParam("person_id") Long person_id){
		Person existingPerson = entityManager.find(Person.class, person_id);
		if (existingPerson == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("la personne d'id : "+person_id+" est introuvable.").build();
		}
		
		List<Reservation> reservations = null;
		String queryString = ("SELECT distinct r FROM Reservation r WHERE r.person.id =" +  person_id);
		Query query = entityManager.createQuery(queryString);
		reservations = query.getResultList();
		return Response.status(Response.Status.OK).entity(reservations).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();		
	}
	
	@POST
	public Response saveReservation(@HeaderParam("restaurant_id") Long restaurant_id, @HeaderParam("person_id") Long person_id, Reservation reservation) {
		Restaurant existingRestaurant = entityManager.find(Restaurant.class, restaurant_id);
		if (existingRestaurant == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("le Restaurant d'id : "+restaurant_id+" est introuvable.").build();
		}
		
		Person existingPerson = entityManager.find(Person.class, person_id);
		if (existingPerson == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("la personne d'id : "+person_id+" est introuvable.").build();
		}
		
		Response response = getReservation(reservation.getId());		
		Reservation existingReservation = (response.getStatus() == Response.Status.OK.getStatusCode()) ? (Reservation) response.getEntity() : null;
        if (existingReservation == null) { // Ajout
        	Reservation reservationToSave = new Reservation();
        	reservationToSave.setPerson(existingPerson);  
        	reservationToSave.setRestaurant(existingRestaurant);
        	reservationToSave.setNbCouverts(reservation.getNbCouverts());
        	reservationToSave.setPeriode(reservation.getPeriode());
        	reservationToSave.setDate(reservation.getDate());      	
            try{
            	entityManager.persist(reservationToSave);
            }catch(Exception e){            
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    					.entity(getExceptionMessage(e))
    					.build();
            }
            return Response.ok(reservationToSave).build();
            
        } else { // Modif
        	existingReservation.setPerson(existingPerson);  
        	existingReservation.setRestaurant(existingRestaurant);
        	existingReservation.setNbCouverts(reservation.getNbCouverts());
        	existingReservation.setPeriode(reservation.getPeriode());
        	existingReservation.setDate(reservation.getDate());
        	try{
        		return Response.ok(entityManager.merge(existingReservation)).build();
        	}catch(Exception e){
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
    					.entity(getExceptionMessage(e))
    					.build();
        	}        
        }
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteReservation(@PathParam("id") Long id) {
		Response response = getReservation(id);
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
