package org.Temirjohn.Task_6.client.impl;

import org.Temirjohn.Task_6.client.HttpClientService;
import org.Temirjohn.Task_6.record.AggregationReport;
import org.Temirjohn.Task_6.record.RequestResult;
import java.util.List;
import java.util.concurrent.*;

public class AggregatorService {
    private final HttpClientService httpClientService;
    private final ExecutorService executorService;

    public AggregatorService(HttpClientService httpClientService, int threadPoolSize) {
        this.httpClientService = httpClientService;
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
    }

    public AggregationReport processUrls(List<String> urls) {
        try {
            List<Callable<RequestResult>> tasks = urls.stream()
                    .map(url -> (Callable<RequestResult>) () -> httpClientService.executeRequest(url))
                    .toList();


            List<Future<RequestResult>> futures = executorService.invokeAll(tasks);

            List<RequestResult> requestResults = futures.stream()
                    .map(this::safeGet)
                    .toList();

            return aggregate(requestResults);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Aggregator interruted", e);
        } finally {
            shutdownExecutor();
        }
    }

    private RequestResult safeGet(Future<RequestResult> future) {
        try {
            return future.get();
        } catch (Exception e) {
            return RequestResult.failure("Unknown", 0, "Future processing error");
        }
    }

    private AggregationReport aggregate(List<RequestResult> requestResults) {
        long successCount = requestResults.stream().filter(RequestResult::isSuccess).count();
        long failureCount = requestResults.stream().filter(r -> !r.isSuccess()).count();

        double avgTime = requestResults.stream()
                .mapToLong(RequestResult::durationMillis)
                .average()
                .orElse(0.0);

        return new AggregationReport(successCount, failureCount, avgTime);
    }

    private void shutdownExecutor() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MICROSECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
