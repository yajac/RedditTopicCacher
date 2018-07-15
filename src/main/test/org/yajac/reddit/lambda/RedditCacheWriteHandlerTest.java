package org.yajac.reddit.lambda;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.yajac.reddit.BaseTestClass;

public class RedditCacheWriteHandlerTest extends BaseTestClass {

    @Spy
    RedditCacheWriteHandler handler;

    @Before
    public void setup(){
        Mockito.when(handler.getAmazonDynamoDB()).thenReturn(dynamoDB);
    }

    @Test
    public void handleRequest() throws Exception {
        CacheRequest request = new CacheRequest();
        request.setSubtopic("dogpictures");
        Object response = handler.handleRequest(request, getContext());
        Assert.assertNotNull(response);
    }

}