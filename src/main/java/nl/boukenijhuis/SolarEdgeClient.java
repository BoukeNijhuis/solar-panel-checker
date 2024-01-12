package nl.boukenijhuis;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Properties;

public class SolarEdgeClient {

    OkHttpClient client = new OkHttpClient();
    private static String URL;

    public SolarEdgeClient(Properties properties) {
        URL = "https://monitoringapi.solaredge.com/site/847432/dataPeriod?api_key=" + properties.get("api-key");
    }

    public String getData() throws IOException {
        Request request = new Request.Builder()
                .url(URL)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
