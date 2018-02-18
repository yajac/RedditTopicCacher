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
public class RedditCacheWriteHandler implements RequestHandler<CacheRequest, GatewayResponse> {

    public GatewayResponse handleRequest(final CacheRequest input, final Context context) {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        context.getLogger().log("Subtopic: " + input);
        final SubtopicListing client = new SubtopicListing();
        final String subtopic = input.getSubtopic();
        setCache(client, subtopic);
        return new GatewayResponse("{ \"Output\": \"Success\"}", headers, 200);
    }

    private void setCache(SubtopicListing client, String subtopic) {
        Map<String, String> subtopics = client.getListingForSubTopic(subtopic + ".json");
        for(String key : subtopics.keySet()){
            CacheManager.setCacheValues(subtopic, key, subtopics.get(key));
        }
    }
}
