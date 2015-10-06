package com.kimkevim.datacache.sample.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.kimkevim.datacache.KimchiDataCache;
import com.kimkevim.datacache.R;
import com.kimkevim.datacache.sample.model.KoreanFood;
import com.kimkevim.datacache.sample.util.NaviUtil;

public class FoodFragment extends Fragment {
    private static final String TAG = FoodFragment.class.getSimpleName();

    private static String ARG_POSITION = "position";

    private TextView txt;
    private Button btn;

    private KoreanFood koreanFoodItem;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_food, container, false);

        txt = (TextView) rootView.findViewById(R.id.main_txt);
        btn = (Button) rootView.findViewById(R.id.main_btn);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (koreanFoodItem == null) return;

        txt.setText("It's " + koreanFoodItem.getName());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KimchiDataCache.getInstance().push(koreanFoodItem);

                NaviUtil.addFragment(getActivity(), new FoodDetailFragment());
            }
        });
    }
}
