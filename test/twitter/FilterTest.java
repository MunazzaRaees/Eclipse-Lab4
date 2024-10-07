/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FilterTest {

    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T09:00:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T12:00:00Z");

    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "alyssa", "another tweet by alyssa", d3);
    private static final Tweet tweet4 = new Tweet(4, "bob", "this tweet is by bob", d4);
    


    // Test cases for writtenBy
    @Test
    public void testWrittenByMultipleTweetsSingleResult() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2), "alyssa");
        
        assertEquals("expected singleton list", 1, writtenBy.size());
        assertTrue("expected list to contain tweet", writtenBy.contains(tweet1));
    }

    @Test
    public void testWrittenByNoMatch() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2), "charlie");
        
        assertTrue("expected empty list", writtenBy.isEmpty());
    }

    @Test
    public void testWrittenByMultipleMatches() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2, tweet3), "alyssa");
        
        assertEquals("expected two tweets", 2, writtenBy.size());
        assertTrue("expected list to contain tweet1", writtenBy.contains(tweet1));
        assertTrue("expected list to contain tweet3", writtenBy.contains(tweet3));
    }

    @Test
    public void testWrittenByEmptyList() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(), "alyssa");
        
        assertTrue("expected empty list", writtenBy.isEmpty());
    }

    // Test cases for inTimespan
    @Test
    public void testInTimespanMultipleTweetsMultipleResults() {
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2, tweet3), new Timespan(d3, d4));
        
        assertFalse("expected non-empty list", inTimespan.isEmpty());
        assertTrue("expected list to contain tweets", inTimespan.containsAll(Arrays.asList(tweet1, tweet2, tweet3)));
    }

    @Test
    public void testInTimespanNoResults() {
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(Instant.parse("2016-02-17T12:01:00Z"), Instant.parse("2016-02-17T12:02:00Z")));
        
        assertTrue("expected empty list", inTimespan.isEmpty());
    }

    @Test
    public void testInTimespanSingleTweet() {
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(d1, d1));
        
        assertEquals("expected one tweet", 1, inTimespan.size());
        assertTrue("expected list to contain tweet1", inTimespan.contains(tweet1));
    }

    @Test
    public void testInTimespanEmptyList() {
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(), new Timespan(d1, d4));
        
        assertTrue("expected empty list", inTimespan.isEmpty());
    }

    // Test cases for containing
    @Test
    public void testContainingWordFound() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2), Arrays.asList("talk"));
        
        assertFalse("expected non-empty list", containing.isEmpty());
        assertTrue("expected list to contain tweet1", containing.contains(tweet1));
        assertTrue("expected list to contain tweet2", containing.contains(tweet2));
    }

    @Test
    public void testContainingWordNotFound() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2), Arrays.asList("nonexistent"));
        
        assertTrue("expected empty list", containing.isEmpty());
    }

    @Test
    public void testContainingMultipleWords() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2), Arrays.asList("talk", "rivest"));
        
        assertFalse("expected non-empty list", containing.isEmpty());
        assertTrue("expected list to contain tweet1", containing.contains(tweet1));
        assertTrue("expected list to contain tweet2", containing.contains(tweet2));
    }

    @Test
    public void testContainingIgnoreCase() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2), Arrays.asList("RIVEST"));
        
        assertFalse("expected non-empty list", containing.isEmpty());
        assertTrue("expected list to contain tweet2", containing.contains(tweet2));
    }

    @Test
    public void testContainingEmptyList() {
        List<Tweet> containing = Filter.containing(Arrays.asList(), Arrays.asList("talk"));
        
        assertTrue("expected empty list", containing.isEmpty());
    }
}
