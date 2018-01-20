package org.yajac.reddit.client;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class SubtopicListingTest {

    @Test
    public void getListingForSubTopic() throws Exception {
        SubtopicListing listing = new SubtopicListing();
        Map<String, String> listings = listing.getListingForSubTopic("new.json");
        System.out.println(listings);
        Assert.assertNotNull(listings);
        Assert.assertTrue(listings.size() == 100);
    }

}