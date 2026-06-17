package com.learning.ai_starter.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Component
public class DateTimeTools {

    @Tool(description = "Get the current date and time. " +
            "Call this when user asks about current date or time.")
    public String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter
                .ofPattern("dd-MM-yyyy HH:mm:ss")) +
                " IST (Bangalore, India)";
    }

    @Tool(description = "Calculate number of days between two dates. " +
            "Date format: yyyy-MM-dd")
    public long daysBetween(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return ChronoUnit.DAYS.between(start, end);
    }

    @Tool(description = "Get day of week for a specific date. " +
            "Date format: yyyy-MM-dd")
    public String getDayOfWeek(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return localDate.getDayOfWeek().toString() + ", " +
                localDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
}
