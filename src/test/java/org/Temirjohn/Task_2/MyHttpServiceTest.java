package org.Temirjohn.Task_2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MyHttpServiceTest {

    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpResponse<String> httpResponse;

    @InjectMocks
    private MyHttpService myHttpService;

    @Test
    void Test1() throws IOException, InterruptedException {
        String expectBody = "{\"status\":\"ok\"}";
        int expectedStatusCode = 200;

        when(httpResponse.statusCode()).thenReturn(expectedStatusCode);
        when(httpResponse.body()).thenReturn(expectBody);


        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);
        String actualBody = myHttpService.sendGetRequest("http://example.com");

        assertEquals(expectBody, actualBody);

        verify(httpClient, times(1)).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        verify(httpResponse).statusCode();
    }
}
