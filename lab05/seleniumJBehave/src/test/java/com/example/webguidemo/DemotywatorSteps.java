package com.example.webguidemo;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;

import static org.junit.Assert.*;

public class DemotywatorSteps {
	
	private final Pages pages;

	public DemotywatorSteps(Pages pages) {
		this.pages = pages;
	}
	
	@Given("user is on Home page")
    public void userIsOnHomePage(){        
        pages.home().open();        
    }
    
    @When("user opens Login link")
    public void userClicksOnLoginLink(){        
        pages.home().clickLoginLink();
    }
 
    @Then("Login page is shown")
    public void loginPageIsShown(){
       assertEquals("Demotywatory.pl", pages.login().getTitle());
    }	
    
    @When("user enters username malinowiec and password malin")
    public void userInputWrongPassLink(){        
        pages.login().clickWrongPassLink();
    }
    
    @Then("Login error is shown")
    public void loginErrorIsShown(){
       assertEquals("Logowanie nie powiod³o siê. SprawdŸ ponownie wpisywane has³o.", pages.login().findElement(By.id("messages")).getText());
    }	
    
    @When("user enters username malinowiec and password dellene")
    public void userInputPassLink(){        
        pages.login().clickPassLink();
    }
    
    @Then("Login info is shown")
    public void loginGoodIsShown(){
       assertEquals("Witaj w serwisie, malinowiec", pages.login().findElement(By.id("messages")).getText());
    }	

    @When("user clicks Logout link")
    public void userLogoutLink(){        
        pages.home().clickLogoutLink();
    }
    
    @Then("Logout info is shown")
    public void logoutInfoIsShown(){
       assertEquals("Wylogowano. Zapraszamy kiedyœ ponownie.", pages.home().findElement(By.id("messages")).getText());
    }
    
}
