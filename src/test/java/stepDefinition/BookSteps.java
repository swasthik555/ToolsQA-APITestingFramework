package stepDefinition;

import apiEngine.model.Book;
import apiEngine.model.request.AddBooksRequest;
import apiEngine.model.request.ISBN;
import apiEngine.model.request.RemoveBookRequest;
import apiEngine.model.responses.Books;
import apiEngine.model.responses.UserAccount;
import apiEngne.IRestResponse;
import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class BookSteps extends BaseStep {

	public BookSteps(TestContext testContext) {
		super(testContext);
	}

	@Given("^A list of books are available$")
	public void listOfBooksAreAvailable() {
		IRestResponse<Books> booksResponse = getEndPoints().getBooks();
		Book book = booksResponse.getBody().books.get(0);
		getScenarioContext().setContext(Context.BOOK, book);
	}

	@When("^I add a book to my reading list$")
	public void addBookInList() {
		Book book = (Book) getScenarioContext().getContext(Context.BOOK);
		String userId = (String) getScenarioContext().getContext(Context.USER_ID);

		ISBN isbn = new ISBN(book.isbn);
		AddBooksRequest addBooksRequest = new AddBooksRequest(userId, isbn);

		IRestResponse<UserAccount> userAccountResponse = getEndPoints().addBook(addBooksRequest);
		getScenarioContext().setContext(Context.USER_ACCOUNT_RESPONSE, userAccountResponse);
		
	}

	@When("^I remove a book from my reading list$")
	public void removeBookFromList() {
		Book book = (Book) getScenarioContext().getContext(Context.BOOK);
		String userId = (String) getScenarioContext().getContext(Context.USER_ID);

		RemoveBookRequest removeBookRequest = new RemoveBookRequest(userId, book.isbn);

		Response response = getEndPoints().removeBook(removeBookRequest);
		getScenarioContext().setContext(Context.BOOK_REMOVE_RESPONSE, response);
	}

}
