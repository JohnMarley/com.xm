package com.xm.stepdefs.webdefs;

import com.xm.pages.web.mainnavmenu.MainNavMenu;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class MainNavMenuSteps {

    @Autowired
    private MainNavMenu mainNavMenu;


    @When("^user navigates to \"(HOME|RESEARCH & EDUCATION)\" page$")
    public void navigateByMenu(String menuOption){
        switch (menuOption){
            case "HOME":
                mainNavMenu.navigateToHomePage();
                break;
            case "RESEARCH & EDUCATION":
                mainNavMenu.navigateToResearchEducationPage();
                break;
        }
    }
}
