package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.example.restservicedemo.domain.Band;
import com.example.restservicedemo.domain.Song;
import com.jayway.restassured.RestAssured;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class SongServiceTest {
	
	private static final String SONG_TITLE = "JakisTytul";

	
	@BeforeClass
    public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";   	
    }
	
	@Test
	
	public void addSongs(){		
		
		delete("/song/deleteSongs").then().assertThat().statusCode(200);
		delete("/band/deleteBands").then().assertThat().statusCode(200);
		
		Song song = new Song(0, SONG_TITLE, "ALBUM");
		
		given().
	       contentType(MediaType.APPLICATION_JSON).
	       body(song).
	    when().	     
	    post("/song/add").then().assertThat().statusCode(201);
				
		Song rSong = get("/song/0").as(Song.class);
		
		assertThat(SONG_TITLE, equalToIgnoringCase(rSong.getTitle()));
		
	}
	
	@Test
	
	public void deleteSongWithId(){
		
Song song = new Song(0, SONG_TITLE, "ALBUM");
		
		given().
	       contentType(MediaType.APPLICATION_JSON).
	       body(song).
	    when().	     
	    post("/song/add").then().assertThat().statusCode(201);
				
		Song rSong = get("/song/0").as(Song.class);
		
		assertThat(SONG_TITLE, equalToIgnoringCase(rSong.getTitle()));
		
		
		delete("/song/deleteSong/0").then().assertThat().statusCode(200);
		
	}
	
	@Test
	
	public void clearSongs() {
		delete("/song/deleteSongs").then().assertThat().statusCode(200);
		
		given()
	       	.contentType(MediaType.APPLICATION_JSON)
	       	.body(Song.class)
	    .when()
	    .then()
	    	.body("", Matchers.hasSize(0));
	}
	
	@Test
	
	public void getAllSongs(){
		delete("/song/deleteSongs/").then().assertThat().statusCode(200);
		Song song1 = new Song(1,"Jeden", "album1");
		Song song2 = new Song(2,"Dwa", "album2");
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(song1)
		.when()
			.post("/song/add").then().assertThat().statusCode(201);
		
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(song2)
		.when()
			.post("/song/add").then().assertThat().statusCode(201);

		given()
		.when()
			.get("/song/getAll")
		.then()
			.body("song[0].id", equalTo("1"))
			.body("song[0].title", equalTo("Jeden"))
			.body("song[0].album", equalTo("album1"))
			.body("song[1].id", equalTo("2"))
			.body("song[1].title",equalTo("Dwa"))
			.body("song[1].album", equalTo("album2"))
		
			.body("song.id", hasItems("1","2"));
	}

}