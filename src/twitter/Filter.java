package twitter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.hamcrest.Matcher;

public class Filter {

    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
        List<Tweet> result = new ArrayList<>();
        for (Tweet tweet : tweets) {
            if (tweet.getAuthor().equals(username)) {
                result.add(tweet);
            }
        }
        return result;
    }

    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
        List<Tweet> result = new ArrayList<>();
        for (Tweet tweet : tweets) {
            if (timespan.contains(tweet.getTimestamp())) {
                result.add(tweet);
            }
        }
        return result;
    }

//    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
//        List<Tweet> result = new ArrayList<>();
//        for (Tweet tweet : tweets) {
//            for (String word : words) {
//                if (tweet.getText().toLowerCase().contains(word.toLowerCase())) {
//                    result.add(tweet);
//                    break; // Found a matching word, no need to check further
//                }
//            }
//        }
//        return result;
//    }
//    alterative imp 1
//    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
//        return tweets.stream()
//                .filter(tweet -> words.stream()
//                        .anyMatch(word -> tweet.getText().toLowerCase().contains(word.toLowerCase())))
//                .collect(Collectors.toList());
//    }
//  alterative imp 2
//    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
//        if (words.isEmpty()) {
//            return new ArrayList<>(); // Early return for empty word list
//        }
//
//        String patternString = String.join("|", words.stream()
//                .map(word -> "\\b" + Pattern.quote(word) + "\\b") // Word boundary regex
//                .toArray(String[]::new));
//        
//        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
//        
//        List<Tweet> result = new ArrayList<>();
//        for (Tweet tweet : tweets) {
//            java.util.regex.Matcher matcher = pattern.matcher(tweet.getText());
//            if (matcher.find()) {
//                result.add(tweet);
//            }
//        }
//        return result;
//    }

//  alterative imp 3
    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
        Set<String> uniqueWords = new HashSet<>(words);
        List<Tweet> result = new ArrayList<>();
        
        for (Tweet tweet : tweets) {
            for (String word : uniqueWords) {
                if (tweet.getText().toLowerCase().contains(word.toLowerCase())) {
                    result.add(tweet);
                    break; // Found a matching word, no need to check further
                }
            }
        }
        return result;
    }


}
