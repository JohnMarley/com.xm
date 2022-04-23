package com.xm.pages.web.researcheducation.research;

import com.xm.core.Driver;
import com.xm.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.xm.constants.Constants.DEFAULT_TIMEOUT;
import static com.xm.core.Driver.getDriverInstance;

@Component
public class EconomicCalendarIframe extends EconomicCalendarPage {

    @Autowired
    private WaitUtils waitUtils;

    @Lazy
    @FindBy(xpath = ".//a[@id='timeFrame_yesterday']")
    private WebElement yesterdayButton;

    @Lazy
    @FindBy(xpath = ".//a[@id='timeFrame_today']")
    private WebElement todayButton;

    @Lazy
    @FindBy(xpath = ".//a[@id='timeFrame_tomorrow']")
    private WebElement tomorrowButton;

    @Lazy
    @FindBy(xpath = ".//a[@id='timeFrame_thisWeek']")
    private WebElement thisWeekButton;

    @Lazy
    @FindBy(xpath = ".//div[@id='widgetField']/div")
    private WebElement dateWidget;

    @Lazy
    @FindBy(xpath = ".//div[@id='economicCalendarLoading' and @style='display: block;']")
    private WebElement dateSpinner;

    private static final String CALENDAR_SPINNER_XPATH = ".//div[@id='economicCalendarLoading' and @style='display: block;']";


    private void activateFiltersIfCollapsed(){
        var filter = Driver.getDriverInstance()
                .findElements(By.xpath(".//li[@class='saveSpace']"));
        if(filter.size()>0 && filter.get(0).isDisplayed()){
            filter.get(0).click();
        }
    }

    public void yesterdayFilterClick(){
        applyFilter(yesterdayButton);
    }

    public void todayFilterClick(){
        applyFilter(todayButton);
    }

    public void tomorrowFilterClick(){
        applyFilter(tomorrowButton);
    }

    public void thisWeekFilterClick(){
        applyFilter(thisWeekButton);
    }

    private void applyFilter(WebElement filter){
        webElementUtils.scrollIntoView(Driver.getDriverInstance(), calendarTitle);
        String parentName = getDriverInstance().getWindowHandle();
        getDriverInstance().switchTo().frame(calendarIframe);
        activateFiltersIfCollapsed();
        filter.click();
        waitForSpinnerDisappearance();
        getDriverInstance().switchTo().window(parentName);
    }

    public String getDateRangeWidget(){
        String parentName = getDriverInstance().getWindowHandle();
        getDriverInstance().switchTo().frame(calendarIframe);
        var result = dateWidget.getText();
        getDriverInstance().switchTo().window(parentName);
        return result;
    }

    private void waitForSpinnerDisappearance() {
        getDriverInstance().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        if (getDriverInstance().findElements(By.xpath(CALENDAR_SPINNER_XPATH)).size() > 0) {
            var wait = new WebDriverWait(getDriverInstance(), Duration.ofSeconds(5));
            wait.pollingEvery(Duration.ofMillis(300))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(CALENDAR_SPINNER_XPATH)));
        }
        getDriverInstance().manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

}
