package com.xm.pages;

import com.xm.core.Driver;
import com.xm.decorator.CustomFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public BasePage() {
        PageFactory.initElements(new CustomFieldDecorator(Driver.getDriverInstance()), this);
    }
}
