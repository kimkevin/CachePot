package com.github.kimkevin.cachepot;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CachePotTest {
  private static final String TAG = "CachePotTest";

  private CachePot cachePot;
  private User user1;
  private User user2;

  @Before
  public void setUp() throws Exception {
    cachePot = CachePot.getInstance();

    MockitoAnnotations.initMocks(this);

    user1 = new User(0, "test1");
    user2 = new User(1, "test2");
  }

  @After
  public void tearDown() throws Exception {
    cachePot.clear();
  }

  @Test
  public void whenPush_thenSizeIsOne() throws Exception {
    cachePot.push(user1);

    assertEquals(1, CachePot.getInstance().size());
  }

  @Test
  public void whenPop_thenIdIsValid() throws Exception {
    cachePot.push(user1);
    User user = cachePot.pop(User.class);

    assertEquals(user1.getId(), user.getId());
  }

  @Test
  public void whenPushMultipleUser_thenLastUserIsValid() throws Exception {
    cachePot.push(user1);
    cachePot.push(user2);

    User user = cachePot.pop(User.class);

    assertEquals(user2.getId(), user.getId());
  }

  @Test
  public void whenPushMultipleUser_thenUserIsOnlyOne() throws Exception {
    cachePot.push(user1);
    cachePot.push(user2);

    int potSize = cachePot.size();

    assertEquals(1, potSize);
  }

  @Test
  public void whenPushWithId_thenPotSizeIsOne() throws Exception {
    cachePot.push(user1.getId(), user1);

    int potSize = cachePot.size();

    assertEquals(1, potSize);
  }

  @Test
  public void whenPopWithId_thenUserIsValid() throws Exception {
    cachePot.push(user1.getId(), user1);

    User user = cachePot.pop(user1.getId());

    assertNotNull(user);
    assertEquals(user1.getId(), user.getId());
  }

  @Test
  public void whenPushMultipleUsersWithId_thenPotSizeIsTwo() throws Exception {
    cachePot.push(user1.getId(), user1);
    cachePot.push(user2.getId(), user2);

    int potSize = cachePot.size();

    assertEquals(2, potSize);
  }

  @Test
  public void givenMultipleUsersWithId_whenPopFirstUser_thenUserIsValid() throws Exception {
    cachePot.push(user1.getId(), user1);
    cachePot.push(user2.getId(), user2);

    User firstUser = cachePot.pop(user1.getId());

    assertNotNull(firstUser);
    assertEquals(user1.getId(), firstUser.getId());
  }

  @Test
  public void givenMultipleUsersWithId_whenPopWithInvalidId_thenUserIsNull() throws Exception {
    cachePot.push(user1.getId(), user1);
    cachePot.push(user2.getId(), user2);

    User firstUser = cachePot.pop(3);

    assertNull(firstUser);
  }

  @Test
  public void givenMultipleUsersWithId_whenClear_thenPotIsEmpty() throws Exception {
    cachePot.push(user1.getId(), user1);
    cachePot.push(user2.getId(), user2);

    cachePot.clear();

    assertEquals(0, cachePot.size());
  }

  @Test
  public void whenPushUsersWithKey_thenPotSizeIsTwo() throws Exception {
    cachePot.push("user1", user1);
    cachePot.push("user2", user2);

    assertEquals(2, cachePot.size());
  }

  @Test
  public void givenPushUsersWithKey_whenPopLastUserWithValidKey_thenUserIsValid() throws Exception {
    cachePot.push("user1", user1);
    cachePot.push("user2", user2);

    User lastUser = cachePot.pop("user2");

    assertEquals(1, lastUser.getId());
    assertEquals("test2", lastUser.getName());
  }

  @Test
  public void givenPushUsersWithKey_whenPopLastUserWithInvalidKey_thenUserIsInvalid() throws Exception {
    cachePot.push("user1", user1);
    cachePot.push("user2", user2);

    User user = cachePot.pop("user3");

    assertNull(user);
  }

  @Test
  public void whenPushUsersWithTagAndId_thenPotSizeIsTwo() throws Exception {
    cachePot.push(TAG, user1.getId(), user1);
    cachePot.push(TAG, user2.getId(), user2);

    assertEquals(2, cachePot.size(TAG));
  }

  @Test
  public void givenPushUsersWithKeyAndId_whenPopLastUserWithValidKey_thenUserIsValid() throws Exception {
    cachePot.push(TAG, user1.getId(), user1);
    cachePot.push(TAG, user2.getId(), user2);

    User lastUser = cachePot.pop(TAG, user2.getId());

    assertEquals(1, lastUser.getId());
    assertEquals("test2", lastUser.getName());
  }

  @Test
  public void givenPushUsersWithKeyAndId_whenPopLastUserWithInvalidKey_thenUserIsInvalid() throws Exception {
    cachePot.push(TAG, user1.getId(), user1);
    cachePot.push(TAG, user2.getId(), user2);

    User user = cachePot.pop(TAG, 5);

    assertNull(user);
  }

  @Test
  public void givenPushUsersWithKeyAndId_whenClearWithTag_thenGroupPotIsEmpty() throws Exception {
    cachePot.push(TAG, user1.getId(), user1);
    cachePot.push(TAG, user2.getId(), user2);

    cachePot.clear(TAG);

    assertEquals(0, cachePot.size(TAG));
  }

  @Test
  public void givenPushUsersWithKeyAndId_whenPopWithTagId_thenGroupPotIsEmpty() throws Exception {
    cachePot.push(TAG, user1.getId(), user1);
    cachePot.push(TAG, user2.getId(), user2);

    cachePot.pop(TAG, user1.getId());
    cachePot.pop(TAG, user2.getId());

    assertEquals(0, cachePot.size(TAG));
    assertEquals(0, cachePot.size());
  }

  @Test
  public void givenPushUsersWithKeyAndId_whenClearWithTag_thenPotIsEmpty() throws Exception {
    cachePot.push(TAG, user1.getId(), user1);
    cachePot.push(TAG, user2.getId(), user2);

    cachePot.clear(TAG);

    assertEquals(0, cachePot.size());
  }

  private class User {
    int id;
    String name;

    User(int id, String name) {
      this.id = id;
      this.name = name;
    }

    public int getId() {
      return id;
    }

    public String getName() {
      return name;
    }
  }
}
