package org.Temirjohn.Task_2;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyHttpService {

    private final HttpClient httpClient;
    private final Logger logger = Logger.getLogger(MyHttpService.class.getName());

    public MyHttpService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String sendGetRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        logger.log(Level.INFO, "Response Status Code: {0}", response.statusCode());

        return response.body();
    }
}
