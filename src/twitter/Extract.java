package twitter;

import java.time.Instant; // Import for Instant
import java.util.HashSet; // Import for HashSet
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher; // Import for Matcher
import java.util.regex.Pattern; // Import for Pattern
import java.util.NoSuchElementException; // Import for exception

public class Extract {

    /**
     * Get the time span (start and end) of the tweets
     * @param tweets a list of tweets
     * @return the timespan from the earliest to the latest tweet
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
        if (tweets.isEmpty()) {
            // Handle empty list, decide to throw an exception or return a default timespan
            throw new IllegalArgumentException("Tweet list cannot be empty");
        }
        Instant start = tweets.stream()
                              .map(Tweet::getTimestamp)
                              .min(Instant::compareTo)
                              .orElseThrow(() -> new NoSuchElementException("No start time found"));

        Instant end = tweets.stream()
                            .map(Tweet::getTimestamp)
                            .max(Instant::compareTo)
                            .orElseThrow(() -> new NoSuchElementException("No end time found"));

        return new Timespan(start, end); }
//	alternative implementation 1
//	public static Timespan getTimespan(List<Tweet> tweets) {
//	    List<Tweet> sortedTweets = new ArrayList<>(tweets);
//	    sortedTweets.sort(Comparator.comparing(Tweet::getTimestamp));
//
//	    Instant start = sortedTweets.get(0).getTimestamp();
//	    Instant end = sortedTweets.get(sortedTweets.size() - 1).getTimestamp();
//
//	    return new Timespan(start, end);
//	}
//	alternative implementation 2
//	public static Timespan getTimespan(List<Tweet> tweets) {
//	    List<Instant> timestamps = tweets.stream()
//	                                     .map(Tweet::getTimestamp)
//	                                     .collect(Collectors.toList());
//	    Instant start = Collections.min(timestamps);
//	    Instant end = Collections.max(timestamps);//
//	    return new Timespan(start, end);
//	}

//	alternative implementation 3
//    public static Timespan getTimespan(List<Tweet> tweets) {
//        if (tweets.isEmpty()) {
//            // Handle empty list, decide to throw an exception or return a default timespan
//            throw new IllegalArgumentException("Tweet list cannot be empty");
//        }
//        Instant start = tweets.stream()
//                              .map(Tweet::getTimestamp)
//                              .min(Instant::compareTo)
//                              .orElseThrow(() -> new NoSuchElementException("No start time found"));
//
//        Instant end = tweets.stream()
//                            .map(Tweet::getTimestamp)
//                            .max(Instant::compareTo)
//                            .orElseThrow(() -> new NoSuchElementException("No end time found"));
//
//        return new Timespan(start, end); }

    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        Set<String> mentionedUsers = new HashSet<>();
        Pattern mentionPattern = Pattern.compile("(?<!\\w)@([A-Za-z0-9_]+)(?!\\w)");

        for (Tweet tweet : tweets) {
            Matcher matcher = mentionPattern.matcher(tweet.getText());
            while (matcher.find()) {
                mentionedUsers.add(matcher.group(1).toLowerCase()); // case-insensitive
            }
        }

        return mentionedUsers;}}