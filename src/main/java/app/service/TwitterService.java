package app.service;
import java.io.File;

import org.springframework.stereotype.Service;

import app.config.TwitterConfig;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Service
public class TwitterService {
	
	TwitterFactory tf = new TwitterFactory(TwitterConfig.configurationBuilder().build());
	Twitter twitter = tf.getInstance();
	
	public String createTweet(String tweet) throws TwitterException {
	    Status status = twitter.updateStatus("Rest Call on load");
	    return status.getText();
	}
	
	public String addImage(String message, File image) throws TwitterException {
		System.out.println("reaching here");
		StatusUpdate statusUpdate = new StatusUpdate(message);
		statusUpdate.setMedia(image);
		return String.valueOf(twitter.updateStatus(statusUpdate).getId());
	}
}
