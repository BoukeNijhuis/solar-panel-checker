package nl.boukenijhuis;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class SolarEdgeClient {

    HttpClient client = HttpClient.newHttpClient();
    private static String URL;

    public SolarEdgeClient(Properties properties) {
        URL = "https://monitoringapi.solaredge.com/site/847432/dataPeriod?api_key=" + properties.get("api-key");
    }

    public String getData() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
     }
}
