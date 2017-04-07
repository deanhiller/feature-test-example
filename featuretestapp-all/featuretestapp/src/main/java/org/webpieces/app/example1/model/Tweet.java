package org.webpieces.app.example1.model;

public class Tweet {
  private String fromUser;
  private String text;

  public Tweet(String fromUser, String text) {
    this.fromUser = fromUser;
    this.text = text;
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
        "fromUser='" + fromUser + '\'' +
        ", text='" + text + '\'' +
        '}';
  }
}
