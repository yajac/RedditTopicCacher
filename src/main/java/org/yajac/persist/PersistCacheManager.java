package org.yajac.persist;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersistCacheManager {

	public static final String INSERT_UTC = "insert_utc";

	public static long TTL_TIME = 60 * 60 * 48;

	private AmazonDynamoDB amazonDynamoDB;

	public PersistCacheManager(AmazonDynamoDB amazonDynamoDB){
		this.amazonDynamoDB = amazonDynamoDB;
	}


	public List<String> getEvents(final String tableName, final String subreddit) {
		List<String> events = new ArrayList<>();
		DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
		Table table = dynamoDB.getTable(tableName);
		QuerySpec spec = new QuerySpec()
				.withKeyConditionExpression("subreddit = :subreddit")
				.withValueMap(new ValueMap()
						.withString(":subreddit", subreddit));

		ItemCollection<QueryOutcome> items = table.query(spec);
		for(Item item : items){
			events.add(item.toJSON());
		}
		return events;
	}

	public void putEvent(final String tableName, final String eventJson){
		Item eventItem = Item.fromJSON(eventJson);
		eventItem.withLong(INSERT_UTC, (System.currentTimeMillis() / 1000 + TTL_TIME));
		Map<String, Object> map = new HashMap<>(eventItem.asMap());
		for(String key : map.keySet()){
			Object value = map.get(key);
			if(value instanceof String && ((String) value).isEmpty()){
				eventItem.removeAttribute(key);
			}
		}
		eventItem.withPrimaryKey("subreddit", eventItem.get("subreddit"));
		DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
		Table table = dynamoDB.getTable(tableName);
		table.putItem(eventItem);
	}

}
