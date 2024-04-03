package stepDefinition;

import apiEngine.model.request.AuthorizationRequest;
import cucumber.TestContext;
import io.cucumber.java.en.Given;

public class AccountsSteps extends BaseStep {

	public AccountsSteps(TestContext testContext) {
		super(testContext);
	}

	@Given("^I am an authorized user$")
	public void iAmAnAuthorizedUser() {
		AuthorizationRequest authRequest = new AuthorizationRequest("sujaybp", "Sujay@123");
		getEndPoints().authenticateUser(authRequest);
	}

}
