package org.yajac.reddit.lambda;

/**
 * POJO containing request object for API Gateway.
 */
public class CacheReadRequest {

    private final String subtopic;

    public CacheReadRequest(final String subtopic) {
        this.subtopic = subtopic;
    }

    public String getSubtopic() {
        return subtopic;
    }
}
