package RestTests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import 	static io.restassured.RestAssured.*;
import 	static io.restassured.matcher.RestAssuredMatchers.*;
import 	static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
/*
 * SetUP
 * GET, POst,Put Patch, Dlt
 * Json Validation
 * XML Validation
 * */
public class test_1 {
	
	 RequestSpecification requestSpecification;
	 Response response;
	 ValidatableResponse validatableResponse;
	 
	 
	 
	@BeforeTest
	public void methodURL() {
		useRelaxedHTTPSValidation();
		RestAssured.baseURI = "https://reqres.in";
		
	}
	@Test()
	public   void bsic() {
		 
		useRelaxedHTTPSValidation();
		
		//TestNG class
		//GET class -->https://reqres.in/    http://dummy.restapiexample.com/api/v1/employees
		/*https://restful-api.dev/
		 * */
		/*
		RestAssured.useRelaxedHTTPSValidation();
		
		
		//strore response 
		Response res = RestAssured.get("https://10.11.144.178:8107/home/login/1/");
		
		
		System.out.println(res.getStatusCode());
		System.out.println(res.contentType());
		System.out.println(res.getTime());
		System.out.println(res.getBody().asString()); 
		System.out.println(res.asPrettyString()); 
		System.out.println(res.getHeaders());
		
		System.out.println(res.getStatusLine());
		System.out.println(res.getHeaders().asList()); 
		System.out.println(res.header("Content-Type")); 
		
		baseURI = "http://dummy.restapiexample.com";
		baseURI = "https://reqres.in";
		
*/
		
		baseURI = "http://dummy.restapiexample.com";
		
		given().get("/api/v1/employees")
		.then()
			.body("message", equalTo("Successfully! All records has been fetched."));
			//.body("data.employee_name", hasItems("allNames")); 
		
		
		
		//post
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("employee_name", "Shiva");
		map.put("employee_salary", "90000");
		map.put("employee_age", "24");
		System.out.println(map);
		
		JSONObject obj = new JSONObject(map);
		
		obj.put("employee_name", "newname");
		System.out.println(obj);
		
		given()
		.body(obj.toJSONString())
		.when().post("/api/users")
		.then().statusCode(201);
		
		
		
		
		
		
	
		
	}
	
	//@Test()
	
	public void getthod() {
		useRelaxedHTTPSValidation();
		/*given().get("https://reqres.in/api/users?page=2")
		.then()
		.body("data[4].email", equalTo("george.edwards@reqres.in"))
		; */
		
		//RestAssured.baseURI = "http://dummy.restapiexample.com";
		Response req = given().get("https://reqres.in/api/users");
		System.out.println(req.getHeader("Content-type"));
		String body = given().get("https://reqres.in/api/users").getBody().asPrettyString();
		System.out.println(body);
		//given().get("/api/v1/employees").then().statusCode(200).body("data[10].employee_name", equalTo("Jena Gaines"));
		
		
		
		
		
	}
	
	//@Test
	public void Postmethod() {
		
		
		//API https://reqres.in/api/users
		
		//baseURI = "https://reqres.in/api";
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", "value");
		map.put("job", "v");
		System.out.println(map);
		JSONObject obj = new JSONObject();
		obj.put("name", "john");
		obj.put("job", "tester");
		System.out.println(obj);
		given().body(obj.toJSONString())
		.when().post("https://reqres.in/api/users")
		.then().statusCode(201)
		.log().all();
		
	}
		
	
	//@Test
	public  void suma() {
		useRelaxedHTTPSValidation();
		baseURI= "https://reqres.in";
		//Getmethod -1
		Response res = RestAssured.get("https://reqres.in/api/users?page=2");
		
		//Getmethod-2
		//given().get("/api/users?page=2").then().header("Content-Type", "application/json; charset=utf-8").log().all();
		
		ValidatableResponse response = 
				given()
		.get("/api/unknown")
		//.then().assertThat().body(matchesJsonSchemaInClasspath ("schema.json")).statusCode(200);
		
		.then()
		.assertThat()
		.body("data[1].name", equalTo("fuchsia rose"))
		
		//.log().all().extract().response().asPrettyString()
		;
		System.out.println(response.log().all().extract().response().toString());
		
		
	}
	
	
	
	
	
	//@Test
	//https://reqres.in/api/users?page=2&ID-5
	public void pathQuery_param() {

		given()   //Prerequisite
			.pathParam("path", "users")
			.queryParam("page", 2)
			.queryParam("id", 7)
			
		.when()   //Action
			.get("https://reqres.in/api/{path}")
		
		.then()
		.log().all()
		;  //Validation and Assretion
			
	}
	//@Test
	public void Assertions() {
		
		RestAssured.baseURI = "https://reqres.in";
		
		
		Response res = 
				given()
				.when()
				.get("/api/users?page=2&ID-5");
		int code = res.getStatusCode();
		System.out.println(code);
		
		Assert.assertEquals(code,200);
		Object s = res.jsonPath().get("data");
		System.out.println(s);
		
		//Assert.assertEquals(s, "https://reqres.in/img/faces/9-image.jpg");
		
		//Getting maiid
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.get("");
		//JSONObject ob = new JSONObject(res.asString());
		
		
		
		
	}
	
	
	//@Test
	public void newPOst() {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "morpheus");
		map.put("job", "leader");
		
		ValidatableResponse res =    RestAssured.given()
		.contentType(ContentType.JSON)
		.body(map)
		.when()
		.post("/api/users")
		.then()
		.statusCode(201)
		.log().all();
		
		System.out.println(res.toString());
		
	}
	
	
	
	
	
	
	
	
	
}
