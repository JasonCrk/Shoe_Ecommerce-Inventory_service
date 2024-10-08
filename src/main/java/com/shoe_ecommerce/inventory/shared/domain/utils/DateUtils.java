package com.shoe_ecommerce.inventory.shared.domain.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtils {
    public static String dateToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static String dateToString(Timestamp timestamp) {
        return dateToString(timestamp.toLocalDateTime());
    }
}
