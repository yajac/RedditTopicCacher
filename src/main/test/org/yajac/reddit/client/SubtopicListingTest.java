package org.yajac.reddit.client;

import org.junit.Assert;

import java.util.List;

public class SubtopicListingTest {

    @org.junit.Test
    public void getListingForSubTopic() throws Exception {
        SubtopicListing listing = new SubtopicListing();
        List<String> listings = listing.getListingForSubTopic("new.json");
        System.out.println(listings);
        Assert.assertNotNull(listings);
        Assert.assertTrue(listings.size() == 100);
    }

}