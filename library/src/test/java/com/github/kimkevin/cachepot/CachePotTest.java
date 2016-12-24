package com.github.kimkevin.cachepot;


import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class CachePotTest {
  private class User {
    int id;
    String name;

    User(int id, String name) {
      this.id = id;
      this.name = name;
    }
  }

  @Test
  public void PushTest() throws Exception {
    User user = mock(User.class);

    CachePot.getInstance().push(user);

    assertEquals(1, CachePot.getInstance().size());
    assertThat(CachePot.getInstance().size(), is(greaterThan(0)));

    CachePot.getInstance().clear();
  }

  @Test
  public void MultiplePushTest() throws Exception {
    User user1 = new User(0, "Mike");
    User user2 = new User(1, "Kevin");
    User user3 = new User(2, "Jassi");

    CachePot.getInstance().push(user1);
    CachePot.getInstance().push(user2);
    CachePot.getInstance().push(user3);

    assertThat(1, is(CachePot.getInstance().size()));
    assertThat(user3.id, is(user3.id));
    assertThat(user3.name, is(user3.name));

    CachePot.getInstance().clear();
  }

  @Test
  public void PopTest() throws Exception {
    User user = new User(0, "Mike");
    CachePot.getInstance().push(user);

    User cachedUser = CachePot.getInstance().pop(User.class);

    assertEquals(CachePot.getInstance().size(), 0);
    assertThat(user.id, is(cachedUser.id));
    assertThat(user.name, is(cachedUser.name));

    CachePot.getInstance().clear();
  }

  @Test
  public void PushIdTest() throws Exception {
    User user1 = new User(0, "Mike");
    User user2 = new User(1, "Kevin");
    User user3 = new User(2, "Jassi");

    CachePot.getInstance().push(user1.id, user1);
    CachePot.getInstance().push(user2.id, user2);
    CachePot.getInstance().push(user3.id, user3);

    assertEquals(3, CachePot.getInstance().size());

    CachePot.getInstance().clear();
  }

  @Test
  public void PopPositionTest() throws Exception {
    User user1 = new User(0, "Mike");
    User user2 = new User(1, "Kevin");
    User user3 = new User(2, "Jassi");

    CachePot.getInstance().push(2, user1);
    CachePot.getInstance().push(3, user2);
    CachePot.getInstance().push(4, user3);

    assertEquals(3, CachePot.getInstance().size());

    User cachedUser2 = CachePot.getInstance().pop(3);
    assertThat(user2.name, is(cachedUser2.name));
    assertThat(user2.id, is(cachedUser2.id));

    CachePot.getInstance().clear();
  }

  @Test
  public void PopIdTest() throws Exception {
    User user1 = new User(0, "Mike");
    User user2 = new User(1, "Kevin");
    User user3 = new User(2, "Jassi");

    CachePot.getInstance().push(user1.id, user1);
    CachePot.getInstance().push(user2.id, user2);
    CachePot.getInstance().push(user3.id, user3);

    assertEquals(3, CachePot.getInstance().size());

    User cachedUser2 = CachePot.getInstance().pop(user2.id);
    assertThat(user2.name, is(cachedUser2.name));
    assertThat(user2.id, is(cachedUser2.id));

    CachePot.getInstance().clear();
  }

  @Test
  public void PushTagTest() throws Exception {
    User user1 = new User(0, "Mike");
    User user2 = new User(1, "Kevin");
    User user3 = new User(2, "Jassi");

    CachePot.getInstance().push("Adapter", 0, user1);
    CachePot.getInstance().push("Adapter", 1, user2);
    CachePot.getInstance().push("Adapter", 2, user3);

    assertEquals(1, CachePot.getInstance().size());

    CachePot.getInstance().clear();
  }

  @Test
  public void PopTagTest() throws Exception {
    final String TAG = "Adapter";

    User user1 = new User(0, "Mike");
    User user2 = new User(1, "Kevin");
    User user3 = new User(2, "Jassi");

    CachePot.getInstance().push(TAG, 2, user1);
    CachePot.getInstance().push(TAG, 3, user2);
    CachePot.getInstance().push(TAG, 4, user3);

    assertEquals(1, CachePot.getInstance().size());
    assertEquals(3, CachePot.getInstance().size(TAG));

    User cachedUser2 = CachePot.getInstance().pop(TAG, 3);
    assertThat(user2.name, is(cachedUser2.name));
    assertThat(user2.id, is(cachedUser2.id));

    CachePot.getInstance().clear();
  }

  @Test
  public void ClearTest() throws Exception {
    User user = mock(User.class);

    CachePot.getInstance().push(user);

    assertThat(CachePot.getInstance().size(), is(greaterThan(0)));

    CachePot.getInstance().clear();

    assertThat(CachePot.getInstance().size(), is(equalTo(0)));
  }

  @Test
  public void ClearTagTest() throws Exception {
    final String TAG = "Adapter";

    User user1 = new User(0, "Mike");
    User user2 = new User(1, "Kevin");
    User user3 = new User(2, "Jassi");

    CachePot.getInstance().push(TAG, 2, user1);
    CachePot.getInstance().push(TAG, 3, user2);
    CachePot.getInstance().push(TAG, 4, user3);

    assertThat(CachePot.getInstance().size(TAG), is(greaterThan(0)));
    assertThat(CachePot.getInstance().size(TAG), is(equalTo(3)));

    CachePot.getInstance().clear(TAG);

    assertThat(CachePot.getInstance().size(TAG), is(equalTo(0)));

    CachePot.getInstance().clear();
  }
}
