package com.cortez.samples.javaee7angular.rest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
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

import com.cortez.samples.javaee7angular.data.Disponibility;
import com.cortez.samples.javaee7angular.data.Restaurant;
import com.cortez.samples.javaee7angular.data.Speciality;
import com.cortez.samples.javaee7angular.data.TableResto;
import com.cortez.samples.javaee7angular.pagination.PaginatedListWrapper;

@Stateless
@ApplicationPath("/resources")
@Path("restaurants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestaurantResource extends Application {

	@PersistenceContext
	private EntityManager entityManager;
	
	private Integer countRestaurants() {
		Query query = entityManager.createQuery("SELECT COUNT(r.id) FROM Restaurant r");
		return ((Long) query.getSingleResult()).intValue();
	}

	@GET
	@Path("{id}")
	public Response getRestaurant(@PathParam("id") Long id) {
		Restaurant rest = null;
		try {
			
			rest = entityManager.find(Restaurant.class, id);
			if (rest == null) {
				return Response.status(Response.Status.NOT_FOUND)
						.entity("le restaurant d'id : "+id+" est introuvable.").build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(getExceptionMessage(e)).build();
		}
		return Response.ok(rest).build();
	}
	@GET
	@Path("/search")
	public Response searchRestaurantsByCriteria(@QueryParam("disponibility") String disponibility, @QueryParam("speciality") String speciality,@QueryParam("day") String day, @QueryParam("nbCouverts") int nbCouverts/*, @QueryParam("address") String address*/){
		List<Restaurant> results = null;	
		
		//results = query.getResultList();
		String queryString = "SELECT distinct r FROM Restaurant r";
		if(disponibility != null){
			queryString += " JOIN r.disponibilities d ON d.periode = '"+disponibility+"'";
			if(day != null){
				queryString+=" AND d.day = '"+day+"'";
			}
		}
		else if(day != null){
			queryString += " JOIN r.disponibilities d ON d.day = '"+day+"'";
		}
		
		if(speciality != null){
			queryString += " JOIN r.specialities s ON s.speciality_label = '"+speciality+"'";
		}				
		
		Query query = entityManager.createQuery(queryString);
		results = query.getResultList();
		
		if(nbCouverts != 0)
		{
			for(Restaurant r : results){
				Response response = isRestaurantAvailable(r.getId(), nbCouverts);
				Object available_obj = (response.getStatus() == Response.Status.OK.getStatusCode()) ? response.getEntity() : null;
				if(available_obj == null){
					return Response.status(Response.Status.NOT_FOUND)
							.entity("le restaurant d'id : "+r.getId()+" est introuvable.").build();
				}
				else{
					boolean available_bool = (boolean)available_obj;
					if(!available_bool){
						results.remove(r);
					}
				}
			}
		}
		return Response.ok(results).build();
	}

	private List<Restaurant> findRestaurants(int startPosition, int maxResults, String sortFields,
			String sortDirections) {
		Query query = entityManager
				.createQuery("SELECT r.id, r.name, r.address FROM Restaurant r ORDER BY r." + sortFields + " " + sortDirections);
		query.setFirstResult(startPosition);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}
	

	private PaginatedListWrapper findRestaurants(PaginatedListWrapper wrapper) {
		wrapper.setTotalResults(countRestaurants());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setRestaurants(
				findRestaurants(start, wrapper.getPageSize(), wrapper.getSortFields(), wrapper.getSortDirections()));
		return wrapper;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PaginatedListWrapper listRestaurants(@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		PaginatedListWrapper paginatedListWrapper = new PaginatedListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findRestaurants(paginatedListWrapper);
	}

	@POST
	public Response saveRestaurant(Restaurant restaurant) {
		
		Response response = getRestaurant(restaurant.getId());		
		Restaurant existingRestaurant = (response.getStatus() == Response.Status.OK.getStatusCode()) ? (Restaurant) response.getEntity() : null;
		if (existingRestaurant == null) { // Ajout
			Restaurant restaurantToSave = new Restaurant();
			restaurantToSave.setAddress(restaurant.getAddress());
			restaurantToSave.setEmail(restaurant.getEmail());
			restaurantToSave.setName(restaurant.getName());
			restaurantToSave.setTel_number(restaurant.getTel_number());
			restaurantToSave.setUrl_img(restaurant.getUrl_img());
			try {	
				
				entityManager.persist(restaurantToSave);			
			} catch (Exception e) {			
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity(getExceptionMessage(e)).build();
			}
			return Response.ok(restaurantToSave).build();

		} else { // Modif
			existingRestaurant.setAddress(restaurant.getAddress());
			existingRestaurant.setEmail(restaurant.getEmail());			
			existingRestaurant.setName(restaurant.getName());
			existingRestaurant.setTel_number(restaurant.getTel_number());
			existingRestaurant.setUrl_img(restaurant.getUrl_img());
			try {
				return Response.ok(entityManager.merge(existingRestaurant)).build();
			} catch (Exception e) {				
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity(getExceptionMessage(e)).build();
			}
		}
	}


	@DELETE
	@Path("{id}")
	public Response deleteRestaurant(@PathParam("id") Long id) {		
		Response response = getRestaurant(id);
		if(response.getStatus() != Response.Status.OK.getStatusCode()){
			return response;
		}
		
		Restaurant restaurantToDelete = (Restaurant)response.getEntity();
		
		// Supprimer les tables associées
		List<TableResto> tablesToDelete = restaurantToDelete.getTables();
		for(TableResto tr : tablesToDelete){
			TableResto tableToDelete = entityManager.find(TableResto.class, tr.getId());
			if(tableToDelete != null)
				try{
					entityManager.remove(tableToDelete);
				}catch (Exception e) {			
					return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
							.entity(getExceptionMessage(e)).build();
				}				
		}
		
		// Supprimer les disponibilités associées
		List<Disponibility> diponibilitiesToDelete = restaurantToDelete.getDisponibilities();
		for(Disponibility dispo : diponibilitiesToDelete){
			Disponibility dispoToDelete = entityManager.find(Disponibility.class, dispo.getId());
			if(dispoToDelete != null)
				try{
					entityManager.remove(dispoToDelete);
				}catch (Exception e) {			
					return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
							.entity(getExceptionMessage(e)).build();
				}				
		}
		
		//Supprimer les spécialités associées
		List<Speciality> specialitiesToDelete = restaurantToDelete.getSpecialities();
		for(Speciality speciality : specialitiesToDelete){
			Speciality specialityToDelete = entityManager.find(Speciality.class, speciality.getId());
			if(specialityToDelete != null)
				try{
					entityManager.remove(specialityToDelete);
				}catch (Exception e) {			
					return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
							.entity(getExceptionMessage(e)).build();
				}				
		}
		
		
		// Suppression du restaurant
		try { 
			entityManager.remove(response.getEntity());
		} catch (Exception e) {			
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(getExceptionMessage(e)).build();
		}
		return Response.status(Response.Status.OK).build();
	}
	
	@GET
	@Path("/isRestaurantAvailable/{nbCouverts}")
	public Response isRestaurantAvailable(@HeaderParam("restaurant_id") Long restaurant_id, @QueryParam("nbCouverts") int nbCouverts){		
		boolean available = false;
		Response response = getRestaurant(restaurant_id);		
		Restaurant existingRestaurant = (response.getStatus() == Response.Status.OK.getStatusCode()) ? (Restaurant) response.getEntity() : null;
		if(existingRestaurant==null){
			return Response.status(Response.Status.NOT_FOUND)
					.entity("le restaurant d'id : "+restaurant_id+" est introuvable.").build();
		}
		else{
			List<TableResto> tableRestoList = existingRestaurant.getTables();
			for(TableResto tr : tableRestoList){
				if(tr.getPlaces()>=nbCouverts){
					available = true;
					break;
				}
			}
		}
		return Response.status(Response.Status.OK)
				.entity(available).build();
	}
	
	private String getExceptionMessage(Exception e){
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		return e.getLocalizedMessage() + "/n" + errors.toString();
	}
	
}
