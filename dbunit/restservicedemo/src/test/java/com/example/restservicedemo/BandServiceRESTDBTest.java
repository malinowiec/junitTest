package com.example.restservicedemo;

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
				.when().post("/band/").then().assertThat().statusCode(201);
		
		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("BAND");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
				(actualTable, new String[]{"B_ID"});
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				new File("src/test/resources/BandData.xml"));
		ITable expectedTable = expectedDataSet.getTable("BAND");
		
		Assertion.assertEquals(expectedTable, filteredTable);
	}
	
	@Test
	@Ignore
	public void addSong() throws Exception {
		Song aSong = new Song(2, "title", "album");
		given().contentType(MediaType.APPLICATION_JSON).body(aSong)
				.when().post("/songs/").then().assertThat().statusCode(201);

		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("SONG");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
				(actualTable, new String[]{"OWNER_ID"});
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				new File("src/test/resources/songData.xml"));
		ITable expectedTable = expectedDataSet.getTable("SONG");
		
		Assertion.assertEquals(expectedTable, filteredTable);
	}
	
	@AfterClass
	public static void tearDown() throws Exception{
		databaseTester.onTearDown();
	}

}