package org.yajac.reddit.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpMethod.GET;

public class SubtopicListing {

    private static String USER_AGENT = "AWSAPILoad:org.yajac.topic.cache:v0.0.1";
    private static String URL_BASE = "https://www.reddit.com/r/subreddit/{subtopic}?limit=100";
    private ObjectMapper mapper = new ObjectMapper();



    public List<String> getListingForSubTopic(final String subtopic){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(URL_BASE, GET, new HttpEntity<byte[]>(getHeaders()), String.class, getURIParams(subtopic));
        if(!response.getStatusCode().is2xxSuccessful()){
            throw new RedditException("Unable to connect to Redit");
        }
        String subtopicJSON = response.getBody();
        try {
            JsonNode node = mapper.readTree(subtopicJSON);
            return getListingData(node);
        } catch (IOException  e) {
            throw new RedditException("Invalid return from Reddit");
        }
    }

    protected List<String> getListingData(JsonNode node) throws JsonProcessingException {
        List<String> listings = new ArrayList<String>();
        ArrayNode children = (ArrayNode) node.get("data").get("children");
        for(int i = 0; i < children.size(); i++){
            JsonNode listing = children.get(i).get("data");
            listings.add(mapper.writeValueAsString(listing));
        }
        return listings;
    }

    private Map<String, String> getURIParams(final String subtopic){
        Map<String,String> myMap = new HashMap<String,String>();
        myMap.put("subtopic", subtopic);
        return myMap;
    }

    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.USER_AGENT, USER_AGENT);
        return headers;
    }
}
