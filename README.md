# CachePot - Android Simple Data Cache

[![Build Status](https://travis-ci.org/kimkevin/CachePot.svg?branch=master)](https://travis-ci.org/kimkevin/CachePot)
[![Download](https://api.bintray.com/packages/kimkevin/maven/com.github.kimkevin%3Acachepot/images/download.svg) ](https://bintray.com/kimkevin/maven/com.github.kimkevin%3Acachepot/_latestVersion)
[![API](https://img.shields.io/badge/API-14%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=14)

CachePot is a simple open source for data cache management and passing data object between Fragments(Activities) without Pacelable and Serializable.

> WARNING: It would be inappropriate to pass data both ways between different android applications, it's a better way to use `Intent` properly.

# Download

Gradle:

```bash
repositories {
  jcenter()
}

dependencies {
  compile 'com.github.kimkevin:cachepot:1.1.0'
}
```

Maven:

```bash
<dependency>
  <groupId>com.github.kimkevin</groupId>
  <artifactId>cachepot</artifactId>
  <version>1.1.0</version>
</dependency>
```

# Usage

It works anywhere. there're examples as below
* Between `Activizty` and `Activity`
* Between `Activity` and `Fragment`
* Between `Fragment` and `Fragment`
* From `PagerAdapter` to individual `Fragment`

## 1. Signle Type, Single Data

Push your data object to `CachePot` instance in your `Activity` or `Fragment`.
> public void push(Object data)

```java
KoreanFood foodItem = new KoreanFood(1, "Kimchi", "Traditional fermented Korean side dish made of vegetables")  
CachePot.getInstance().push(foodItem);
// open your activity or fragment
```

Pop your data object from `CachePot` after view changes
> public Object pop(Class classType)

```java
public class MainFragment extends Fragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        KoreanFood foodItem = CachePot.getInstance().pop(KoreanFood.class);
    }
```

#### In case of Collection or Map

> Push

```java
List<KoreanFood> foodItems = new ArrayList<>();
foodItems.add(new KoreanFood(1, "Kimchi", "Traditional fermented Korean side dish made of vegetables"));
foodItems.add(new KoreanFood(2, "Kkakdugi", "A variety of kimchi in Korean cuisine"));

CachePot.getInstance().push(foodItems);
// open your activity or fragment
```

> Pop

```java
List<KoreanFood> foodItems = CachePot.getInstance().pop(ArrayList.class);
```

## 2. Signle Type, Multiple Data

How to pass Object(Model) Asynchronously when using ViewPager

First push your data object with `position` or `id` to `CachePot` instance in `FragmentStatePagerAdapter`(or `FragmentPagerAdapter`)

> public void push(long id, Object data)

```java
private class PagerAdapter extends FragmentStatePagerAdapter {
    ...
    public Fragment getItem(int position) {
        KoreanFood foodItem = foodItems.get(position);
        CachePot.getInstance().push(TAG, position, foodItem);
        // or
        CachePot.getInstance().push(TAG, foodItem.getId(), foodItem);
        ...
    }
}
```

> public Object pop(long id)

```java
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ...
    if (getArguments() != null) {
        final int position = getArguments().getInt(ARG_POSITION);
        foodItem = CachePot.getInstance().pop(position);
        // or
        foodItem = CachePot.getInstance().pop(id);
    }
}
```

**If more complecated views, use TAG**

> public void push(String tag, long id, Object data)

```java
private class PagerAdapter extends FragmentStatePagerAdapter {
    ...
    public Fragment getItem(int position) {
        KoreanFood foodItem = foodItems.get(position);
        CachePot.getInstance().push(TAG, position, foodItem);
        // or
        CachePot.getInstance().push(TAG, foodItem.getId(), foodItem);
        ...
    }
}
```

> public Object pop(String tag, long id)

```java
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ...
    if (getArguments() != null) {
        final int position = getArguments().getInt(ARG_POSITION);
        foodItem = CachePot.getInstance().pop(TAG, position);
        // or
        foodItem = CachePot.getInstance().pop(TAG, id);
    }
}
```

# Developed By
* Kevin Yongjun Kim - imkimkevin@gmail.com

# Contributing
All contributions are welcome. Open a [Pull Requests](https://github.com/kimkevin/CachePot/pulls) or refer to
the [Issues](https://github.com/kimkevin/CachePot/issues) section.

# License
MIT
