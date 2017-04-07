package org.webpieces.app.example1.model;

public class Tweet {
  private String id;
  private String fromUser;
  private String text;

  public Tweet(String id, String fromUser, String text) {
    this.id = id;
    this.fromUser = fromUser;
    this.text = text;
  }

  public String getId() {
    return id;
  }

  public String getFromUser() {
    return fromUser;
  }

  public String getText() {
    return text;
  }

  @Override
  public String toString() {
    return "Tweet{" +
        "id='" + id + '\'' +
        ", fromUser='" + fromUser + '\'' +
        ", text='" + text + '\'' +
        '}';
  }
}
