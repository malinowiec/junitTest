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

import com.example.restservicedemo.domain.Song;
import com.example.restservicedemo.service.BandManager;

@Path("song")
public class SongRESTService {	
	
	private BandManager bm = new BandManager();
		
	@GET
	@Path("/{songId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Song getSong(@PathParam("songId") Long id){
		Song s = new Song();
		s.setId(id);
		Song song = bm.getSongWithOwner(s);
		return song;
	}
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Song> getAll(){
		return bm.getAllSongs();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSong(Song song){
		bm.addSongWithId(song);
		return Response.status(201).entity("Song").build(); 
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "REST API /song is running";
	}
	
	@DELETE
	@Path("/deleteSongs")
	public Response clearSongs(){
		bm.clearSongs();
		return Response.status(200).build();
	}
	
	@POST
	@Path("/addSongWithId")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSongWithId(Song song){
		bm.addSongWithId(song);
		return Response.status(201).entity("Song").build(); 
	}
	
	@DELETE
    @Path("/deleteSong/{songId}")
	public Response deleteSongWithId(@PathParam("songId") Long id){
		bm.deleteSongWithId(id);
		return Response.status(200).build();
	}

}
