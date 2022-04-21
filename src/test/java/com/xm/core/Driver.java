package com.xm.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Driver {

    private static Environment environment;
    @Autowired
    public Driver(Environment environment) {
        Driver.environment = environment;
    }

    @Getter
    private static WebDriver driver;

    public static void closeWebDriver(){
        if(driver != null){
            driver.quit();
        }
    }

    public static void initWebDriver(){

        switch (environment.getBrowser()){
            case "CH":
                getChromeDriverInstance();
                if(environment.getIsBrowserMaximized()) {
                    driver.manage().window().maximize();
                } else {
                    driver.manage().window().setSize(new Dimension(environment.getBrowserWidth(), environment.getBrowserHeight()));
                }
                //TODO could be added inits for other browser types

        }
    }

    private static WebDriver getChromeDriverInstance() {
        if(!(driver instanceof ChromeDriver)){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        return driver;
    }
}
