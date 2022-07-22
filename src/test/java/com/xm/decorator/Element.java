package com.xm.decorator;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.xm.core.Driver.getDriverInstance;

@Slf4j
public abstract class Element {

    WebElement webElement;
    By by;
    String name = "SET ELEMENT NAME";

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }

    public Element(WebElement webElement, String name, By by) {
        this.webElement = webElement;
        this.name = name;
        this.by = by;
    }

    public Element(By by){
        this.webElement = getDriverInstance().findElement(by);
        setName();
    }

    public void click(){
        log.info("ELEMENT CLICK!!!");
        System.out.println("ELEMENT CLICK!!! " + this.name);
        webElement.click();
    }

    private void setName(){
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        this.name = ste[4].getMethodName();
    }

}
