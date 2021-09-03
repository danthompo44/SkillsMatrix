package com.university.skillsmatrix.helper;

import java.sql.Date;
import java.util.Calendar;

public class DateHelper {
    public static Date getCurrentDate(){
        return new Date(System.currentTimeMillis());
    }

    public static Date getAYearFromToday(){
        Calendar c = Calendar.getInstance();
        c.setTime(getCurrentDate());
        c.add(Calendar.DATE, 365);
        return new Date(c.getTimeInMillis());
    }
}
