package org.yajac.reddit.lambda;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class RedditCacheWriteHandlerTest {
    @Test
    @Ignore
    public void handleRequest() throws Exception {
        RedditCacheWriteHandler handler = new RedditCacheWriteHandler();
        Object response = handler.handleRequest(null, null);
        Assert.assertNotNull(response);
    }

}