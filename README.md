# KimchiDataCache

A basic sample which shows how to transfer object of model between fragments simply without Pacelable and Serializable.

# Usage
You can copy only KimchiDataCache file to your util folder.

**1. Transfer Object(Model) Synchronously between activities and fragments**

First push your data object to `KimchiDataCache` instance in your `Activity` or `Fragment`.

```java
KoreanFood foodItem = new KoreanFood(1, "Kimchi", "Traditional fermented Korean side dish made of vegetables")  // Sample Model

KimchiDataCache.getInstance().push(foodItem);

// add your activity or fragment
```

Get your data object in your Activity of Fragment that you added.

```java
public class MainFragment extends Fragment{
    private KoreanFood foodItem;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        foodItem = KimchiDataCache.getInstance().pop(KoreanFood.class);
    }
```

**2. Transfer Object(Model) Asynchronously when using ViewPager**

First push your data object with position to `KimchiDataCache` instance in `FragmentStatePagerAdapter`(or `FragmentPagerAdapter`)

```java
private class PagerAdapter extends FragmentStatePagerAdapter {

    ...
    
    public Fragment getItem(int position) {
        KimchiDataCache.getInstance().push(position, foodItems.get(position));

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
        koreanFoodItem = KimchiDataCache.getInstance().pop(position);
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
