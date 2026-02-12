package org.Temirjohn.Task_6.record;

public record RequestResult(String url, boolean isSuccess, int statusCode, long durationMillis, String errorMessage) {
    public static RequestResult success(String url, int code, long duration) {
        return new RequestResult(url, true, code, duration, null);
    }

    public static RequestResult failure(String url, long duration, String error) {
        return new RequestResult(url, false, 0, duration, error);
    }
}
