package com.xm.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.xm.constants.Constants.DEFAULT_TIMEOUT;

@Component
public class Driver {

    private static Environment environment;
    @Autowired
    public Driver(Environment environment) {
        Driver.environment = environment;
    }

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public static void closeWebDriver(){
        if(getDriverInstance() != null){
            getDriverInstance().quit();
            driver.remove();
        }
    }

    public static WebDriver getDriverInstance() {
        if(driver.get() == null){
            setDriver(environment.getBrowser());
        }
        return driver.get();
    }

    private static void setDriver(String browser){
        switch (browser){
            case "CH":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;
            //TODO could be added inits for other browser types
        }
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT));
    }
}
