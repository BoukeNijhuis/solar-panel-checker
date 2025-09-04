package nl.boukenijhuis;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Timer;

public class Main {

    public static void main(String[] args) {
        Checker checker = new Checker();

        // every sunday evening
        Timer timer = new Timer();
        final LocalDateTime localDateTime = LocalDateTime.of(2025, 8, 31, 20, 0);
        final Date firstTime = new Date(localDateTime.toEpochSecond(ZoneOffset.UTC));
        timer.schedule(checker, firstTime, 1000 * 60 * 60 * 24 * 7);
    }
}

