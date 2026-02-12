package org.Temirjohn.Task_6;

import org.Temirjohn.Task_6.client.HttpClientService;
import org.Temirjohn.Task_6.client.impl.AggregatorService;
import org.Temirjohn.Task_6.client.impl.HttpClientServiceImpl;
import org.Temirjohn.Task_6.record.AggregationReport;

import java.net.http.HttpClient;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();


        HttpClientServiceImpl httpClientServiceImpl = new HttpClientServiceImpl(httpClient);
        AggregatorService aggregatorService = new AggregatorService(httpClientServiceImpl, 3);

        List<String> targets = List.of(
                "https://www.google.com",
                "https://www.github.com",
                "https://httpstat.us/404",
                "https://invalid-url-test.local"
        );

        System.out.println("START!!!");
        AggregationReport aggregationReport = aggregatorService.processUrls(targets);

        System.out.println(aggregationReport);
    }
}
