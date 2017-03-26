package com.fsck.k9.message.quote;


import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.content.res.Resources;

import com.fsck.k9.K9;
import com.fsck.k9.mail.Message;


class QuoteHelper {
    // amount of extra buffer to allocate to accommodate quoting headers or prefixes
    static final int QUOTE_BUFFER_LENGTH = 512;


    /**
     * Extract the date from a message and convert it into a locale-specific
     * date string suitable for use in a header for a quoted message.
     *
     * @return A string with the formatted date/time
     */
    static String getSentDateText(Resources resources, Message message) {
        try {
            final int dateStyle = DateFormat.LONG;
            final int timeStyle = DateFormat.LONG;
            Date date = message.getSentDate();
            DateFormat dateFormat;
            Locale locale;
            if (K9.hideTimeZone()) {
                locale=Locale.ROOT;
                dateFormat = DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale);
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            } else {
                locale = resources.getConfiguration().locale;
                dateFormat = DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale);
            }
            return dateFormat.format(date);
        } catch (Exception e) {
            return "";
        }
    }
}
