package com.xm.stepdefs;

import com.xm.constants.Routes;
import com.xm.core.Environment;
import com.xm.utils.CalendarUtils;
import com.xm.utils.WaitUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonsSteps {

    @Autowired
    protected Environment environment;
    @Autowired
    protected WaitUtils waitUtils;
    @Autowired
    protected Routes routes;
    @Autowired
    protected CalendarUtils calendarUtils;

}
