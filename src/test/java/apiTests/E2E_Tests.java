package apiTests;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class E2E_Tests {

	public static void main(String[] args) {
		String userID = "5cb46dc4-528e-441a-8ee7-6ede431c32c2";
		String userName = "Swasthi@123";
		String password = "Admin@123";
		String baseUrl = "https://bookstore.toolsqa.com";

	        RestAssured.baseURI = baseUrl;
	        RequestSpecification request = RestAssured.given();


	        //Step - 1
	        //Test will start from generating Token for Authorization
	        request.header("Content-Type", "application/json");

	        Response response = request.body("{ \"userName\":\"" + userName + "\", \"password\":\"" + password + "\"}").post("/Account/v1/GenerateToken");

	        Assert.assertEquals(response.getStatusCode(), 200);

	        String jsonString = response.asString();
	        Assert.assertTrue(jsonString.contains("token"));

	        //This token will be used in later requests
	        String token = JsonPath.from(jsonString).get("token");
	        
	 System.out.println("Token ="+token);


	        //Step - 2
	        // Get Books - No Author is required for this.
	        response = request.get("/BookStore/v1/Books");

	        Assert.assertEquals(200, response.getStatusCode());

	        jsonString = response.asString();
	        List<Map<String, String>> books = JsonPath.from(jsonString).get("books");
	        Assert.assertTrue(books.size() > 0);

	         //This bookId will be used in later requests, to add the book with respective isbn
	        String bookId = books.get(0).get("isbn");
	        
	 System.out.println("isbn ="+bookId);


	        //Step - 3
	        // Add a book - with Author
	        //The token we had saved in the variable before from response in Step 1, 
	        //we will be passing in the headers for each of the succeeding request
	        request.header("Authorization", "Bearer " + token)
	                .header("Content-Type", "application/json");

	        response = request.body("{ \"userId\": \"" + userID + "\", " +
	                "\"collectionOfIsbns\": [ { \"isbn\": \"" + bookId + "\" } ]}")
	                .post("/BookStore/v1/Books");

	        Assert.assertEquals(201, response.getStatusCode());


	        //Step - 4
	        // Delete a book - with Auth
	        request.header("Authorization", "Bearer " + token)
	                .header("Content-Type", "application/json");

	        response = request.body("{ \"isbn\": \"" + bookId + "\", \"userId\": \"" + userID + "\"}")
	                .delete("/BookStore/v1/Book");

	        Assert.assertEquals(204, response.getStatusCode());

	        //Step - 5
	        // Get User
	        request.header("Authorization", "Bearer " + token)
	                .header("Content-Type", "application/json");

	        response = request.get("/Account/v1/User/" + userID);
	        Assert.assertEquals(200, response.getStatusCode());

	        jsonString = response.asString();
	        List<Map<String, String>> booksOfUser = JsonPath.from(jsonString).get("books");
	        Assert.assertEquals(0, booksOfUser.size());
	}

}
