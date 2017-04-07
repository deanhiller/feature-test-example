package org.webpieces.app.example1.model;

// TODO Switch to UserDbo for DB data
public class User {
  private String userId;
  private String displayName;

  public User(String userId, String displayName) {
    this.userId = userId;
    this.displayName = displayName;
  }

  public String getUserId() {
    return userId;
  }

  public String getDisplayName() {
    return displayName;
  }

  @Override
  public String toString() {
    return "User{" +
        "userId='" + userId + '\'' +
        ", displayName='" + displayName + '\'' +
        '}';
  }
}
