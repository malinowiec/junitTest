package com.example.webguidemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

public class Login extends WebDriverPage{
	
	public Login(WebDriverProvider driverProvider) {
		super(driverProvider);		
	}

	public void open() {
		get("http://demotywatory.pl/login");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void clickWrongPassLink(){
	findElement(By.xpath("(//input[@name='username'])[3]")).sendKeys("malinowiec");
	findElement(By.xpath("(//input[@name='password'])[3]")).sendKeys("malin");
	findElement(By.xpath("(//input[@value='Zaloguj'])[2]")).click();
	}
	
	public void clickPassLink(){
	findElement(By.xpath("(//input[@name='username'])[3]")).sendKeys("malinowiec");
	findElement(By.xpath("(//input[@name='password'])[3]")).sendKeys("dellene");
	findElement(By.xpath("(//input[@value='Zaloguj'])[2]")).click();
	}
	

}