package org.yajac.reddit.lambda;

/**
 * POJO containing request object for API Gateway.
 */
public class CacheRequest {

    private String subtopic;

    public CacheRequest(){

    }

    public CacheRequest(final String subtopic) {
        this.subtopic = subtopic;
    }

    public String getSubtopic() {
        return subtopic;
    }
}
