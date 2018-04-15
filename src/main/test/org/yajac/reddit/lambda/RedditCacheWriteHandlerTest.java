package org.yajac.reddit.lambda;

import org.junit.Assert;
import org.junit.Test;
import org.yajac.reddit.BaseTestClass;

public class RedditCacheWriteHandlerTest extends BaseTestClass {

    @Test
    public void handleRequest() throws Exception {
        RedditCacheWriteHandler handler = new RedditCacheWriteHandler();
        CacheRequest request = new CacheRequest();
        request.setSubtopic("test");
        Object response = handler.handleRequest(request, getContext());
        Assert.assertNotNull(response);
    }

}