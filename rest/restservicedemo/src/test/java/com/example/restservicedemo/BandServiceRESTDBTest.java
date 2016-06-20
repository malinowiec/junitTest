package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.ws.rs.core.MediaType;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.example.restservicedemo.domain.Song;
import com.example.restservicedemo.domain.Band;
import com.example.restservicedemo.service.BandManager;
import com.jayway.restassured.RestAssured;

public class BandServiceRESTDBTest {
	
	private static IDatabaseConnection connection ;
	private static IDatabaseTester databaseTester;
	
	private static BandManager pm = new BandManager();

	@BeforeClass
	public static void setUp() throws Exception{
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";
		
		Connection jdbcConnection;
		jdbcConnection = DriverManager.getConnection(
				"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
		connection = new DatabaseConnection(jdbcConnection);
		
		databaseTester = new JdbcDatabaseTester(
				"org.hsqldb.jdbcDriver", "jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(
				new FileInputStream(new File("src/test/resources/fullData.xml")));
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}

	@Test
	
	public void addBand() throws Exception{
	
		Band aBand = new Band(1, "BandName1", 2010);
		given().contentType(MediaType.APPLICATION_JSON).body(aBand)
				.when().post("/band/add").then().assertThat().statusCode(201);
		
		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("BAND");
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				new File("src/test/resources/afterAddData.xml"));
		ITable expectedTable = expectedDataSet.getTable("BAND");
		
		Assertion.assertEquals(expectedTable, actualTable);
		
		delete("/band/deleteBands").then().assertThat().statusCode(200);
	}
	
	@Test
	
	public void addSong() throws Exception {
		Song aSong = new Song(1, "TitleName1", "AlbumName");
		given().contentType(MediaType.APPLICATION_JSON).body(aSong)
				.when().post("/song/add").then().assertThat().statusCode(201);

		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("SONG");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
				(actualTable, new String[]{"OWNER_ID"});
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				new File("src/test/resources/afterAddData.xml"));
		ITable expectedTable = expectedDataSet.getTable("SONG");
		
		Assertion.assertEquals(expectedTable, filteredTable);
		
		delete("/song/deleteSongs").then().assertThat().statusCode(200);
	}
	
	@Test
	
	public void deleteSongs() throws Exception {
		delete("/song/deleteSongs").then().assertThat().statusCode(200);
		
		given()
	       	.contentType(MediaType.APPLICATION_JSON)
	       	.body(Song.class)
	    .when()
	    .then()
	    	.body("", Matchers.hasSize(0));
		
		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("SONG");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
				(actualTable, new String[]{"OWNER_ID"});
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				new File("src/test/resources/afterDelete.xml"));
		ITable expectedTable = expectedDataSet.getTable("SONG");
		
		Assertion.assertEquals(expectedTable, filteredTable);
	}
	
	@Test
	
	public void deleteBands() throws Exception {
		delete("/band/deleteBands").then().assertThat().statusCode(200);
		
		given()
	       	.contentType(MediaType.APPLICATION_JSON)
	       	.body(Band.class)
	    .when()
	    .then()
	    	.body("", Matchers.hasSize(0));
		
		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("BAND");

		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				new File("src/test/resources/afterDelete.xml"));
		ITable expectedTable = expectedDataSet.getTable("BAND");
		
		Assertion.assertEquals(expectedTable, actualTable);
	}
	
@Test
	
	public void deleteBandWithName() throws Exception {
		delete("/band/deleteBands").then().assertThat().statusCode(200);
		
		Band aBand = new Band(1, "BandName1", 2010);
		
		given().contentType(MediaType.APPLICATION_JSON).body(aBand)
		.when().post("/band/add").then().assertThat().statusCode(201);
		
		delete("/band/deleteBand/BandName1").then().assertThat().statusCode(200);
		
		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("BAND");

		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				new File("src/test/resources/afterRemoveNameData.xml"));
		ITable expectedTable = expectedDataSet.getTable("BAND");
		
		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	
	@AfterClass
	public static void tearDown() throws Exception{
		databaseTester.onTearDown();
	}

}