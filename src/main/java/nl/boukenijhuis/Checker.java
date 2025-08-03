package nl.boukenijhuis;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.TimerTask;

class Checker extends TimerTask {

    @Override
    public void run() {
        try {
            checkSolarPanels();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkSolarPanels() throws IOException {
        // read the properties
        Properties properties = new Properties();
        properties.load(Mailer.class.getResourceAsStream("/solar-panel-checker.properties"));

        String response = new SolarEdgeClient(properties).getData();

        LocalDate date = getDate(response);

        // send email
        String subject = "De zonnepanelen werken naar behoren: ";

        if (!date.isEqual(LocalDate.now())) {
            subject = "De zonnepanelen werken NIET naar behoren: ";
        }
        subject += date;

        Mailer.sendEmail(properties, subject);

    }

    @NotNull
    private LocalDate getDate(String response) {
        // get output for this week
        System.out.println(response);

        // find endDate
        String searchValue = "\"endDate\":\"";
        int startIndex = response.indexOf(searchValue) + searchValue.length();
        int endIndex = response.indexOf("\"", startIndex);

        String endDate = response.substring(startIndex, endIndex);
        System.out.println("endDate (as String): " + endDate);

        LocalDate date = LocalDate.parse(endDate);
        System.out.println("endDate (as Date): " + date);
        return date;
    }
}
