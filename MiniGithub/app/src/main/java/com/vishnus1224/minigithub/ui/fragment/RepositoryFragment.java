package com.vishnus1224.minigithub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vishnus1224.minigithub.R;
import com.vishnus1224.minigithub.ui.presenter.Presenter;
import com.vishnus1224.minigithub.ui.presenter.RepositoryPresenter;
import com.vishnus1224.minigithub.ui.view.RepositoryView;

/**
 * Created by Vishnu on 2/6/2016.
 */
public class RepositoryFragment extends BaseFragment implements RepositoryView {

    private Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_repositorie, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter = new RepositoryPresenter();

        //initialize the presenter by passing it the view.
        presenter.init(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();

        presenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.destroy();
    }

    @Override
    public void fetchData() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }
}
