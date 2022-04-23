package com.xm.pages.web.riskwarning;

import com.xm.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class RiskDisclosuresPage extends BasePage {

    @Lazy
    @FindBy(xpath = ".//embed[@type='application/pdf']")
    private WebElement riskPdfDoc;


    public boolean isRiskPdfDisplayed(){
        return riskPdfDoc.isDisplayed();
    }
}
