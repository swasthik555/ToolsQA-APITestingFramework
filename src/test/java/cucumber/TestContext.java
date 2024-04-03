package cucumber;

import apiEngne.Endpoints;
import dataProvider.ConfigFileReader;
import enums.Context;

public class TestContext {

	private Endpoints endPoints = new Endpoints(ConfigFileReader.getInstance().getBaseUrl());
	private ScenarioContext scenarioContext;

	public TestContext() {
		scenarioContext = new ScenarioContext();
		scenarioContext.setContext(Context.USER_ID, ConfigFileReader.getInstance().getUserID());
	}

	public Endpoints getEndPoints() {
		return endPoints;
	}

	public ScenarioContext getScenarioContext() {
		return scenarioContext;
	}
}
