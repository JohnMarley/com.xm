package com.xm.stepdefs.webdefs;

import com.xm.pages.web.researcheducation.ResearchEducationPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;

public class ResearchEducationSteps {

    @Autowired
    private ResearchEducationPage researchEducationPage;

    @Then("'Research & Education' page is displayed and matches to mockup")
    public void isResearchPageDisplayed(){
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(researchEducationPage.isResearchBlockDisplayed())
                    .as("Research block is not displayed")
                    .isTrue();
            softAssertions.assertThat(researchEducationPage.isLearningCenterBlockDisplayed())
                    .as("Learning Center block is not displayed")
                    .isTrue();
            softAssertions.assertThat(researchEducationPage.isToolsBlockDisplayed())
                    .as("Tools block is not displayed")
                    .isTrue();
        });
    }

    @When("user navigates to 'Economic Calendar' page")
    public void navigateToEconomicCalendarPage(){
        researchEducationPage.economicCalendarClick();
    }
}
