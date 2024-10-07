/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    

//    addtitional test cases for get timespan class

	/*
	 * Additional Test Case 1: Test with Tweets Having Same Timestamp
	 * This test checks how the method behaves when all tweets have the same timestamp.
	 */
//	@Test
//	public void testGetTimespanSameTimestampTweets() {
//	    Tweet tweetWithSameTime1 = new Tweet(3, "alyssa", "tweet with same timestamp 1", d1);
//	    Tweet tweetWithSameTime2 = new Tweet(4, "bob", "tweet with same timestamp 2", d1);
//	
//	    Timespan timespan = Extract.getTimespan(Arrays.asList(tweetWithSameTime1, tweetWithSameTime2));
//	    
//	    assertEquals("expected start and end to be the same", d1, timespan.getStart());
//	    assertEquals("expected start and end to be the same", d1, timespan.getEnd());
//	}
	
	/*
	 * Additional Test Case 2: Test with Empty List of Tweets
	 * This test checks for an exception when an empty list of tweets is provided.
	 */
//	@Test(expected = IllegalArgumentException.class)
//	public void testGetTimespanEmptyList() {
//	    Extract.getTimespan(Collections.emptyList());
//	}
//	
	/*
	 * Additional Test Case 3: Test with Tweets in Reverse Order
	 * This test checks if the method works correctly when the tweets are passed in reverse order.
	 */
//	@Test
//	public void testGetTimespanReverseOrder() {
//	    Timespan timespan = Extract.getTimespan(Arrays.asList(tweet2, tweet1)); // tweet2 has a later timestamp than tweet1
//	    
//	    assertEquals("expected start", d1, timespan.getStart());
//	    assertEquals("expected end", d2, timespan.getEnd());
//	}
//	
	/*
	 * Additional Test Case 4: Test with More Than Two Tweets
	 * This test checks the general case with more than two tweets and different timestamps.
	 */
//	@Test
//	public void testGetTimespanThreeTweets() {
//	    Instant d3 = Instant.parse("2016-02-17T12:00:00Z");
//	    Tweet tweet3 = new Tweet(3, "charlie", "third tweet", d3);
//	
//	    Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2, tweet3));
//	
//	    assertEquals("expected start", d1, timespan.getStart());
//	    assertEquals("expected end", d3, timespan.getEnd());
//	}
	
	/*
	 * Additional Test Case 5: Test with Maximum and Minimum Timestamps
	 * This test checks if the method can correctly handle extreme timestamp values.
	 */
//	@Test
//	public void testGetTimespanMaxMinTimestamps() {
//	    Instant earliest = Instant.parse("2015-01-01T00:00:00Z");
//	    Instant latest = Instant.parse("2020-01-01T00:00:00Z");
//	    
//	    Tweet earlyTweet = new Tweet(5, "dave", "early tweet", earliest);
//	    Tweet lateTweet = new Tweet(6, "eva", "late tweet", latest);
//	    
//	    Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2, earlyTweet, lateTweet));
//	    
//	    assertEquals("expected start", earliest, timespan.getStart());
//	    assertEquals("expected end", latest, timespan.getEnd());
//	}
    
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }
    
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }

    @Test
    public void testGetTimespanSingleTweet() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1));
        
        assertEquals("expected start and end to be the same for single tweet", d1, timespan.getStart());
        assertEquals("expected start and end to be the same for single tweet", d1, timespan.getEnd());
    }

    @Test
    public void testGetTimespanMultipleTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }

    /* getMentionedUsers
     * Additional Test Case 1: Test for Case Insensitivity
     * This test checks if the function handles usernames in a case-insensitive manner.
     */
//    @Test
//    public void testGetMentionedUsersCaseInsensitive() {
//        Tweet tweetWithCaseSensitiveMentions = new Tweet(6, "user", "Hey @Alice, meet @ALICE", d1);
//        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweetWithCaseSensitiveMentions));
//        
//        assertTrue("expected only one unique mentioned user", mentionedUsers.size() == 1);
//        assertTrue("expected case-insensitive mention", mentionedUsers.contains("alice"));
//    }

    /*
     * Additional Test Case 2: Test with Consecutive Mentions
     * This test checks if consecutive mentions are handled correctly.
     */
//    @Test
//    public void testGetMentionedUsersConsecutiveMentions() {
//        Tweet tweetWithConsecutiveMentions = new Tweet(7, "user", "Hello @alice@bob!", d1);
//        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweetWithConsecutiveMentions));
//        
//        assertTrue("expected valid mentions", mentionedUsers.contains("alice"));
//        assertFalse("expected invalid mention not to be recognized", mentionedUsers.contains("bob"));
//    }

    /*
     * Additional Test Case 3: Test with Non-alphanumeric Characters
     * This test checks if mentions with non-alphanumeric characters after the "@" symbol are ignored.
     */
//    @Test
//    public void testGetMentionedUsersNonAlphanumeric() {
//        Tweet tweetWithInvalidMention = new Tweet(8, "user", "Hello @alice! and @bob$, how are you?", d1);
//        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweetWithInvalidMention));
//        
//        assertTrue("expected valid mention", mentionedUsers.contains("alice"));
//        assertFalse("expected invalid mention with special character", mentionedUsers.contains("bob$"));
//    }

    /*
     * Additional Test Case 4: Test with Multiple Tweets
     * This test checks if the function correctly identifies mentioned users across multiple tweets.
     */
//    @Test
//    public void testGetMentionedUsersMultipleTweets() {
//        Tweet tweetWithMention1 = new Tweet(9, "user", "Hello @alice!", d1);
//        Tweet tweetWithMention2 = new Tweet(10, "user", "Hey @bob!", d2);
//        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweetWithMention1, tweetWithMention2));
//        
//        assertTrue("expected mentioned user alice", mentionedUsers.contains("alice"));
//        assertTrue("expected mentioned user bob", mentionedUsers.contains("bob"));
//    }

    /*
     * Additional Test Case 5: Test with Multiple Mentions and Different Formats
     * This test checks if the function correctly extracts usernames in various formats (upper, lower, mixed).
     */
//    @Test
//    public void testGetMentionedUsersDifferentFormats() {
//        Tweet tweetWithMentions = new Tweet(11, "user", "Hi @Alice, @BOB and @Charlie_123!", d1);
//        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweetWithMentions));
//        
//        assertTrue("expected mentioned user alice", mentionedUsers.contains("alice"));
//        assertTrue("expected mentioned user bob", mentionedUsers.contains("bob"));
//        assertTrue("expected mentioned user charlie_123", mentionedUsers.contains("charlie_123"));
//    }
    @Test    public void testGetMentionedUsersSingleMention() {
        Tweet tweetWithMention = new Tweet(3, "bbitdiddle", "Hello @alice!", d1);
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweetWithMention));
        
        assertTrue("expected one mentioned user", mentionedUsers.contains("alice"));
    }

//    test case modified to cause failure
//    @Test    public void testGetMentionedUsersSingleMention() {
//        Tweet tweetWithMention = new Tweet(3, "bbitdiddle", "Hello!", d1);
//        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweetWithMention));
//        
//        assertTrue("expected one mentioned user", mentionedUsers.contains("alice"));
//    }
    @Test
    public void testGetMentionedUsersMultipleMentions() {
        Tweet tweetWithMentions = new Tweet(4, "bbitdiddle", "Hello @alice and @bob!", d2);
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweetWithMentions));
        
        assertTrue("expected mentioned user alice", mentionedUsers.contains("alice"));
        assertTrue("expected mentioned user bob", mentionedUsers.contains("bob"));
    }

    @Test
    public void testGetMentionedUsersIgnoreInvalidMention() {
        Tweet tweetWithInvalidMention = new Tweet(5, "bbitdiddle", "email address bitdiddle@mit.edu", d1);
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweetWithInvalidMention));
        
        assertTrue("expected empty set for invalid mention", mentionedUsers.isEmpty());
    }

}
