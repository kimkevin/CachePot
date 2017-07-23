package com.github.kimkevin.cachepot.sample.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.kimkevin.cachepot.CachePot;
import com.github.kimkevin.cachepot.sample.R;
import com.github.kimkevin.cachepot.sample.model.KoreanFood;

import java.util.ArrayList;
import java.util.List;

public class JarFragment extends Fragment {
  private static final String TAG = JarFragment.class.getSimpleName();

  private ViewPager mViewPager;

  private List<KoreanFood> foodItems;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    foodItems = new ArrayList<>();
    foodItems.add(new KoreanFood(1, "Kimchi", "Traditional fermented Korean side dish made of vegetables"));
    foodItems.add(new KoreanFood(2, "Kkakdugi", "A variety of kimchi in Korean cuisine"));
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_main, container, false);
    mViewPager = (ViewPager) rootView.findViewById(R.id.main_pager);

    return rootView;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    PagerAdapter mPagerAdapter = new PagerAdapter(getFragmentManager());
    mViewPager.setAdapter(mPagerAdapter);
  }

  private class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      /**
       * Put your Obejct with Position to DataCache
       */
      CachePot.getInstance().push(position, foodItems.get(position));

      return FoodFragment.newInstance(position);
    }

    @Override
    public int getCount() {
      return foodItems.size();
    }
  }
}
