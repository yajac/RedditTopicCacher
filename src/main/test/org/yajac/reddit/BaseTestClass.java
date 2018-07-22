package org.yajac.reddit;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.yajac.persist.PersistCacheManager;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseTestClass {

    protected AmazonDynamoDB dynamoDB;
    protected PersistCacheManager persistCacheManager;
    public static final String REDDIT_EVENTS = "redditEvents";

    @Before
    public void setupDatabase(){
        dynamoDB = DynamoDBEmbedded.create().amazonDynamoDB();
        createTable(dynamoDB, REDDIT_EVENTS, "subreddit");
        persistCacheManager = new PersistCacheManager(dynamoDB);
    }

    protected static CreateTableResult createTable(AmazonDynamoDB ddb, String tableName, String hashKeyName) {
        List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
        attributeDefinitions.add(new AttributeDefinition(hashKeyName, ScalarAttributeType.S));

        List<KeySchemaElement> ks = new ArrayList<KeySchemaElement>();
        ks.add(new KeySchemaElement(hashKeyName, KeyType.HASH));

        ProvisionedThroughput provisionedthroughput = new ProvisionedThroughput(1000L, 1000L);

        CreateTableRequest request =
                new CreateTableRequest()
                        .withTableName(tableName)
                        .withAttributeDefinitions(attributeDefinitions)
                        .withKeySchema(ks)
                        .withProvisionedThroughput(provisionedthroughput);

        return ddb.createTable(request);
    }

    public Context getContext() {
        LambdaLogger logger = mock(LambdaLogger.class);
        Context contxt = mock(Context.class);
        when(contxt.getLogger()).thenReturn(logger);
        return contxt;
    }
}
