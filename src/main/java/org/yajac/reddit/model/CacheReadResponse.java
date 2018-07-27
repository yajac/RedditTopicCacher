package org.yajac.reddit.model;

import java.util.*;

/**
 * POJO containing response object for API Gateway.
 */
public class CacheReadResponse {

    private final Collection<String> body;
    private final Map<String, String> headers;
    private final int statusCode;

    public CacheReadResponse(final Collection<String> body, final Map<String, String> headers, final int statusCode) {
        this.statusCode = statusCode;
        this.body = body;
        this.headers = Collections.unmodifiableMap(new HashMap<>(headers));
    }

    public Collection<String> getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
