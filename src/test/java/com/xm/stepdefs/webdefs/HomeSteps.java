package com.xm.stepdefs.webdefs;

import com.xm.core.Driver;
import com.xm.pages.web.homepage.HomePage;
import com.xm.stepdefs.CommonsSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.Dimension;
import org.springframework.beans.factory.annotation.Autowired;

public class HomeSteps extends CommonsSteps {

    @Autowired
    private HomePage homePage;


    @Given("user opens 'Home' page with {string}")
    public void openHomePage(String browserSize) {
        if ("max".equals(browserSize)) {
            Driver.getDriverInstance().manage().window().maximize();
        } else {
            int width = Integer.parseInt(browserSize.split("\\sx\\s")[0]);
            int height = Integer.parseInt(browserSize.split("\\sx\\s")[1]);
            Driver.getDriverInstance().manage().window().setSize(new Dimension(width, height));
        }
        Driver.getDriverInstance().get(environment.getBaseUrl());
        if(homePage.isCookieModalDisplayed()){
            homePage.acceptCookiesButtonClick();
        }
    }

    @Then("'Home' page is displayed and matches to mockup")
    public void homePageIsDisplayed() {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(homePage.isHeaderButtonsDisplayed())
                    .as("Header buttons section is not displayed")
                    .isTrue();
            softAssertions.assertThat(homePage.getHeaderButtonsText())
                    .as("Not all header buttons are displayed")
                    .containsExactly("MEMBER LOGIN", "LIVE CHAT", "CONTACT", "OPEN AN ACCOUNT", "ENGLISH");
            softAssertions.assertThat(homePage.isMainLogoDisplayed())
                    .as("XM main logo is not displayed")
                    .isTrue();
            softAssertions.assertThat(homePage.isMainNavBarDisplayed())
                    .as("Main navigation panel is not displayed")
                    .isTrue();
            softAssertions.assertThat(homePage.getMainNavBarItemsText())
                    .as("Not all tabs from the Main navigation menu are displayed")
                    .containsExactly("HOME", "TRADING", "PLATFORMS", "RESEARCH & EDUCATION", "PROMOTIONS", "ABOUT US", "PARTNERSHIPS");
        });
    }

    @Then("'Home' page is displayed in mobile view mode and matches to mockup")
    public void homePageMobileModeIsDisplayed() {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(homePage.isHeaderButtonsDisplayed())
                    .as("Header buttons section is not displayed")
                    .isTrue();
            softAssertions.assertThat(homePage.getHeaderButtonsText())
                    .as("Not all header buttons are displayed")
                    .containsExactly("MEMBER LOGIN", "LIVE CHAT", "CONTACT", "OPEN AN ACCOUNT", "ENGLISH");
            softAssertions.assertThat(homePage.isMainLogoDisplayed())
                    .as("XM main logo is not displayed")
                    .isTrue();
            softAssertions.assertThat(homePage.isMainNavBarDisplayed())
                    .as("Main navigation panel is not displayed")
                    .isTrue();
            softAssertions.assertThat(homePage.getMainNavBarItemsText())
                    .as("Not all tabs from the Main navigation menu are displayed")
                    .containsExactly("HOME", "TRADING", "PLATFORMS", "RESEARCH & EDUCATION", "PROMOTIONS", "ABOUT US", "PARTNERSHIPS");
        });
    }

}
