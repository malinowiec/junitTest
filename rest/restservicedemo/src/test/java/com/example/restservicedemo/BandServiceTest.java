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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import com.example.restservicedemo.domain.Band;
import com.jayway.restassured.RestAssured;

public class BandServiceTest {
	
	private static final String BAND_NAME = "JakisZespol";
	
	@BeforeClass
    public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";   	
    }
	
	@Test
	public void addBands(){		
		
		delete("/band/deleteBands").then().assertThat().statusCode(200);
		
		Band band = new Band(0, BAND_NAME, 1976);
		
		given().
	       contentType(MediaType.APPLICATION_JSON).
	       body(band).
	    when().	     
	    post("/band/add").then().assertThat().statusCode(201);
				
		Band rBand = get("/band/0").as(Band.class);
		
		assertThat(BAND_NAME, equalToIgnoringCase(rBand.getName()));
		
	}
	
	@Test
	public void clearBands() {
		delete("/band/deleteBands").then().assertThat().statusCode(200);
		
		given()
	       	.contentType(MediaType.APPLICATION_JSON)
	       	.body(Band.class)
	    .when()
	    .then()
	    	.body("", Matchers.hasSize(0));
	}
	
	@Test
		public void deleteBandWithName(){
			
		delete("/band/deleteBands").then().assertThat().statusCode(200);
		
Band band = new Band(0, BAND_NAME, 1976);
		
		given().
	       contentType(MediaType.APPLICATION_JSON).
	       body(band).
	    when().	     
	    post("/band/add").then().assertThat().statusCode(201);
				
		Band rBand = get("/band/0").as(Band.class);
		
		assertThat(BAND_NAME, equalToIgnoringCase(rBand.getName()));
		
			delete("/band/deleteBand/JakisZespol").then().assertThat().statusCode(200);
			
		}
	
	@Test
	public void getAllBands(){
		delete("/band/deleteBands").then().assertThat().statusCode(200);
		
		Band band1 = new Band(1, "Zespol1", 1990);
		Band band2 = new Band(3,"Zespol2", 2002);
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(band1)
		.when()
			.post("/band/add").then().assertThat().statusCode(201);
		
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(band2)
		.when()
			.post("/band/add").then().assertThat().statusCode(201);

		given()
		.when()
			.get("/band/getAll")
		.then()
			.body("band[0].id", equalTo("1"))
			.body("band[0].name", equalTo("Zespol1"))
			.body("band[0].yoc", equalTo("1990"))
			.body("band[2].id", equalTo("3"))
			.body("band[2].name",equalTo("Zespol2"))
			.body("band[2].yoc", equalTo("2002"));
		//
		//	.body("band.id", hasItems("1","2"));
	}
	

}
