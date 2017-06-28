package org.webpieces.app.example1.model.db;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TWEETS")
@NamedQueries({
    @NamedQuery(name = "findAllTweets", query = "select u from TweetDbo as u")
//    @NamedQuery(name = "findByName", query = "select u from TweetDbo as u where u.name=:name")
})
public class TweetDbo {
  @Id
  @SequenceGenerator(name = "tweet_id_gen", sequenceName = "tweet_sequence", initialValue = 1, allocationSize = 10)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tweet_id_gen")
  private Integer id;

  private String fromUser;
  private String text;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFromUser() {
    return fromUser;
  }

  public void setFromUser(String fromUser) {
    this.fromUser = fromUser;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getText() {
    return text;

  }

  @SuppressWarnings("unchecked")
  public static List<TweetDbo> findAll(EntityManager mgr) {
    Query query = mgr.createNamedQuery("findAllTweets");
    return query.getResultList();
  }

  public static TweetDbo findById(EntityManager mgr, Integer id) {
    try {
      return (TweetDbo) mgr.find(TweetDbo.class, id);
    } catch (NoResultException e) {
      return null;
    }
  }
}
