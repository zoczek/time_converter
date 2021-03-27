package test;

import main.TimeConverter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TimeConverterTest {
    TimeConverter timeConverter = new TimeConverter();

    @org.junit.jupiter.api.Test
    void getSupportedTimeZones() {
        Set<String> timeZones = Set.of("GMT", "PDT");
        assertEquals(timeZones, timeConverter.getSupportedTimeZones());
    }

    @org.junit.jupiter.api.Test
    void convertTime() {
        String timeZoneGMT = "GMT";
        String timeZonePDT = "PDT";
        LocalTime time = LocalTime.parse("18:20:43", DateTimeFormatter.ISO_LOCAL_TIME);
        LocalTime timeFromGMTToPDT = LocalTime.parse("11:20:43", DateTimeFormatter.ISO_LOCAL_TIME);
        LocalTime timeFromPDTToGMT = LocalTime.parse("01:20:43", DateTimeFormatter.ISO_LOCAL_TIME);

        assertEquals(timeFromGMTToPDT, timeConverter.convertTime(time, timeZoneGMT, timeZonePDT));
        assertEquals(timeFromPDTToGMT, timeConverter.convertTime(time, timeZonePDT, timeZoneGMT));
    }
}