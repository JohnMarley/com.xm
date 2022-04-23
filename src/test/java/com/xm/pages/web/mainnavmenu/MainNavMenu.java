package com.xm.pages.web.mainnavmenu;

import com.xm.core.Driver;
import com.xm.pages.web.homepage.HomePage;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
public class MainNavMenu extends HomePage {

    private static final String MAIN_MENU_OPTIONS = ".//li[contains(@class,'main_nav_%s')]";
    private static final String HOME = "home";
    private static final String RESEARCH = "research";


    private void navigateTo(String menuOption){
        Driver.getDriverInstance()
                .findElement(By.xpath(String.format(MAIN_MENU_OPTIONS, menuOption)))
                .click();
    }

    public void navigateToHomePage(){
        navigateTo(HOME);
    }

    public void navigateToResearchEducationPage(){
        navigateTo(RESEARCH);
    }
}
