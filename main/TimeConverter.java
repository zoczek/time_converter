package main;

import java.util.*;
import java.time.LocalTime;

public class TimeConverter{
    private static final Map<String, Integer> timeDifferenceInMinutesByZone = new HashMap<>();
    {
        timeDifferenceInMinutesByZone.put("GMT", 0);
        timeDifferenceInMinutesByZone.put("PDT", -7 * 60);
    }

    public Set<String> getSupportedTimeZones(){
        return timeDifferenceInMinutesByZone.keySet();
    }

    public LocalTime convertTime(LocalTime time, String timeZoneFrom, String timeZoneTo){
        int timeDifference = calculateTimeDifferenceInMinutes(timeZoneFrom, timeZoneTo);
        return time.plusMinutes(timeDifference);
    }

    private int calculateTimeDifferenceInMinutes(String timeZoneFrom, String timeZoneTo){
        int timeShiftFrom = timeDifferenceInMinutesByZone.get(timeZoneFrom);
        int timeShiftTo = timeDifferenceInMinutesByZone.get(timeZoneTo);

        return timeShiftTo - timeShiftFrom;
    }
}