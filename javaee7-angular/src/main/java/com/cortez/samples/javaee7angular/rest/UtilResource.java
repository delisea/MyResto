package com.cortez.samples.javaee7angular.rest;

import java.net.InetAddress;

import javax.ejb.Stateless;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@ApplicationPath("/resources")
@Path("utils/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtilResource {

	@GET
	@Path("getHostname")
	public String getHostname(){
		try{
			return InetAddress.getLocalHost().getHostName();
		}catch(Exception e){
			return "Erreur lors de la récupération du hostname";
		}
	}
}
