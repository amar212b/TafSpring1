package com.project.stepdefinitions;

import com.project.context.ScenarioContext;
import com.project.pages.LoginPage;
import com.project.utils.AssertionUtils;
import com.project.utils.ExpressionResolver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.project.context.TestContext;


public class LoginSteps {

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private ExpressionResolver expressionResolver;

    @Autowired
    private ScenarioContext scenarioContext;

    @Autowired
    private TestContext testContext;

//    @Autowired
//    public LoginSteps(LoginPage loginPage, ExpressionResolver expressionResolver,ScenarioContext scenarioContext) {
//        this.loginPage = loginPage;
//        this.expressionResolver = expressionResolver;
//        this.scenarioContext = scenarioContext;
//    }

    @Given("I navigate to the login page")
    public void iNavigateToTheLoginPage() {
        testContext.navigateToApplication();  // Opens the URL
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        String resolvedUsername = expressionResolver.resolve(username);
        String resolvedPassword = expressionResolver.resolve(password);
        LoginPage loginPage = testContext.getPageManager().getLoginPage();

        loginPage.login(resolvedUsername, resolvedPassword);
    }

    @Then("I should see the {string}")
    public void iShouldSeeThe(String expectedResult) {
//        String resolvedExpectedResult = expressionResolver.resolve(expectedResult);
        if (expectedResult.equalsIgnoreCase("dashboard page")) {
            AssertionUtils.assertTrue(loginPage.isDashboardVisible(), "Dashboard page is visible");
        } else if (expectedResult.equalsIgnoreCase("login error message")) {
            AssertionUtils.assertTrue(loginPage.isLoginErrorMessageVisible(), "Login error message is visible");
        } else {
            throw new IllegalArgumentException("Unexpected expected result: " + expectedResult);
        }
    }
}
