package com.xm.pages.web.researcheducation.research;

import com.xm.core.Driver;
import com.xm.pages.BasePage;
import com.xm.utils.WebElementUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class EconomicCalendarPage extends BasePage {

    @Autowired
    protected WebElementUtils webElementUtils;

    @Lazy
    @FindBy(xpath = ".//h2[contains(text(),'Calendar')]")
    protected WebElement calendarTitle;

    @Lazy
    @FindBy(xpath = ".//div[@class='economic-calendar']/iframe")
    protected WebElement calendarIframe;

    @Lazy
    @FindBy(xpath = ".//div[@id='risk-block']//a[.='Risk Disclosure']")
    private WebElement disclaimerLink;


    public boolean isEconomicCalendarDisplayed(){
        webElementUtils.scrollIntoView(Driver.getDriverInstance(), calendarIframe);
        return calendarIframe.isDisplayed();
    }

    public boolean isCalendarTitleDisplayed(){
        return calendarTitle.isDisplayed();
    }

    public void hereDisclaimerLinkClick(){
        webElementUtils.scrollIntoView(Driver.getDriverInstance(), disclaimerLink);
        disclaimerLink.click();
    }

}
