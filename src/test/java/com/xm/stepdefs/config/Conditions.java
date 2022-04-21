package com.xm.stepdefs.config;

import com.xm.core.Config;
import com.xm.core.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = Config.class)
public class Conditions {

    @Before
    public void setUp(){
        Driver.initWebDriver();
    }

    @After
    public void afterScenario() {
        Driver.closeWebDriver();
    }
}
