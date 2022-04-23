package com.xm.pages.web.researcheducation;

import com.xm.core.Driver;
import com.xm.pages.BasePage;
import com.xm.utils.WebElementUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ResearchEducationPage extends BasePage {

    @Autowired
    private WebElementUtils webElementUtils;

    @Lazy
    @FindBy(xpath = ".//div[@class='block' and .//li[contains(@class,'research')]]")
    private WebElement researchBlock;

    @Lazy
    @FindBy(xpath = ".//div[@class='block' and .//li[contains(@class,'research')]]//li[./a[contains(@href,'Calendar')]]")
    private WebElement economicCalendarLink;

    @Lazy
    @FindBy(xpath = ".//div[@class='block' and .//li[contains(@class,'tutorials')]]")
    private WebElement learningCenterBlock;

    @Lazy
    @FindBy(xpath = ".//div[@class='block' and .//li[contains(@class,'signals')]]")
    private WebElement toolsBlock;


    public boolean isResearchBlockDisplayed(){
        return researchBlock.isDisplayed();
    }

    public boolean isLearningCenterBlockDisplayed(){
        return learningCenterBlock.isDisplayed();
    }

    public boolean isToolsBlockDisplayed(){
        return toolsBlock.isDisplayed();
    }

    public void economicCalendarClick(){
        webElementUtils.scrollIntoView(Driver.getDriverInstance(), economicCalendarLink);
        economicCalendarLink.click();
    }

}
