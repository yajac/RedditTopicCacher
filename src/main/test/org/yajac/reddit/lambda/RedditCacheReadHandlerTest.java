package org.yajac.reddit.lambda;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

public class RedditCacheReadHandlerTest {
    @Test
    @Ignore
    public void handleRequest() throws Exception {
        RedditCacheReadHandler handler = new RedditCacheReadHandler();
        CacheReadRequest request = new CacheReadRequest("new");
        CacheResponse response = handler.handleRequest(request, null);
        Assert.assertNotNull(response);
        Collection<String> bodies = response.getBody();
        //Assert.assertTrue(bodies.size() > 10);
    }

}