package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import javax.ws.rs.core.MediaType;

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
	@Ignore
	public void addSongs(){		
		
		delete("/song/deleteSongs").then().assertThat().statusCode(200);
		
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
	@Ignore
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
	@Ignore
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
	
	@Test
	@Ignore
	public void getSongWithOwner(){
		delete("/song/deleteSongs/").then().assertThat().statusCode(200);
		delete("/band/deleteBands/").then().assertThat().statusCode(200);
		Band band1 = new Band(1,"ZespolJeden", 1990);
		Song song1 = new Song(1,"SongFiat","1999");
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(band1)
		.when()
			.post("/band/add").then().assertThat().statusCode(201);
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(song1)
		.when()
			.post("/song/addSongWithId").then().assertThat().statusCode(201);
		
		given()
		.when()
			.get("/song/song/1")
		.then()
			.body("id", equalTo("1"))
			.body("title", equalTo("SongFiat"))
			.body("album", equalTo("1999"))
			.body("owner.id", equalTo("1"))
			.body("owner.name", equalTo("ZespolJeden"))
			.body("owner.yop", equalTo("1990"));
	}

}