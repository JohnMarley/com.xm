package com.xm.pages.mobile.homepage;

import com.xm.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class HomeMobilePage extends BasePage {

    @Lazy
    @FindBy(xpath = ".//div[@class='center-logo']//a[@class='navbar-brand logo' and .//img]")
    private WebElement navbarMainLogo;

    @Lazy
    @FindBy(xpath = ".//button[@class='toggleLeftNav']")
    private WebElement menuLink;


    public boolean isMainLogoDisplayed() {
        return navbarMainLogo.isDisplayed();
    }

    public boolean isMenuLinkDisplayed(){
        return menuLink.isDisplayed();
    }

    public void menuLinkClick(){
        menuLink.click();
    }

}
