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
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.cortez.samples.javaee7angular.data.Restaurant;
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
						.entity(rest).build();
			}
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getLocalizedMessage() + "/n" + errors.toString()).build();
		}
		return Response.ok(rest).build();
	}

	private List<Restaurant> findRestaurants(int startPosition, int maxResults, String sortFields,
			String sortDirections) {
		Query query = entityManager
				.createQuery("SELECT r FROM Restaurant r ORDER BY r." + sortFields + " " + sortDirections);
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
		Restaurant existingRestaurant = (Restaurant) getRestaurant(restaurant.getId()).getEntity();
		if (existingRestaurant == null) { // Ajout
			Restaurant restaurantToSave = new Restaurant();
			restaurantToSave.setAddress(restaurant.getAddress());
			restaurantToSave.setEmail(restaurant.getEmail());
			restaurantToSave.setLatitude(restaurant.getLatitude());
			restaurantToSave.setLongitude(restaurant.getLongitude());
			restaurantToSave.setName(restaurant.getName());
			restaurantToSave.setSpeciality(restaurant.getSpeciality());
			restaurantToSave.setTel_number(restaurant.getTel_number());
			restaurantToSave.setUrl_img(restaurant.getUrl_img());
			try {
				entityManager.persist(restaurantToSave);
			} catch (Exception e) {
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity(e.getLocalizedMessage() + "/n" + errors.toString()).build();
			}
			return Response.ok(restaurantToSave).build();

		} else { // Modif
			existingRestaurant.setAddress(restaurant.getAddress());
			existingRestaurant.setEmail(restaurant.getEmail());
			existingRestaurant.setLatitude(restaurant.getLatitude());
			existingRestaurant.setLongitude(restaurant.getLongitude());
			existingRestaurant.setName(restaurant.getName());
			existingRestaurant.setSpeciality(restaurant.getSpeciality());
			existingRestaurant.setTel_number(restaurant.getTel_number());
			existingRestaurant.setUrl_img(restaurant.getUrl_img());
			try {
				return Response.ok(entityManager.merge(existingRestaurant)).build();
			} catch (Exception e) {
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity(e.getLocalizedMessage() + "/n" + errors.toString()).build();
			}
		}
	}

	@DELETE
	@Path("{id}")
	public Response deleteRestaurant(@PathParam("id") Long id) {
		try {
			entityManager.remove(getRestaurant(id).getEntity());
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getLocalizedMessage() + "/n" + errors.toString()).build();
		}
		return Response.status(Response.Status.OK).build();
	}
	
	
}
