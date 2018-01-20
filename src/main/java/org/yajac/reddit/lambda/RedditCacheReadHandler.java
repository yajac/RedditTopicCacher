package org.yajac.reddit.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.yajac.cache.CacheManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handler for requests to Lambda function.
 */
public class RedditCacheReadHandler implements RequestHandler<CacheRequest, CacheResponse> {

    public CacheResponse handleRequest(final CacheRequest input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        final String subtopic = input.getSubtopic();
        List<String> values =  CacheManager.getCacheValues(subtopic);
        return new CacheResponse(values, headers, 200);
    }
}
