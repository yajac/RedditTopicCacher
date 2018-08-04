package org.yajac.reddit.lambda;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.yajac.persist.PersistCacheManager;
import org.yajac.reddit.model.CacheReadResponse;
import org.yajac.reddit.model.CacheRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handler for requests to Lambda function.
 */
public class RedditCacheReadHandler implements RequestHandler<CacheRequest, CacheReadResponse> {

    static final String table = "redditEvents";

    public CacheReadResponse handleRequest(final CacheRequest input, final Context context) {
        context.getLogger().log("Input: " + input);
        final String subtopic = input.getPathParameters().get("subtopic");
        context.getLogger().log("Subtopic: " + subtopic);
        List<String> values =  getCacheValues(subtopic);
        return new CacheReadResponse(values, getReturnHeaders(), 200);
    }

    private Map<String, String> getReturnHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }

    protected List<String> getCacheValues(String subtopic) {
        AmazonDynamoDB amazonDynamoDB = getAmazonDynamoDB();
        PersistCacheManager persistCacheManager = new PersistCacheManager(amazonDynamoDB);
        return persistCacheManager.getEvents(table, subtopic);
    }

    protected AmazonDynamoDB getAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard().build();
    }
}
