# CachePot - Android Simple Data Cache

[![Build Status](https://travis-ci.org/kimkevin/CachePot.svg?branch=master)](https://travis-ci.org/kimkevin/CachePot)
[![Download](https://api.bintray.com/packages/kimkevin/maven/com.github.kimkevin%3Acachepot/images/download.svg) ](https://bintray.com/kimkevin/maven/com.github.kimkevin%3Acachepot/_latestVersion)

CachePot is a simple open source for data cache management and passing data object between Fragments without Pacelable and Serializable.

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

#### 1. Pass Object(Model) Synchronously

First push your data object to `CachePot` instance in your `Activity` or `Fragment`.

```java
// Sample data model
KoreanFood foodItem = new KoreanFood(1, "Kimchi", "Traditional fermented Korean side dish made of vegetables")  

CachePot.getInstance().push(foodItem);
// open your activity or fragment
```

Get your data object in your `Activity` or `Fragment`

```java
public class MainFragment extends Fragment{
    private KoreanFood foodItem;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        foodItem = CachePot.getInstance().pop(KoreanFood.class);
    }
```

#### 2. Pass Collection or Map Synchronously

First push your collection to `CachePot` instance in your `Activity` or `Fragment`.

```java
List<KoreanFood> foodItems = new ArrayList<>();
foodItems.add(new KoreanFood(1, "Kimchi", "Traditional fermented Korean side dish made of vegetables"));
foodItems.add(new KoreanFood(2, "Kkakdugi", "A variety of kimchi in Korean cuisine"));

CachePot.getInstance().push(foodItems);
// open your activity or fragment
```

Get your collection in your `Activity` or `Fragment`

```java
List<KoreanFood> foodItems = CachePot.getInstance().pop(ArrayList.class);
```

#### 3. Pass Object(Model) Asynchronously when using ViewPager

First push your data object with position to `CachePot` instance in `FragmentStatePagerAdapter`(or `FragmentPagerAdapter`)

```java
private class PagerAdapter extends FragmentStatePagerAdapter {
    ...
    public Fragment getItem(int position) {
        CachePot.getInstance().push(position, foodItems.get(position));

        return FoodFragment.newInstance(position);
    }
}
```

Get your data object in your `Fragment` of `ViewPager`

```java
public static FoodFragment newInstance(int position) {
    FoodFragment fragment = new FoodFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_POSITION, position);
    fragment.setArguments(args);
    return fragment;
}

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments() != null) {
        final int position = getArguments().getInt(ARG_POSITION);
        koreanFoodItem = CachePot.getInstance().pop(position);
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
