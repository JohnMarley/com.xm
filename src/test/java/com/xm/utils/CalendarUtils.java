package com.xm.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


@Component
public class CalendarUtils {

    public Calendar getDateFromSting(SimpleDateFormat simpleDateFormat, String date){
        Date result = null;
        try {
            result = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        var c = Calendar.getInstance();
        c.setTime(Objects.requireNonNull(result));
        return c;
    }

    public Calendar addDaysFromCurrentDate(int addDays){
        var c = Calendar.getInstance();
        c.add(Calendar.DATE, addDays);
        return c;
    }

    public List<Calendar> getCurrentWeekStartEndDate(){
        var currentDate = Calendar.getInstance(Locale.US);
        var firstDayOfWeek = currentDate.getFirstDayOfWeek();

        var startDate = Calendar.getInstance(Locale.US);
        startDate.setTime(currentDate.getTime());
        var days = (startDate.get(Calendar.DAY_OF_WEEK) + 7 - firstDayOfWeek) % 7;
        startDate.add(Calendar.DATE, -days);

        Calendar endDate = Calendar.getInstance(Locale.US);
        endDate.setTime(startDate.getTime());
        endDate.add(Calendar.DATE, 6);

        return List.of(startDate, endDate);
    }

}
