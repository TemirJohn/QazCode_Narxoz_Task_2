package org.Temirjohn.Task_6.client;

import org.Temirjohn.Task_6.record.RequestResult;

public interface HttpClientService {
    RequestResult executeRequest(String url);
}
