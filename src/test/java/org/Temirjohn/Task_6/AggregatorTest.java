package org.Temirjohn.Task_6;

import org.Temirjohn.Task_6.client.HttpClientService;
import org.Temirjohn.Task_6.client.impl.AggregatorService;
import org.Temirjohn.Task_6.client.impl.HttpClientServiceImpl;
import org.Temirjohn.Task_6.record.AggregationReport;
import org.Temirjohn.Task_6.record.RequestResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AggregatorTest {
    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpResponse<Object> httpResponse;

    private HttpClientServiceImpl httpClientService;
    private AggregatorService aggregatorService;

    @BeforeEach
    void setUp() {
        httpClientService = new HttpClientServiceImpl(httpClient);
        aggregatorService = new AggregatorService(httpClientService, 2);
    }

    @Test
    void testSuccessFullAgg() throws IOException, InterruptedException {
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpClient.send(any(HttpRequest.class), any())).thenReturn(httpResponse);

        List<String> urls = List.of("http://test1.com", "http://test2.com");

        AggregationReport report = aggregatorService.processUrls(urls);

        assertEquals(2, report.successCount(), "Need to be 2 success request");
        assertEquals(0, report.failureCount(), "Error cannot be");

        verify(httpClient, times(2)).send(any(), any());
    }

    @Test
    void testMixedResult() throws IOException, InterruptedException {
        HttpClientService mockService = mock(HttpClientServiceImpl.class);
        AggregatorService LocalAggregator= new AggregatorService(mockService, 2);

        when(mockService.executeRequest("https://ok.com")).thenReturn(RequestResult.success("https://ok.com", 200, 100));
        when(mockService.executeRequest("https://fail.com")).thenReturn(RequestResult.failure("https://fail.com", 50, "Error"));

        AggregationReport  aggregationReport = LocalAggregator.processUrls(List.of("https://ok.com", "https://fail.com"));

        assertEquals(1, aggregationReport.successCount());
        assertEquals(1, aggregationReport.failureCount());
        assertEquals(75.0, aggregationReport.averageDurationMillis(), "averrege duration need to be 75");
    }

    @Test
    void testHttpClient() throws IOException, InterruptedException {
        when(httpClient.send(any(), any())).thenThrow(new IOException("error"));

        RequestResult requestResult = httpClientService.executeRequest("http://bad-network.com");
        assertFalse(requestResult.isSuccess());
        assertEquals("error", requestResult.errorMessage());
    }
}
