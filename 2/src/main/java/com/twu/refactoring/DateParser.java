package com.twu.refactoring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateParser {
    private final String dataTime;
    private static final HashMap<String, TimeZone> KNOWN_TIME_ZONES = new HashMap<String, TimeZone>();

    static {
        KNOWN_TIME_ZONES.put("UTC", TimeZone.getTimeZone("UTC"));
    }

    /**
     * Takes a date in ISO 8601 format and returns a date
     *
     * @param dataTime - should be in format ISO 8601 format
     *                 examples -
     *                 2012-06-17 is 17th June 2012 - 00:00 in UTC TimeZone
     *                 2012-06-17TZ is 17th June 2012 - 00:00 in UTC TimeZone
     *                 2012-06-17T15:00Z is 17th June 2012 - 15:00 in UTC TimeZone
     */
    public DateParser(String dataTime) {
        this.dataTime = dataTime;
    }

    public Date parse() {
        int year, month, date, hour, minute;

        year = checkUtil("Year", 0, 4, 2000, 2012);
        month = checkUtil("Month", 5, 7, 1, 12);
        date = checkUtil("Date", 8, 10, 1, 31);

        if (dataTime.substring(11, 12).equals("Z")) {
            hour = 0;
            minute = 0;
        } else {
            hour = checkUtil("Hour", 11, 13, 0, 23);
            minute = checkUtil("Minute", 14, 16, 0, 59);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(year, month - 1, date, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    public int checkUtil(String timeType, int start, int end, int minValue, int maxValue) {
        int result;
        try {
            String resultString = dataTime.substring(start, end);
            result = Integer.parseInt(resultString);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("%s string is less than %d characters", timeType, end - start));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("%s is not an integer", timeType));
        }

        if (result < minValue || result > maxValue) {
            throw new IllegalArgumentException(String.format("%s cannot be less than %d or more than %d", timeType, minValue, maxValue));
        }

        return result;
    }
}
