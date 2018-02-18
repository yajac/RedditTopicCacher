package org.yajac.reddit.lambda;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public class RedditCacheReadHandlerTest {

    @Test
    public void handleRequest() throws Exception {
        RedditCacheReadHandler handler = new RedditCacheReadHandler();
        CacheRequest request = new CacheRequest();
        request.setSubtopic("new");
        CacheResponse response = handler.handleRequest(request, null);
        Assert.assertNotNull(response);
        Collection<String> bodies = response.getBody();
        Assert.assertTrue(bodies.size() >= 10);
    }

}