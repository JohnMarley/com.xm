package com.xm.pages;

import com.xm.core.Driver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
}
