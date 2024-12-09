package me.psychomantis.utils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class OffsetDateTimeConverter {
    public static String formatOffsetDateTime(OffsetDateTime dateTime) {
        // Define a custom date and time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm a", Locale.ENGLISH);

        // Format the OffsetDateTime as a string
        return dateTime.format(formatter);
    }
}
