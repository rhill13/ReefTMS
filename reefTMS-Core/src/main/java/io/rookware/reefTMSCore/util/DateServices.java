package io.rookware.reefTMSCore.util;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;
import java.util.stream.Stream;

@Component
public class DateServices {

    public static Date parseStringToDate(String dateString) {
        List<Integer> yearMonthDay = Arrays.stream(dateString.split("-")).map(Integer::parseInt).toList();
        return new GregorianCalendar(yearMonthDay.get(0),yearMonthDay.get(1) - 1, yearMonthDay.get(2)).getTime();
    }

    public static String generateString(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String year, month, day;
        year = Integer.toString(calendar.get(Calendar.YEAR));
        if ((calendar.get(Calendar.MONTH)+1) < 10) month = String.format("0%d", (calendar.get(Calendar.MONTH)+1));
        else month = Integer.toString(calendar.get(Calendar.MONTH)+1);
        day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        return String.format("%s-%s-%s", year, month, day);
    }
}
