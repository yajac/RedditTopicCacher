package org.yajac.reddit.lambda;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.yajac.reddit.BaseTestClass;

import java.util.Collection;


public class RedditCacheReadHandlerTest extends BaseTestClass {

    private final String TEST = "{\"approved_at_utc\": null, \"subreddit\": \"dogpictures\", \"selftext\": \"Hey Everyone!\\n\\nThis weeks \\\"Dog of the Month\\\" is /u/ThisEpiphany's dog, [Zeke](https://i.imgur.com/AMmXzDR.jpg).\\n\\nZeke, unfortunately, passed away a month ago, be sure to pass on some kind words.\\n\\nThank you for your submission,\\n\\n&amp;nbsp;\\n\\nThe r/DogPictures mods\", \"user_reports\": [], \"saved\": false, \"mod_reason_title\": null, \"gilded\": 0, \"clicked\": false, \"title\": \"[Dog of the Month] -Month 5 | /u/ThisEpiphany\", \"link_flair_richtext\": [], \"subreddit_name_prefixed\": \"r/dogpictures\", \"hidden\": false, \"pwls\": 6, \"link_flair_css_class\": null, \"downs\": 0, \"thumbnail_height\": null, \"parent_whitelist_status\": \"all_ads\", \"hide_score\": false, \"name\": \"t3_8ly11u\", \"quarantine\": false, \"link_flair_text_color\": \"dark\", \"author_flair_background_color\": null, \"subreddit_type\": \"public\", \"ups\": 36, \"domain\": \"self.dogpictures\", \"media_embed\": {}, \"thumbnail_width\": null, \"author_flair_template_id\": null, \"is_original_content\": false, \"secure_media\": null, \"is_reddit_media_domain\": false, \"category\": null, \"secure_media_embed\": {}, \"link_flair_text\": null, \"can_mod_post\": false, \"score\": 36, \"approved_by\": null, \"thumbnail\": \"self\", \"edited\": false, \"author_flair_css_class\": null, \"author_flair_richtext\": [], \"post_hint\": \"self\", \"content_categories\": null, \"is_self\": true, \"mod_note\": null, \"created\": 1527241077.0, \"link_flair_type\": \"text\", \"wls\": 6, \"post_categories\": null, \"banned_by\": null, \"author_flair_type\": \"text\", \"contest_mode\": false, \"selftext_html\": \"&lt;!-- SC_OFF --&gt;&lt;div class=\\\"md\\\"&gt;&lt;p&gt;Hey Everyone!&lt;/p&gt;\\n\\n&lt;p&gt;This weeks &amp;quot;Dog of the Month&amp;quot; is &lt;a href=\\\"/u/ThisEpiphany\\\"&gt;/u/ThisEpiphany&lt;/a&gt;&amp;#39;s dog, &lt;a href=\\\"https://i.imgur.com/AMmXzDR.jpg\\\"&gt;Zeke&lt;/a&gt;.&lt;/p&gt;\\n\\n&lt;p&gt;Zeke, unfortunately, passed away a month ago, be sure to pass on some kind words.&lt;/p&gt;\\n\\n&lt;p&gt;Thank you for your submission,&lt;/p&gt;\\n\\n&lt;p&gt;&amp;nbsp;&lt;/p&gt;\\n\\n&lt;p&gt;The &lt;a href=\\\"/r/DogPictures\\\"&gt;r/DogPictures&lt;/a&gt; mods&lt;/p&gt;\\n&lt;/div&gt;&lt;!-- SC_ON --&gt;\", \"likes\": null, \"suggested_sort\": null, \"banned_at_utc\": null, \"view_count\": null, \"archived\": false, \"no_follow\": false, \"is_crosspostable\": true, \"pinned\": false, \"over_18\": false, \"preview\": {\"images\": [{\"source\": {\"url\": \"https://i.redditmedia.com/R3WAgX01T5S8At_91qiRayLZhptkUljU-rSeCzSuuh4.jpg?s=1afbd1200f28f8e3af809f0ae4a526ba\", \"width\": 3718, \"height\": 2092}, \"resolutions\": [{\"url\": \"https://i.redditmedia.com/R3WAgX01T5S8At_91qiRayLZhptkUljU-rSeCzSuuh4.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=108&amp;s=b273b8d43bd802cc8d0234d208e37dfc\", \"width\": 108, \"height\": 60}, {\"url\": \"https://i.redditmedia.com/R3WAgX01T5S8At_91qiRayLZhptkUljU-rSeCzSuuh4.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=216&amp;s=4e493c564666f06feb68dea0d4e5094a\", \"width\": 216, \"height\": 121}, {\"url\": \"https://i.redditmedia.com/R3WAgX01T5S8At_91qiRayLZhptkUljU-rSeCzSuuh4.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=320&amp;s=f2ecbe332fb65a95fdc0e95a8c93b1d8\", \"width\": 320, \"height\": 180}, {\"url\": \"https://i.redditmedia.com/R3WAgX01T5S8At_91qiRayLZhptkUljU-rSeCzSuuh4.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=640&amp;s=247170b76f19dbf70e3e480c06785f36\", \"width\": 640, \"height\": 360}, {\"url\": \"https://i.redditmedia.com/R3WAgX01T5S8At_91qiRayLZhptkUljU-rSeCzSuuh4.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=960&amp;s=b5a67ae5dab24e69c7578ff3190d616d\", \"width\": 960, \"height\": 540}, {\"url\": \"https://i.redditmedia.com/R3WAgX01T5S8At_91qiRayLZhptkUljU-rSeCzSuuh4.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=1080&amp;s=18788c35995d37a554e5bd8edf917342\", \"width\": 1080, \"height\": 607}], \"variants\": {}, \"id\": \"2gTFrHRV1TM9BtfRPMjORUCNy-_4LLNAsFlZdK-ifpM\"}], \"enabled\": false}, \"media_only\": false, \"can_gild\": true, \"spoiler\": false, \"locked\": false, \"author_flair_text\": null, \"rte_mode\": \"markdown\", \"visited\": false, \"num_reports\": null, \"distinguished\": \"moderator\", \"subreddit_id\": \"t5_2r5qg\", \"mod_reason_by\": null, \"removal_reason\": null, \"id\": \"8ly11u\", \"report_reasons\": null, \"author\": \"123icebuggy\", \"num_crossposts\": 0, \"num_comments\": 0, \"send_replies\": true, \"mod_reports\": [], \"author_flair_text_color\": null, \"permalink\": \"/r/dogpictures/comments/8ly11u/dog_of_the_month_month_5_uthisepiphany/\", \"whitelist_status\": \"all_ads\", \"stickied\": true, \"url\": \"https://www.reddit.com/r/dogpictures/comments/8ly11u/dog_of_the_month_month_5_uthisepiphany/\", \"subreddit_subscribers\": 212689, \"created_utc\": 1527212277.0, \"media\": null, \"is_video\": false}";


    @Spy
    RedditCacheReadHandler handler;

    @Before
    public void setup(){
        Mockito.when(handler.getAmazonDynamoDB()).thenReturn(dynamoDB);
        persistCacheManager.putEvent(REDDIT_EVENTS, TEST);
    }

    @Test
    public void handleRequest() throws Exception {
        CacheRequest request = new CacheRequest();
        request.setSubtopic("dogpictures");
        CacheResponse response = handler.handleRequest(request, getContext());
        Assert.assertNotNull(response);
        Collection<String> bodies = response.getBody();
        Assert.assertNotNull(bodies);
        Assert.assertTrue(bodies.size() == 1);
    }

}