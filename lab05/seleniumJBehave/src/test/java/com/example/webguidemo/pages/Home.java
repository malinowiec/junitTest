package com.example.webguidemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

public class Home extends WebDriverPage {

	public Home(WebDriverProvider driverProvider) {
		super(driverProvider);
	}

	
	private final static String LOGIN_LINK = "mlogin";

	
	public void open() {
		get("http://demotywatory.pl");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public void clickLoginLink(){
		findElement(By.id(LOGIN_LINK)).click();
	}
	
	public void clickLogoutLink(){
		findElement(By.cssSelector("#mavatar > span")).click();
		findElement(By.linkText("Wyloguj")).click();
		}

	

	
}
