package org.yajac.reddit.lambda;

import org.junit.Assert;
import org.junit.Test;

public class RedditCacheHandlerTest {
    @Test
    public void handleRequest() throws Exception {
        RedditCacheReadHandler handler = new RedditCacheReadHandler();
        Object response = handler.handleRequest(null, null);
        Assert.assertNotNull(response);
    }

}