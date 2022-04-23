package com.xm.core;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Getter
@PropertySource(value = "classpath:application-${env:dev}.properties")
public class Environment {

    @Value("${baseUrl}")
    private String baseUrl;

    @Value("${browser}")
    private String browser;

    @PostConstruct
    public void init(){ browser = browser.equals("${BROWSER_TYPE}")?"CH": browser; }

}
