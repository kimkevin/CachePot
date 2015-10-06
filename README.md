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
public Fragment getItem(int position) {
    KimchiDataCache.getInstance().push(position, foodItems.get(position));

    return FoodFragment.newInstance(position);
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
    Copyright 2015 William Mora

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
