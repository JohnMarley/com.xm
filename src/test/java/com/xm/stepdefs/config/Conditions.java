package com.xm.stepdefs.config;

import com.xm.core.Config;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = Config.class)
public class Conditions {

    @Before
    public void setUp(){}

    @After
    public void afterScenario() {}
}
