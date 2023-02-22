package com.sk.rk.util;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
public class CommonUtil {
    public static final String HEADER_USER_ID                   = "user-id";
    public static final String DEFAULT_INVOKING_USER            = "no-user";
    public static final String START_TIME                       = "startTime";
    public static final String ONLY_DATE_FORMAT                 = "MM/dd/yyyy";
    public static final String YYYY_MM_DD_HH_MM_SS              = "yyyy_MM_dd_hh_mm_ss";
    public static final String LONG_DATE_FORMAT                 = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DURATION_FIELD                   = "duration";
    public static final String DATA_FIELD                       = "data";
    public static final String RESPONSE_USER_MESSAGE_FIELD      = "userMessage";
    public static final String RESPONSE_CODE                    = "code";
    public static final String RESPONSE_SYSTEM_MESSAGE_FIELD    = "systemMessage";
    public static final String RESPONSE_PATH_FIELD              = "path";
    public static final String RESPONSE_EXCEPTION_FIELD         = "exception";
    public static final String TIMESTAMP_FIELD                  = "timestamp";
    public static final String IS_SUCCESS                       = "isSuccess";
    public static final String OUTPUT                           = "MessageText";
    public static final String INVOKING_USER                    = "InvokingUser";
    public static final String SUCCESS                          = "Success";

    public static final int PERMISSION_VIEW                     = 0x1;
    public static final int PERMISSION_VIEW_EDIT                = 0x2;
    public static final int PERMISSION_VIEW_EDIT_DELETE         = 0x4;


    private CommonUtil () {
        throw new IllegalAccessError("Utility class");
    }

    static final String HYPHEN = "'-'";
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(getCurrentDate().getTime());
    }

    public static Date getCurrentDate() {
        try {
            SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
            dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));

            // Local time zone
            SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

            // Time in GMT
            return dateFormatLocal.parse(dateFormatGmt.format(new Date()));
        } catch (Exception e) {
            return new Date(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis());
        }
    }


    public static Date convertStringToDate(String dateInString) throws ParseException {
        String usDateString = convert2USDate(dateInString);
        SimpleDateFormat formatter = new SimpleDateFormat(ONLY_DATE_FORMAT);
        return formatter.parse(usDateString);
    }

    public static String convert2USDate(String date) {
        if (date == null || "".equals(date)) {
            return "";
        }
        if (date.indexOf(HYPHEN) == -1) {
            return date;
        }
        int d1 = date.indexOf(HYPHEN);
        int d2 = date.indexOf(HYPHEN, d1 + 1);
        int d3 = date.indexOf("' '");

        try {
            return date.substring(d1 + 1, d2) + "/" + date.substring(d2 + 1, d3) + "/" + date.substring(0, d1);
        } catch (Exception e) {
            log.debug("error in convert2USDate" + e);
            return date;
        }
    }
}
