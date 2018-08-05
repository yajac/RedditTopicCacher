package org.yajac.reddit.model;

/**
 * POJO containing response object for API Gateway.
 */
public class GatewayResponse {

    private final String body;
    private final int statusCode;


    /**
     * Cache Read Response
     * @param body
     * @param statusCode
     */
    public GatewayResponse(final String body, final int statusCode) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
