//package stepDefinition;
//
//import org.junit.Assert;
//
//import apiEngine.model.Book;
//import apiEngine.model.request.AddBooksRequest;
//import apiEngine.model.request.AuthorizationRequest;
//import apiEngine.model.request.ISBN;
//import apiEngine.model.request.RemoveBookRequest;
//import apiEngine.model.responses.Books;
//import apiEngine.model.responses.Token;
//import apiEngine.model.responses.UserAccount;
//import apiEngne.Endpoints;
//import apiEngne.IRestResponse;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.restassured.response.Response;
//
//public class Steps {
//
//    private static final String USER_ID = "c7641c6e-5e38-40b0-bcd0-3236e0e652c5";    
//    private Response response;
//    private IRestResponse<UserAccount> userAccountResponse;
//    private Book book;
//    private final String BaseUrl = "https://bookstore.toolsqa.com";
//    private Endpoints endPoints;
//    
//    @Given("^I am an authorized user$")
//    public void iAmAnAuthorizedUser() {
//    	endPoints = new Endpoints(BaseUrl);
//    	AuthorizationRequest authRequest = new AuthorizationRequest("Acha@123", "Admin@123");
//    	endPoints.authenticateUser(authRequest);
//    }
//    
//    
//    @Given("^A list of books are available$")
//    public void listOfBooksAreAvailable() {   	    	
//    	IRestResponse<Books> booksResponse = endPoints.getBooks();
//    	book = booksResponse.getBody().books.get(0);
//    }
//
//    @When("^I add a book to my reading list$")
//    public void addBookInList() {
//    	
//        ISBN isbn = new ISBN(book.isbn);
//        AddBooksRequest addBooksRequest = new AddBooksRequest(USER_ID, isbn);
//        userAccountResponse = endPoints.addBook(addBooksRequest);
//    }
//
//    @Then("^The book is added$")
//    public void bookIsAdded() {
//        
//    	Assert.assertTrue(userAccountResponse.isSuccessful());
//        Assert.assertEquals(201, userAccountResponse.getStatusCode());
//
//        Assert.assertEquals(USER_ID, userAccountResponse.getBody().userId);
//        Assert.assertEquals(book.isbn, userAccountResponse.getBody().books.get(0).isbn);
//    }
//
//    @When("^I remove a book from my reading list$")
//    public void removeBookFromList() {
//
//        RemoveBookRequest removeBookRequest = new RemoveBookRequest(USER_ID, book.isbn);
//        response = endPoints.removeBook(removeBookRequest);
//    }
//
//    @Then("^The book is removed$")
//    public void bookIsRemoved() {
//    	
//        Assert.assertEquals(204, response.getStatusCode());
//
//        userAccountResponse = endPoints.getUserAccount(USER_ID);
//        Assert.assertEquals(200, userAccountResponse.getStatusCode());
//        
//        Assert.assertEquals(0, userAccountResponse.getBody().books.size());
//    }
//
//}