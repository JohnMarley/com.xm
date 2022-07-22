package com.xm.decorator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Button extends Element{

    public Button(WebElement webElement) {
        super(webElement);
    }

    public Button(By by){
        super(by);
    }

    public Button(WebElement element, String name, By by) {
        super(element, name, by);
    }

    @Override
    public void click(){
        super.click();
    }
}
