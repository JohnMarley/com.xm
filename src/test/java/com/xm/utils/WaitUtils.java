package com.xm.utils;

import com.xm.core.Driver;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class WaitUtils {


    public void waitRedirect(String path){
        waitRedirect(path, 20);
    }

    public void waitRedirect(String path, int timeOutInSeconds){
        var wait = new WebDriverWait(Driver.getDriverInstance(), Duration.ofSeconds(timeOutInSeconds));
        try {
            wait.until(p -> Driver.getDriverInstance().getCurrentUrl().contains(path));
        } catch (TimeoutException e){
            Assertions.fail("Expected condition failed: waiting for redirect to URL: "
            + path + " (tried for " + timeOutInSeconds + " second(s)");
        }
    }
}
