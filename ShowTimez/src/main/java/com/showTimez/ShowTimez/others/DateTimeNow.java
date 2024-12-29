package com.showTimez.ShowTimez.others;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeNow {
	public static String getFormattedDate() {
        
        LocalDateTime now = LocalDateTime.now();

       
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");

        
        return now.format(formatter);
    }
}
