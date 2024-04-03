package stepDefinition;

import apiEngne.Endpoints;
import cucumber.ScenarioContext;
import cucumber.TestContext;

public class BaseStep {

	private Endpoints endPoints;
	private ScenarioContext scenarioContext;

	public BaseStep(TestContext testContext) {
		endPoints = testContext.getEndPoints();
		scenarioContext = testContext.getScenarioContext();
	}

	public Endpoints getEndPoints() {
		return endPoints;
	}

	public ScenarioContext getScenarioContext() {
		return scenarioContext;
	}
}
