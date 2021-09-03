package com.university.skillsmatrix.helper;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateHelperTests {
    //Tested on 03/09/2021
    //Change expected manually to today's date when testing
    @Test
    public void test01DateHelperReturnsCorrectDate(){
        assertEquals("2021-09-03", DateHelper.getCurrentDate().toString());
    }
    @Test
    public void test02DateHelperReturnsCorrectDate(){
        assertEquals("2022-09-03", DateHelper.getAYearFromToday().toString());
    }

}
