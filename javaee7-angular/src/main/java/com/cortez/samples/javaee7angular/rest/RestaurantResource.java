package com.cortez.samples.javaee7angular.rest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.transaction.Transactional;
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
import java.util.regex.*;

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
						.entity("le restaurant d'id : " + id + " est introuvable.").build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(getExceptionMessage(e)).build();
		}
		return Response.ok(rest).build();
	}

	@GET
	@Path("/search")
	public Response searchRestaurantsByCriteria(@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections,
			@QueryParam("disponibility") String disponibility,
			@QueryParam("speciality") String speciality, @QueryParam("day") String day,
			@DefaultValue("0") @QueryParam("nbCouverts") int nbCouverts,
			@QueryParam("latitude") Float latitude, @QueryParam("longitude") Float longitude, @QueryParam("rayon") Float rayon) {

		// Préparation du wrapper
		
		PaginatedListWrapper paginatedListWrapper = new PaginatedListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		paginatedListWrapper.setTotalResults(countRestaurants());
		int start = (paginatedListWrapper.getCurrentPage() - 1) * paginatedListWrapper.getPageSize();
				
		////////////////////////////
		
		
		List<Restaurant> requestResults = null;
		List<String> disponibilities = new ArrayList<String>();
		List<String> specialities = new ArrayList<String>();
		List<String> days = new ArrayList<String>();

		if (disponibility != null) {
			disponibilities = Arrays.asList(disponibility.split("\\s*,\\s*"));
			if (disponibilities.get(0).isEmpty()) {
				disponibilities = disponibilities.subList(1, disponibilities.size());
			}
		}

		if (speciality != null) {
			specialities = Arrays.asList(speciality.split("\\s*,\\s*"));
			if (specialities.get(0).isEmpty()) {
				specialities = specialities.subList(1, specialities.size());
			}
		}

		if (day != null) {
			days = Arrays.asList(day.split("\\s*,\\s*"));
			if (days.get(0).isEmpty()) {
				days = days.subList(1, days.size());
			}
		}

		String queryString = "SELECT distinct r FROM Restaurant r";
		if (disponibility != null && !disponibilities.isEmpty()) {
			queryString += " JOIN r.disponibilities d ON d.periode = '" + disponibilities.get(0) + "'";
			for (int i = 1; i < disponibilities.size(); i++) {
				queryString += " OR d.periode = '" + disponibilities.get(i) + "'";
			}
			if (day != null && !days.isEmpty()) {
				queryString += " AND d.day = '" + days.get(0) + "'";
				for (int i = 1; i < days.size(); i++) {
					queryString += " OR d.day = '" + days.get(i) + "'";
				}
			}
		} else if (day != null && !days.isEmpty()) {
			queryString += " JOIN r.disponibilities d ON d.day = '" + days.get(0) + "'";
			for (int i = 1; i < days.size(); i++) {
				queryString += " OR d.day = '" + days.get(i) + "'";
			}
		}

		if (speciality != null && !specialities.isEmpty()) {
			queryString += " JOIN r.specialities s ON s.speciality_label = '" + specialities.get(0) + "'";
			for (int i = 1; i < specialities.size(); i++) {
				queryString += " OR s.speciality_label = '" + specialities.get(i) + "'";
			}
		}

		//Geosearh
		if (latitude != null && longitude != null && rayon != null && rayon != 0){
			float maxlatitude = latitude + offsetLat(latitude, rayon);
			float minlatitude = latitude - offsetLat(latitude, rayon);
			float maxlongitude = longitude + offsetLong(latitude, longitude, rayon);
			float minlongitude = longitude - offsetLong(latitude, longitude, rayon);

			queryString += " WHERE r.latitude <= " + maxlatitude + " AND r.latitude >= "  + minlatitude + " AND r.longitude <= " + maxlongitude + " AND r.longitude >= " + minlongitude;	
		}
		
		// Elements de pagination
		queryString += " ORDER BY r." + sortFields + " " + sortDirections;
		
		Query query = entityManager.createQuery(queryString);
		query.setFirstResult(start);
		query.setMaxResults(paginatedListWrapper.getPageSize());
		requestResults = query.getResultList();

		if (nbCouverts != 0) {
			List<Restaurant> returnList = new ArrayList<Restaurant>();
			for (Restaurant r : requestResults) {
				Response response = isRestaurantAvailable(r.getId(), nbCouverts);
				Object available_obj = (response.getStatus() == Response.Status.OK.getStatusCode())
						? response.getEntity() : null; // true/false or null
				if (available_obj == null) {
					return Response.status(Response.Status.NOT_FOUND)
							.entity("le restaurant d'id : " + r.getId() + " est introuvable.").build();
				} else {
					boolean available_bool = (boolean) available_obj;
					if (available_bool) {
						returnList.add(r);
					}
				}
			}
			paginatedListWrapper.setRestaurants(returnList);
		
			return Response.ok(paginatedListWrapper).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
		} else {
			paginatedListWrapper.setRestaurants(requestResults);
			return Response.ok(paginatedListWrapper).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
		}
	}

	/*
	 * Retourne la liste paginée de tous les restaurants de la base
	 */

	private List<Restaurant> findRestaurants(int startPosition, int maxResults, String sortFields,
			String sortDirections) {
		Query query = entityManager.createQuery(
				"SELECT r.id, r.name, r.address FROM Restaurant r ORDER BY r." + sortFields + " " + sortDirections);
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
		Restaurant existingRestaurant = (response.getStatus() == Response.Status.OK.getStatusCode())
				? (Restaurant) response.getEntity() : null;
		if (existingRestaurant == null) { // Ajout
			Restaurant restaurantToSave = new Restaurant();
			restaurantToSave.setAddress(restaurant.getAddress());
			restaurantToSave.setEmail(restaurant.getEmail());
			restaurantToSave.setName(restaurant.getName());
			restaurantToSave.setTel_number(restaurant.getTel_number());
			restaurantToSave.setUrl_img(restaurant.getUrl_img());
			restaurantToSave.setLocalisation(restaurant.getLatitude(), restaurant.getLongitude());
			try {
				entityManager.persist(restaurantToSave);
			} catch (Exception e) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(getExceptionMessage(e)).build();
			}
			return Response.ok(restaurantToSave).build();

		} else { // Modif
			existingRestaurant.setAddress(restaurant.getAddress());
			existingRestaurant.setEmail(restaurant.getEmail());
			existingRestaurant.setName(restaurant.getName());
			existingRestaurant.setTel_number(restaurant.getTel_number());
			existingRestaurant.setUrl_img(restaurant.getUrl_img());
			existingRestaurant.setLocalisation(restaurant.getLatitude(), restaurant.getLongitude());
			try {
				return Response.ok(entityManager.merge(existingRestaurant)).build();
			} catch (Exception e) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(getExceptionMessage(e)).build();
			}
		}
	}

	@DELETE
	@Path("/deleteAllRestaurants")
	public Response deleteAllRestaurants(){
		Response response;
		Query query = entityManager.createQuery(
				"SELECT r.id FROM Restaurant r" );
		List<Long> restaurant_ids = query.getResultList();
		for(Long l : restaurant_ids){
			response = deleteRestaurant(l);
			if (response.getStatus() != Response.Status.NO_CONTENT.getStatusCode()) {
				return response;
			}
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	
	@DELETE
	@Path("{id}")
	public Response deleteRestaurant(@PathParam("id") Long id) {
		Response response = getRestaurant(id);
		if (response.getStatus() != Response.Status.OK.getStatusCode()) {
			return response;
		}

		Restaurant restaurantToDelete = (Restaurant) response.getEntity();

		// Supprimer les tables associées
		List<TableResto> tablesToDelete = restaurantToDelete.getTables();
		for (TableResto tr : tablesToDelete) {
			TableResto tableToDelete = entityManager.find(TableResto.class, tr.getId());
			if (tableToDelete != null)
				try {
					entityManager.remove(tableToDelete);
				} catch (Exception e) {
					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(getExceptionMessage(e))
							.build();
				}
		}

		// Supprimer les disponibilités associées
		List<Disponibility> diponibilitiesToDelete = restaurantToDelete.getDisponibilities();
		for (Disponibility dispo : diponibilitiesToDelete) {
			Disponibility dispoToDelete = entityManager.find(Disponibility.class, dispo.getId());
			if (dispoToDelete != null)
				try {
					entityManager.remove(dispoToDelete);
				} catch (Exception e) {
					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(getExceptionMessage(e))
							.build();
				}
		}

		// Supprimer les spécialités associées
		List<Speciality> specialitiesToDelete = restaurantToDelete.getSpecialities();
		for (Speciality speciality : specialitiesToDelete) {
			Speciality specialityToDelete = entityManager.find(Speciality.class, speciality.getId());
			if (specialityToDelete != null)
				try {
					entityManager.remove(specialityToDelete);
				} catch (Exception e) {
					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(getExceptionMessage(e))
							.build();
				}
		}

		// Suppression du restaurant
		try {
			entityManager.remove(response.getEntity());
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(getExceptionMessage(e)).build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@GET
	@Path("/getTables/{restaurant_id}")
	public Response getTables(@PathParam("restaurant_id") Long restaurant_id) {
		List<TableResto> requestResults = null;
		String queryString = "SELECT t FROM TableResto t where t.restaurant.id=" + restaurant_id;
		Query query = entityManager.createQuery(queryString);
		requestResults = query.getResultList();
		return Response.status(Response.Status.OK).entity(requestResults).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	}

	@GET
	@Path("/getSpecialities/{restaurant_id}")
	public Response getSpecialities(@PathParam("restaurant_id") Long restaurant_id) {
		List<Speciality> requestResults = null;
		String queryString = "SELECT s FROM Speciality s where s.restaurant.id=" + restaurant_id;
		Query query = entityManager.createQuery(queryString);
		requestResults = query.getResultList();
		return Response.status(Response.Status.OK).entity(requestResults).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	}

	@GET
	@Path("/getDisponibilities/{restaurant_id}")
	public Response getDisponibilities(@PathParam("restaurant_id") Long restaurant_id) {
		List<Disponibility> requestResults = null;
		String queryString = "SELECT d FROM Disponibility d where d.restaurant.id=" + restaurant_id;
		Query query = entityManager.createQuery(queryString);
		requestResults = query.getResultList();
		return Response.status(Response.Status.OK).entity(requestResults).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	}

	@GET
	@Path("/isRestaurantAvailable/{nbCouverts}")
	public Response isRestaurantAvailable(@HeaderParam("restaurant_id") Long restaurant_id,
			@PathParam("nbCouverts") int nbCouverts) {
		boolean available = false;
		Response response = getRestaurant(restaurant_id);
		Restaurant existingRestaurant = (response.getStatus() == Response.Status.OK.getStatusCode())
				? (Restaurant) response.getEntity() : null;
		if (existingRestaurant == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("le restaurant d'id : " + restaurant_id + " est introuvable.").build();
		} else {
			List<TableResto> tableRestoList = existingRestaurant.getTables();
			int nbAvailablePlaces = 0;
			boolean alreadyUnmovable = false; // si une table non bougeable à
												// déjà été rencontrée
			for (TableResto tr : tableRestoList) {
				if (tr.isMovable())
					nbAvailablePlaces += tr.getPlaces();
				else {
					if (!alreadyUnmovable) {
						alreadyUnmovable = true;
						nbAvailablePlaces += tr.getPlaces();
					}
				}
				if (nbAvailablePlaces >= nbCouverts) {
					available = true;
					break;
				}
			}
		}
		return Response.status(Response.Status.OK).entity(available).build();
	}

	private String getExceptionMessage(Exception e) {
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		return e.getLocalizedMessage() + "/n" + errors.toString();
	}

	//For geoSearch
	private float offsetLat(float latitude, float rayon){
		float oneLatitudeDegree = 111.110f;
		return (rayon / oneLatitudeDegree);
	}

	private float offsetLong(float latitude, float longitude, float rayon){
		float oneLatitudeDegree = 111.110f;
		float oneLongitudeDegree =  111.110f *  (float) Math.cos(latitude);
		return (rayon / oneLongitudeDegree);
	}

}
