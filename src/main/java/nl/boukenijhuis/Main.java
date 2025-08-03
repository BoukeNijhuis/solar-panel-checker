package nl.boukenijhuis;

import java.util.Timer;

public class Main {

    // schedule = "0 0 20 * * 0"
    public static void main(String[] args) {
        Checker checker = new Checker();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(checker, 0, 1000 * 60 * 60 * 24 * 7);
    }
}

