package org.webpieces.app.base;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.webpieces.app.example1.model.db.TweetDbo;
import org.webpieces.app.example1.model.db.UserDbo;
import org.webpieces.router.api.Startable;
import org.webpieces.util.logging.Logger;
import org.webpieces.util.logging.LoggerFactory;

public class PopulateDatabase implements Startable {

	private static final Logger log = LoggerFactory.getLogger(PopulateDatabase.class);
	private EntityManagerFactory factory;

	@Inject
	public PopulateDatabase(EntityManagerFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void start() {
		createSomeData();
	}

	private void createSomeData() {
		EntityManager mgr = factory.createEntityManager();

		if(UserDbo.findAll(mgr).size() > 0 || TweetDbo.findAll(mgr).size() > 0)
			return; //This database has users, exit immediately to not screw up existing data 

		EntityTransaction tx = mgr.getTransaction();
		tx.begin();

		populateUsers(mgr);
		populateTweets(mgr);

		tx.commit();
	}

	private void populateTweets(EntityManager mgr) {
		mgr.persist(createTweet("pstover", "just setting up my twttr"));
		mgr.persist(createTweet("dhiller", "testing"));

		mgr.flush();
	}

	private void populateUsers(EntityManager mgr) {
		mgr.persist(createUser("dean@somewhere.com", "dhiller", "Dean", "Hiller"));
		mgr.persist(createUser("patrick@somewhere.com", "pstover", "Patrick", "Stover"));

		mgr.flush();
	}

	private Object createUser(String email, String username, String firstName, String lastName) {
		UserDbo user = new UserDbo();

		user.setEmail(email);
		user.setName(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);

		return user;
	}

	private TweetDbo createTweet(String fromUser, String text) {
		TweetDbo tweet = new TweetDbo();
		tweet.setFromUser(fromUser);
		tweet.setText(text);

		return tweet;
	}
}
