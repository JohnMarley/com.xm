package com.xm.stepdefs.mobiledefs;

import com.xm.pages.mobile.homepage.HomeMobilePage;
import com.xm.stepdefs.CommonsSteps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;

public class HomeMobileSteps extends CommonsSteps {

    @Autowired
    private HomeMobilePage homeMobilePage;


    @Then("'Home' page is displayed in mobile mode and matches to mockup")
    public void homePageIsDisplayed() {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(homeMobilePage.isMainLogoDisplayed())
                    .as("XM main logo is not displayed")
                    .isTrue();
            softAssertions.assertThat(homeMobilePage.isMenuLinkDisplayed())
                    .as("Menu link is not displayed")
                    .isTrue();
        });
    }

    @When("user opens the left side 'Menu' in mobile view mode")
    public void openMenu(){
        homeMobilePage.menuLinkClick();
    }

}
