package com.kimkevim.datacache.sample.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Log.e(TAG, " onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, " onCreate");

        userItem = KimchiDataCache.getInstance().get(User.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, " onCreateView");

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        txt = (TextView) rootView.findViewById(R.id.detail_txt);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, " onViewCreated");

        txt.setText("Hello, " + userItem.getName() + " again.");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, " onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, " onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, " onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, " onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, " onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, " onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, " onDetach");
    }
}
