package com.xm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class HomePage extends BasePage {


    @Lazy
    @FindBy(xpath = ".//ul[@id='main-nav']//li[contains(@class,'home')]")
    private WebElement homeNavMenu;

    public boolean isHomeNavDisplayed(){
        return homeNavMenu.isDisplayed();
    }

}
