package org.yajac.reddit.lambda;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.yajac.persist.PersistCacheManager;
import org.yajac.reddit.client.SubtopicListing;

import java.util.HashMap;
import java.util.Map;

/**
 * Handler for requests to Lambda function.
 */
public class RedditCacheWriteHandler implements RequestHandler<CacheRequest, GatewayResponse> {

    static final String table = "redditEvents";

    public GatewayResponse handleRequest(final CacheRequest input, final Context context) {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        if(context != null) {
            context.getLogger().log("Subtopic: " + input);
        }
        final SubtopicListing client = new SubtopicListing();
        final String subtopic = input.getSubtopic();
        setCache(client, subtopic);
        return new GatewayResponse("{ \"Output\": \"Success\"}", headers, 200);
    }

    private void setCache(SubtopicListing client, String subtopic) {
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().build();
        PersistCacheManager persistCacheManager = new PersistCacheManager(amazonDynamoDB);
        Map<String, String> subtopics = client.getListingForSubTopic(subtopic + ".json");
        for(String value : subtopics.values()){
            persistCacheManager.putEvent(table, value);
        }
    }
}
