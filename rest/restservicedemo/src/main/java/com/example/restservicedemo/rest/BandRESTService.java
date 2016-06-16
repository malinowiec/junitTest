package com.example.restservicedemo.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restservicedemo.domain.Band;
import com.example.restservicedemo.service.BandManager;

@Path("band")
public class BandRESTService {	
	
	private BandManager bm = new BandManager();
	
	@GET
	@Path("/{bandId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Band getBand(@PathParam("bandId") Long id){
		Band b = bm.getBand(id);
		return b;
	}
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Band> getAll(){
		return bm.getAllBands();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBand(Band band){
		bm.addBandWithId(band);
		return Response.status(201).entity("Band").build(); 
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "REST API /band is running";
	}
	
	@DELETE
	 @Path("/deleteBands")
	public Response clearBands(){
		bm.clearBands();
		return Response.status(200).build();
	}

	@DELETE
    @Path("/deleteBand/{bandName}")
	public Response deleteBandWithName(@PathParam("bandName") String name){
		bm.deleteBandWithName(name);
		return Response.status(200).build();
	}
	
}
