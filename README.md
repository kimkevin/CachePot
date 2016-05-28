# Android Simple DataCache

A basic sample which shows how to transfer object of model between fragments simply without Pacelable and Serializable.

# Usage
You can copy only DataCache file to your util folder.

It works anywhere as below
* Activizty <--> Activity
* Activity <--> Fragment
* Fragment <--> Fragment

#### 1. Transfer Object(Model) Synchronously

First push your data object to `DataCache` instance in your `Activity` or `Fragment`.

```java
KoreanFood foodItem = new KoreanFood(1, "Kimchi", "Traditional fermented Korean side dish made of vegetables")  // Sample Model

DataCache.getInstance().push(foodItem);
// open your activity or fragment
```

Get your data object in your `Activity` or `Fragment`

```java
public class MainFragment extends Fragment{
    private KoreanFood foodItem;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        foodItem = DataCache.getInstance().pop(KoreanFood.class);
    }
```

#### 2. Transfer Collection Synchronously

First push your collection to `DataCache` instance in your `Activity` or `Fragment`.

```java
List<KoreanFood> foodItems = new ArrayList<>();
foodItems.add(new KoreanFood(1, "Kimchi", "Traditional fermented Korean side dish made of vegetables"));
foodItems.add(new KoreanFood(2, "Kkakdugi", "A variety of kimchi in Korean cuisine"));

DataCache.getInstance().push(foodItems);
// open your activity or fragment
```

Get your collection in your `Activity` or `Fragment`

```java
List<KoreanFood> foodItems = DataCache.getInstance().pop(ArrayList.class);
```

#### 3. Transfer Object(Model) Asynchronously when using ViewPager

First push your data object with position to `DataCache` instance in `FragmentStatePagerAdapter`(or `FragmentPagerAdapter`)

```java
private class PagerAdapter extends FragmentStatePagerAdapter {
    ...
    public Fragment getItem(int position) {
        DataCache.getInstance().push(position, foodItems.get(position));

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
        koreanFoodItem = DataCache.getInstance().pop(position);
    }
}
```

# Developed By
* Kevin Yongjun Kim - imkimkevin@gmail.com

# Contributing
All contributions are welcome. Open a [Pull Requests](https://github.com/kimkevin/AndroidDataCache/pulls) or refer to
the [Issues](https://github.com/kimkevin/AndroidDataCache/issues) section.

# License
MIT
