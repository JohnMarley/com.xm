package com.xm.stepdefs.mobiledefs;

import com.xm.pages.mobile.homepage.HomeMobilePage;
import com.xm.pages.mobile.navmenu.NavMenuPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

public class NavMenuMobileSteps {

    @Autowired
    private HomeMobilePage homeMobilePage;

    @Autowired
    private NavMenuPage navMenuPage;


    @Then("left side menu in mobile mode has the options:")
    public void menuOptions(DataTable dataTable){
        Assertions.assertThat(navMenuPage.getMenuOptionsText())
                .as("Not all menu options are displayed")
                .isEqualTo(dataTable.asList());
    }

    @When("^user navigates to \"(RESEARCH & EDUCATION)\" page mobile mode$")
    public void navigateByMenu(String menuOption){
        switch (menuOption){
            case "RESEARCH & EDUCATION":
                navMenuPage.openMenuOption(menuOption);
                break;
        }
    }

    @When("^user opens \"(Economic Calendar)\" page mobile mode$")
    public void openByMenu(String menuOption){
        switch (menuOption){
            case "Economic Calendar":
                navMenuPage.openResearchOption(menuOption);
                break;
        }
    }
}
