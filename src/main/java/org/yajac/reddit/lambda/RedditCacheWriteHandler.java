package org.yajac.reddit.lambda;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.yajac.persist.PersistCacheManager;
import org.yajac.reddit.client.SubtopicListing;
import org.yajac.reddit.model.CacheRequest;
import org.yajac.reddit.model.GatewayResponse;

import java.util.Map;

/**
 * Handler for requests to Lambda function.
 */
public class RedditCacheWriteHandler implements RequestHandler<CacheRequest, GatewayResponse> {

    static final String table = "redditEvents";

    public GatewayResponse handleRequest(final CacheRequest input, final Context context) {
        context.getLogger().log("Subtopic: " + input);
        final SubtopicListing client = new SubtopicListing();
        final String subtopic = input.getPathParameters().get("subtopic");
        setCache(client, subtopic);
        return new GatewayResponse("{'Output': 'Success'}", 200);
    }

    private void setCache(SubtopicListing client, String subtopic) {
        AmazonDynamoDB amazonDynamoDB = getAmazonDynamoDB();
        PersistCacheManager persistCacheManager = new PersistCacheManager(amazonDynamoDB);
        Map<String, String> subtopics = client.getListingForSubTopic(subtopic + ".json");
        for(String value : subtopics.values()){
            persistCacheManager.putEvent(table, value);
        }
    }

    protected AmazonDynamoDB getAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard().build();
    }
}
