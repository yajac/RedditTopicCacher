package org.yajac.reddit.lambda;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.yajac.persist.PersistCacheManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handler for requests to Lambda function.
 */
public class RedditCacheReadHandler implements RequestHandler<CacheRequest, CacheResponse> {

    static final String table = "redditEvents";

    public CacheResponse handleRequest(final CacheRequest input, final Context context) {
        context.getLogger().log("Subtopic: " + input);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        final String subtopic = input.getSubtopic();
        List<String> values =  getCacheValues(subtopic);
        return new CacheResponse(values, headers, 200);
    }

    private List<String> getCacheValues(String subtopic) {
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().build();
        PersistCacheManager persistCacheManager = new PersistCacheManager(amazonDynamoDB);
        return persistCacheManager.getEvents(table, subtopic);
    }
}
