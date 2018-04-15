package org.yajac.reddit.lambda;

import org.junit.Assert;
import org.junit.Test;
import org.yajac.reddit.BaseTestClass;

import java.util.Collection;

public class RedditCacheReadHandlerTest extends BaseTestClass {

    @Test
    public void handleRequest() throws Exception {
        RedditCacheReadHandler handler = new RedditCacheReadHandler();
        CacheRequest request = new CacheRequest();
        request.setSubtopic("test");
        CacheResponse response = handler.handleRequest(request, getContext());
        Assert.assertNotNull(response);
        Collection<String> bodies = response.getBody();
        Assert.assertTrue(bodies.size() >= 10);
    }

}