package com.xm.pages;

import com.xm.core.Driver;
import org.openqa.selenium.support.PageFactory;


public class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.getDriverInstance(), this);
    }
}
