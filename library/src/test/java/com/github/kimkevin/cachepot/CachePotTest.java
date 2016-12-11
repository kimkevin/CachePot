package com.github.kimkevin.cachepot;


import org.junit.Test;

public class CachePotTest {

  @Test
  public void PushTest() throws Exception {
    User user = new User(0, "Double K");

    CachePot.getInstance().push(user);
  }

  private class User {
    public long id;
    public String name;

    public User(long id, String name) {
      this.id = id;
      this.name = name;
    }
  }
}
