package com.xm.stepdefs;

import com.xm.core.Config;
import com.xm.core.Environment;
import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = Config.class)
public class Conditions {

    @Autowired
    private Environment environment;

    @Before
    public void setUp(){}
}
