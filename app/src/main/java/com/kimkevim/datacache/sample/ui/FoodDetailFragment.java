package com.kimkevim.datacache.sample.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.kimkevim.datacache.DataCache;
import com.kimkevim.datacache.R;
import com.kimkevim.datacache.sample.model.KoreanFood;

public class FoodDetailFragment extends Fragment{
    private static final String TAG = FoodDetailFragment.class.getSimpleName();

    private KoreanFood koreanFoodItem;

    private TextView nameTxt;
    private TextView descTxt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        koreanFoodItem = DataCache.getInstance().pop(KoreanFood.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        nameTxt = (TextView) rootView.findViewById(R.id.name_txt);
        descTxt = (TextView) rootView.findViewById(R.id.desc_txt);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameTxt.setText(koreanFoodItem.getName());
        descTxt.setText(koreanFoodItem.getDescription());
    }
}
