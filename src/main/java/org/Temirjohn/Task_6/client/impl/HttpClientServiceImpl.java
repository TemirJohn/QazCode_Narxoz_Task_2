package org.Temirjohn.Task_6.client.impl;

import org.Temirjohn.Task_6.client.HttpClientService;
import org.Temirjohn.Task_6.record.RequestResult;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpClientServiceImpl implements HttpClientService {
    private static final Logger logger = Logger.getLogger(HttpClientService.class.getName());
    private final HttpClient client;

    public HttpClientServiceImpl(HttpClient client) {
        this.client = client;
    }


    @Override
    public RequestResult executeRequest(String url) {
        long start = System.currentTimeMillis();
        logger.info(() -> "Starting request to: " + url);

        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(5))
                    .GET()
                    .build();


            HttpResponse<Void> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.discarding());
            long duration = System.currentTimeMillis() - start;

            int code = httpResponse.statusCode();
            logger.info(() -> String.format("Finished " + url + " in " + duration + " ms with code " + code));
            if (code == 200) {
                return RequestResult.success(url, code, duration);
            } else {
                return RequestResult.failure(url, duration, "Http error code: " + code);
            }
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - start;
            logger.log(Level.SEVERE, "Error request " + url, e);
            return RequestResult.failure(url, duration, e.getMessage());
        }
    }
}
