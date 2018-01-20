package org.yajac.reddit.lambda;

/**
 * POJO containing request object for API Gateway.
 */
public class CacheRequest {

    private final String subtopic;

    public CacheRequest(final String subtopic) {
        this.subtopic = subtopic;
    }

    public String getSubtopic() {
        return subtopic;
    }
}
