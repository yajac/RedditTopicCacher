package org.yajac.reddit.lambda;

import org.junit.Assert;
import org.junit.Test;

public class RedditCacheWriteHandlerTest {

    @Test
    public void handleRequest() throws Exception {
        RedditCacheWriteHandler handler = new RedditCacheWriteHandler();
        CacheRequest request = new CacheRequest();
        request.setSubtopic("new");
        Object response = handler.handleRequest(request, null);
        Assert.assertNotNull(response);
    }

}