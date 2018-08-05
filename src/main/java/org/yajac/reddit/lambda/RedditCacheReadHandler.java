package org.yajac.reddit.lambda;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.yajac.persist.PersistCacheManager;
import org.yajac.reddit.model.CacheRequest;
import org.yajac.reddit.model.GatewayResponse;

import java.util.List;

/**
 * Handler for requests to Lambda function.
 */
public class RedditCacheReadHandler implements RequestHandler<CacheRequest, GatewayResponse> {

    static final String table = "redditEvents";

    public GatewayResponse handleRequest(final CacheRequest input, final Context context) {
        context.getLogger().log("Input: " + input);
        final String subtopic = input.getPathParameters().get("subtopic");
        context.getLogger().log("Subtopic: " + subtopic);
        return new GatewayResponse(getBody(getCacheValues(subtopic)), 200);
    }

    private String getBody(List<String> values) {
        StringBuffer body = new StringBuffer("[");
        body.append(String.join("},{", values));
        body.append("]");
        return body.toString();
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
