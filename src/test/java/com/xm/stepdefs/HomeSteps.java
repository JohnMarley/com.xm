package com.xm.stepdefs;

import com.xm.core.Driver;
import com.xm.core.Environment;
import com.xm.pages.HomePage;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class HomeSteps {

    @Autowired
    private HomePage homePage;
    @Autowired
    private Environment environment;

    @Given("user opens 'Home' page")
    public void openHomePage(){
        Driver.getDriver().get(environment.getBaseUrl());
    }
}
