package com.vishnus1224.minigithub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vishnus1224.minigithub.R;

/**
 * Created by Vishnu on 2/6/2016.
 */
public class RepositoriesFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_repositories, container, false);

        return view;
    }

    @Override
    public void fetchData() {

    }
}
