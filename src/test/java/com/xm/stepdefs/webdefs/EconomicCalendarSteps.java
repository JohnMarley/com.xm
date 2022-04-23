package com.xm.stepdefs.webdefs;

import com.xm.pages.web.researcheducation.research.EconomicCalendarIframe;
import com.xm.pages.web.researcheducation.research.EconomicCalendarPage;
import com.xm.stepdefs.CommonsSteps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;

public class EconomicCalendarSteps extends CommonsSteps {

    @Autowired
    private EconomicCalendarPage economicCalendarPage;
    @Autowired
    private EconomicCalendarIframe economicCalendarIframe;


    @Then("user verifies that 'Economic Calendar' page is displayed")
    public void isEconomicCalendarDisplayed(){
        waitUtils.waitRedirect(routes.getEconomicCalendar());
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(economicCalendarPage.isCalendarTitleDisplayed())
                    .as("Economic Calendar title is not displayed")
                    .isTrue();
            softAssertions.assertThat(economicCalendarPage.isEconomicCalendarDisplayed())
                    .as("Economic Calendar is not displayed")
                    .isTrue();
        });
    }

    @Then("^user clicks on \"(Yesterday|Today|Tomorrow|This Week)\" filter$")
    public void applyCalendarFilters(String filter){
        switch (filter){
            case "Yesterday":
                economicCalendarIframe.yesterdayFilterClick();
                break;
            case "Today":
                economicCalendarIframe.todayFilterClick();
                break;
            case "Tomorrow":
                economicCalendarIframe.tomorrowFilterClick();
                break;
            case "This Week":
                economicCalendarIframe.thisWeekFilterClick();
                break;
        }
    }

    @Then("^user verifies that correct date for \"(Yesterday|Today|Tomorrow|This Week)\" filter is applied$")
    public void isCorrectDateApplied(String filter) {
        var dayCount = 0;
        switch (filter) {
            case "Yesterday":
                dayCount = -1;
                break;
            case "Today":
                break;
            case "Tomorrow":
                dayCount = 1;
                break;
        }
        var days = dayCount;
        var appliedDateRange = economicCalendarIframe.getDateRangeWidget();
        var fromDate = appliedDateRange.split("\\s-\\s")[0];
        var toDate = appliedDateRange.split("\\s-\\s")[1];
        SoftAssertions.assertSoftly(softAssertions -> {

            if("This Week".equals(filter)){
                var expectedStartWeek = calendarUtils.getCurrentWeekStartEndDate().get(0);
                var expectedEndWeek = calendarUtils.getCurrentWeekStartEndDate().get(1);
                var actualStartWeek = calendarUtils.getDateFromSting(new SimpleDateFormat("dd/MM/yyyy"), fromDate);
                var actualEndWeek = calendarUtils.getDateFromSting(new SimpleDateFormat("dd/MM/yyyy"), toDate);
                softAssertions.assertThat(DateUtils.isSameDay(expectedStartWeek, actualStartWeek))
                        .as("Applied date is not correct. Expected: " + expectedStartWeek + " was: " + actualStartWeek)
                        .isTrue();
                softAssertions.assertThat(DateUtils.isSameDay(expectedEndWeek, actualEndWeek))
                        .as("Applied date is not correct. Expected: " + expectedEndWeek + " was: " + actualEndWeek)
                        .isTrue();
            } else {
                var actualStartDate = calendarUtils
                        .getDateFromSting(new SimpleDateFormat("dd/MM/yyyy"), fromDate);
                var expectedStartDate = calendarUtils.addDaysFromCurrentDate(days);
                var actualEndDate = calendarUtils
                        .getDateFromSting(new SimpleDateFormat("dd/MM/yyyy"), toDate);
                softAssertions.assertThat(DateUtils.isSameDay(actualStartDate, expectedStartDate))
                        .as("Applied date is not correct. Expected: " + expectedStartDate.getTime() + " was: " + actualStartDate.getTime())
                        .isTrue();
                softAssertions.assertThat(DateUtils.isSameDay(actualEndDate, expectedStartDate))
                        .as("Applied date is not correct. Expected: " + expectedStartDate.getTime() + " was: " + actualStartDate.getTime())
                        .isTrue();
            }
        });
    }

    @When("user opens 'Risk Disclosure' link")
    public void userOpensHereDisclaimerLink() {
        economicCalendarPage.hereDisclaimerLinkClick();
    }
}
