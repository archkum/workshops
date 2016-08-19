package com.ontestautomation.restassured.workshop.exercises;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class RestAssuredExercises1 {

	/*******************************************************
	 * Send a GET request to
	 * http://ergast.com/api/f1/2015/drivers.json
	 * and check that the answer has HTTP status code 200 
	 ******************************************************/
	
	@Test
	public void checkResponseCodeForCorrectRequest() {
		
		given().
		when().
				get("http://ergast.com/api/f1/2015/drivers.json").
		then().
				assertThat().
				statusCode(200);
	}
	
	/*******************************************************
	 * Send a GET request to
	 * http://ergast.com/api/f1/incorrect.json
	 * and check that the answer has HTTP status code 400 
	 ******************************************************/
	
	@Test
	public void checkResponseCodeForIncorrectRequest() {
				
		given().
		when().
                get("http://ergast.com/api/f1/incorrect.json").
		then().
                assertThat().
                statusCode(400);
	}
	
	/*******************************************************
	 * Send a GET request to
	 * http://ergast.com/api/f1/2015/drivers.json
	 * and check that the response is in JSON format 
	 ******************************************************/
	
	@Test
	public void checkResponseContentTypeJson() {
		
		given().
		when().
                get("http://ergast.com/api/f1/2015/drivers.json").
		then().
                assertThat().
                contentType("application/json");
	}
	
	/***********************************************
	 * Retrieve circuit information for the first race
	 * of the 2014 season and check the circuitId equals
	 * albert_park
	 * Use http://ergast.com/api/f1/2014/1/circuits.json
	 **********************************************/
	
	@Test
	public void checkTheFirstRaceOf2014WasAtAlbertPark() {
		
		given().
		when().
                get("http://ergast.com/api/f1/2014/1/circuits.json").
		then().
                body("MRData.CircuitTable.Circuits.circuitId",hasItem("albert_park"));
	}
	
	/***********************************************
	 * Retrieve the list of circuits for the 2014
	 * season and check that it contains silverstone
	 **********************************************/
	
	@Test
	public void checkThereWasARaceAtSilverstoneIn2014() {

		given().
		when().
                get("http://ergast.com/api/f1/2014/1/circuits.json").
		then().
                body("",hasItem("silverstone"));
	}
	
	/***********************************************
	 * Retrieve the list of circuits for the 2014
	 * season and check that it does not contain
	 * nurburgring
	 **********************************************/
	
	@Test
	public void checkThereWasNoRaceAtNurburgringIn2014() {

		given().
		when().
                get("http://ergast.com/api/f1/2014/1/circuits.json").
		then().
                body("",not("nurburgring"));
	}
}
