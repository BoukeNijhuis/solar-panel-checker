package nl.boukenijhuis;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

/**
 * Azure Functions with Timer trigger.
 */
public class TimerTriggerJava {

    // for testing
    public static void main(String[] args) throws IOException {
        new TimerTriggerJava().run("", new TestExecutionContext() );
    }

    @FunctionName("TimerTriggerJava")
    public void run(
        @TimerTrigger(name = "timerInfo", schedule = "0 0 20 * * 0") String timerInfo,
                    final ExecutionContext context) throws IOException {

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
    private static LocalDate getDate(String response) {
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
