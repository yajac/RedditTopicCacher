package org.yajac.reddit.model;

/**
 * POJO containing request object for API Gateway.
 */
public class CacheRequest {

    private String subtopic;

    public CacheRequest(){

    }

    public void setSubtopic(String subtopic) {
        this.subtopic = subtopic;
    }

    public String getSubtopic() {
        return subtopic;
    }

    @Override
    public String toString() {
        return "{" +
                "subtopic='" + subtopic + '\'' +
                '}';
    }
}
