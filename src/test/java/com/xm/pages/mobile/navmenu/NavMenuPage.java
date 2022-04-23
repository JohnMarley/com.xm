package com.xm.pages.mobile.navmenu;

import com.xm.core.Driver;
import com.xm.pages.mobile.homepage.HomeMobilePage;
import com.xm.utils.WebElementUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class NavMenuPage extends HomeMobilePage {

    @Autowired
    private WebElementUtils webElementUtils;

    @Lazy
    @FindBy(xpath = ".//ul[@id='main-nav' and contains(@class,'visible')]")
    private WebElement menu;

    @Lazy
    @FindBy(xpath = ".//div[@id='researchMenu' and @aria-expanded='true']")
    private WebElement researchOptions;


    public List<String> getMenuOptionsText(){
        return menu.findElements(By.xpath(".//li"))
                .stream()
                .map(WebElement::getText)
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toList());
    }

    public void openMenuOption(String option){
        var menuOption = menu.findElements(By.xpath("./li"))
                .stream()
                .filter(webElement -> webElement.getText().toLowerCase().contains(option.toLowerCase()))
                .findFirst()
                .orElseThrow();
        webElementUtils.scrollIntoView(Driver.getDriverInstance(), menuOption);
        menuOption.click();
    }

    public void openResearchOption(String option){
        var researchOption = researchOptions.findElements(By.xpath(".//a"))
                .stream()
                .filter(webElement -> webElement.getText().toLowerCase().contains(option.toLowerCase()))
                .findFirst()
                .orElseThrow();
        webElementUtils.scrollIntoView(Driver.getDriverInstance(), researchOption);
        researchOption.click();
    }

}
