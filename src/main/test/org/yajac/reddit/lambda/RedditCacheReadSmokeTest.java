package org.yajac.reddit.lambda;

import org.junit.Assert;
import org.junit.Test;
import org.yajac.reddit.BaseTestClass;
import org.yajac.reddit.model.CacheRequest;
import org.yajac.reddit.model.GatewayResponse;


public class RedditCacheReadSmokeTest extends BaseTestClass {

    private RedditCacheReadHandler handler = new RedditCacheReadHandler();

    @Test
    public void empty() throws Exception {

    }

    @Test
    public void handleRequest() throws Exception {
        CacheRequest request = new CacheRequest();
        request.getPathParameters().put("subtopic", "dogpictures");
        GatewayResponse response = handler.handleRequest(request, getContext());
        Assert.assertNotNull(response);
        String bodies = response.getBody();
        Assert.assertNotNull(bodies);
    }

}