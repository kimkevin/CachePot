package com.kimkevim.datacache.sample.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.kimkevim.datacache.KimchiDataCache;
import com.kimkevim.datacache.R;
import com.kimkevim.datacache.sample.model.User;

public class UserDetailFragmnet extends Fragment{
    private static final String TAG = UserDetailFragmnet.class.getSimpleName();

    private User userItem;

    private TextView txt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userItem = KimchiDataCache.getInstance().get(User.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        txt = (TextView) rootView.findViewById(R.id.detail_txt);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txt.setText("Hello, " + userItem.getName() + " again.");
    }
}
