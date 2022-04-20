package com.xm;

import com.xm.constants.Constants;
import com.xm.core.Driver;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {Constants.PRETTY_PLUGIN},
        features = Constants.RESOURCES_PATH,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@az"
)
public class RunnerTest {

    @BeforeClass
    public static void before() {
        Driver.getChromeDriverInstance();
    }

    @AfterClass
    public static void after() {
        Driver.getChromeDriverInstance().quit();
    }
}
