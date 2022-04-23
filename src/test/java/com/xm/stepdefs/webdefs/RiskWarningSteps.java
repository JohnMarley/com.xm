package com.xm.stepdefs.webdefs;

import com.xm.core.Driver;
import com.xm.pages.web.riskwarning.RiskDisclosuresPage;
import com.xm.stepdefs.CommonsSteps;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RiskWarningSteps extends CommonsSteps {

    @Autowired
    private RiskDisclosuresPage riskDisclosuresPage;


    @Then("user verifies that 'Risk Disclosure' page is displayed")
    public void isRiskPageDisplayed(){
        var tabs = List.copyOf(Driver.getDriverInstance().getWindowHandles());
        Driver.getDriverInstance().switchTo().window(tabs.get(1));
        waitUtils.waitRedirect(routes.getRiskPdf());
        Assertions.assertThat(riskDisclosuresPage.isRiskPdfDisplayed())
                .as("Risk page header is not displayed")
                .isTrue();
        Driver.getDriverInstance().close();
        Driver.getDriverInstance().switchTo().window(tabs.get(0));
    }
}
