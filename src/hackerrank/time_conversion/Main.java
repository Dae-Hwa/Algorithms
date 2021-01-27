package hackerrank.time_conversion;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    static String timeConversion(String s) {
        return LocalTime.parse(s, DateTimeFormatter.ofPattern("hh:mm:ssa", Locale.US))
                .format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString();
    }

    public static void main(String[] args) {
        System.out.println(timeConversion("12:00:00AM"));
    }
}

