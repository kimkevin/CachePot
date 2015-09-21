package com.kimkevim.datacache.sample.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.kimkevim.datacache.DataCache;
import com.kimkevim.datacache.R;
import com.kimkevim.datacache.sample.model.User;
import com.kimkevim.datacache.sample.util.NaviUtil;

public class MainFragment extends Fragment{
    private static final String TAG = MainFragment.class.getSimpleName();

    private User userItem;

    private TextView txt;
    private Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userItem = DataCache.getInstance().get(User.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        txt = (TextView) rootView.findViewById(R.id.main_txt);
        btn = (Button) rootView.findViewById(R.id.main_btn);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txt.setText("Hello , " + userItem.getName());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataCache.getInstance().put(userItem);

                NaviUtil.addFragment(getActivity(), new UserDetailFragmnet());
            }
        });
    }
}
