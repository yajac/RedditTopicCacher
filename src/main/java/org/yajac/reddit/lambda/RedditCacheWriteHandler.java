package org.yajac.reddit.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.yajac.cache.CacheManager;
import org.yajac.reddit.client.SubtopicListing;

import java.util.HashMap;
import java.util.Map;

/**
 * Handler for requests to Lambda function.
 */
public class RedditCacheWriteHandler implements RequestHandler<Object, Object> {

    public GatewayResponse handleRequest(final Object input, final Context context) {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        final SubtopicListing client = new SubtopicListing();
        final String subtopic = "new";
        final String redisServer = null;
        setCache(client, subtopic, redisServer);
        return new GatewayResponse("{ \"Output\": \"Success\"}", headers, 200);
    }

    private void setCache(SubtopicListing client, String subtopic, String redisServer) {
        Map<String, String> subtopics = client.getListingForSubTopic(subtopic + ".json");
        for(String key : subtopics.keySet()){
            CacheManager.setCacheValues(redisServer, subtopic, key, subtopics.get(key));
        }
    }
}
