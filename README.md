# CachePot - Android Simple Data Cache

A basic sample which shows how to transfer object of model between fragments simply without Pacelable and Serializable.

# Download
```java
repositories {
  jcenter()
}

dependencies {
  compile 'com.github.kimkevin:cachepot:1.0.0'
}
```

# Usage
You can copy only CachePot file to your util folder.

It works anywhere as below
* Between Activizty and Activity
* Between Activity and Fragment
* Between Fragment and Fragment

#### 1. Transfer Object(Model) Synchronously

First push your data object to `CachePot` instance in your `Activity` or `Fragment`.

```java
KoreanFood foodItem = new KoreanFood(1, "Kimchi", "Traditional fermented Korean side dish made of vegetables")  // Sample Model

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

#### 2. Transfer Collection or Map Synchronously

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

#### 3. Transfer Object(Model) Asynchronously when using ViewPager

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
