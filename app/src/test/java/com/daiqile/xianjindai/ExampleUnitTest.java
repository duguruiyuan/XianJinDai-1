package com.daiqile.xianjindai;

import android.content.Context;
import android.util.Log;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        int age = getAge("1994.01.06");
        System.out.print(age + "");
    }

    private Date parse(String strDate) throws ParseException {
        SimpleDateFormat sdf;
        try {
            sdf = new SimpleDateFormat("yyyy.MM.dd");
        } catch (Exception e) {
            e.printStackTrace();
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }
        try {
            return sdf.parse(strDate.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getAge(String strDate) throws Exception {
        Date birthDay = parse(strDate);

        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            } else {
                age--;
            }
        }
        return age;
    }


}