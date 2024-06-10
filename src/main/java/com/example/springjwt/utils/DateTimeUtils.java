package com.example.springjwt.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class DateTimeUtils {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static Date createDateFromDateString(String dateString) throws ParseException {
        if (StringUtils.isNotBlank(dateString)) {
            return DATE_FORMAT.parse(dateString);
        } else {
            throw new ParseException(dateString, 0);
        }
    }

    public static String createDateStringFromDate(Date date) {
        if (null == date) {
            return DATE_FORMAT.format(new Date());
        }
        return DATE_FORMAT.format(date);
    }
}
