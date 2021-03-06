package org.yajac.reddit.lambda;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.yajac.reddit.BaseTestClass;
import org.yajac.reddit.model.CacheRequest;
import org.yajac.reddit.model.GatewayResponse;

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
        request.getPathParameters().put("subtopic", "dogpictures");
        GatewayResponse response = handler.handleRequest(request, getContext());
        Assert.assertNotNull(response);
    }

}