package com.xm.decorator;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.List;

@Slf4j
public class CustomFieldDecorator extends DefaultFieldDecorator {

    public CustomFieldDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    /**
     * Overriding decorate method for initializing custom elements via PageFactory
     * @param classLoader
     * @param field
     * @return
     */
    @Override
    public Object decorate(ClassLoader classLoader, Field field){
        // keep default WebElements
        if(field.getType().isAssignableFrom(WebElement.class)){
            return super.decorate(classLoader, field);
        }
        // decorate custom elements extend Element class
        else {
            Class<?> decoratableClass = getDecoratableClass(field);

            if(decoratableClass==null){
                return null;
            }
            ElementLocator elementLocator = factory.createLocator(field);
            if (elementLocator == null) {
                return null;
            }
            return createElement(classLoader, elementLocator, decoratableClass, field);
        }
    }

    /**
     * identifies if class of decoratable field marked by @FindBy can be initialized
     * @param field
     * @return a Class object that identifies the declared type for the field represented by this Field object.
     */
    private Class<?> getDecoratableClass(Field field){
        Class<?> clazz = field.getType();
        if(field.isAnnotationPresent(FindBy.class) && !clazz.isAssignableFrom(List.class)) {
            try {
                clazz.getConstructor(WebElement.class);
                return clazz;
            } catch (NoSuchMethodException e) {
                log.debug("class '" + field.getDeclaringClass() + "' for declared field '" + field.getName() + "' does not have constructor with (WebElement webElement) parameter");
            }
        }
        return null;
    }

    /**
     * Instantiate custom element to enable it to work with webDriver
     * @param classLoader
     * @param elementLocator
     * @param clazz
     * @param field
     * @param <T>
     * @return custom decorated element
     */
    protected <T> T createElement(ClassLoader classLoader, ElementLocator elementLocator, Class<T> clazz, Field field) {
        String elementName = "'" + field.getName().replaceAll(String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])",
                "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])"), " ")
                + "' on ";
        FindBy findBy = field.getAnnotation(FindBy.class);
        By by = buildByFromShortFindBy(findBy);
        elementName += field.getDeclaringClass().getSimpleName();

        WebElement webElement = proxyForLocator(classLoader, elementLocator);
        return createInstance(clazz, webElement, elementName, by);

    }

    /**
     * instantiate By class according to passed selector
     * @param findBy
     * @return By object with selector
     */
    protected By buildByFromShortFindBy(@Nonnull FindBy findBy) {
        if (!"".equals(findBy.className())) {
            return By.className(findBy.className());
        } else if (!"".equals(findBy.css())) {
            return By.cssSelector(findBy.css());
        } else if (!"".equals(findBy.id())) {
            return By.id(findBy.id());
        } else if (!"".equals(findBy.linkText())) {
            return By.linkText(findBy.linkText());
        } else if (!"".equals(findBy.name())) {
            return By.name(findBy.name());
        } else if (!"".equals(findBy.partialLinkText())) {
            return By.partialLinkText(findBy.partialLinkText());
        } else if (!"".equals(findBy.tagName())) {
            return By.tagName(findBy.tagName());
        } else {
            return !"".equals(findBy.xpath()) ? By.xpath(findBy.xpath()) : null;
        }
    }

    /**
     * create instanse of custom element
     * @param clazz
     * @param webElement
     * @param name
     * @param by
     * @param <T>
     * @return return object of custom element
     */
    private <T> T createInstance(Class<T> clazz, WebElement webElement, String name, By by){
        T result = null;
        try {
            result = clazz.getConstructor(WebElement.class, String.class, By.class)
                    .newInstance(webElement, name, by);
        } catch (Exception e){
            e.printStackTrace();
            Assertions.fail("WebElement '"+name+"' can't be represented as " + clazz);
        }
        return result;
    }
}
