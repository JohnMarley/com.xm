package com.xm.pages.web.homepage;

import com.xm.decorator.Button;
import com.xm.pages.BasePage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.xm.core.Driver.getDriverInstance;

@Component
public class HomePage extends BasePage {

    @Lazy
    @FindBy(xpath = ".//div[@id='navigation-collapse']//a[@class='navbar-brand logo' and .//img]")
    private WebElement navbarMainLogo;

    @Lazy
    @FindBy(xpath = ".//ul[@id='main-nav' and contains(@class, 'nav-logo')]")
    private WebElement mainNavBar;

    @Lazy
    @FindBy(xpath = ".//div[@class='header-top']//ul[contains(@class,'buttons-nav')]")
    private WebElement headersButtons;

    @Lazy
    @FindBy(xpath = ".//button[@data-dismiss='modal' and .='ACCEPT ALL']")
    private Button acceptCookiesButton;



    public boolean isMainLogoDisplayed() {
        return navbarMainLogo.isDisplayed();
    }

    public boolean isMainNavBarDisplayed(){
        return mainNavBar.isDisplayed();
    }

    public List<String> getMainNavBarItemsText(){
        return mainNavBar.findElements(By.xpath(".//li"))
                .stream()
                .map(WebElement::getText)
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toList());
    }

    public boolean isHeaderButtonsDisplayed(){
        return headersButtons.isDisplayed();
    }

    public List<String> getHeaderButtonsText(){
        return headersButtons.findElements(By.xpath("./li"))
                .stream()
                .map(WebElement::getText)
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toList());
    }

    public boolean isCookieModalDisplayed(){
        boolean result = false;
        var wait = new WebDriverWait(getDriverInstance(), Duration.ofSeconds(5));
        try {
            return wait
                    .pollingEvery(Duration.ofMillis(500))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@id='cookieModal']")))
                    .isDisplayed();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void acceptCookiesButtonClick(){
        acceptCookiesButton.click();
    }

}
