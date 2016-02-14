package com.vishnus1224.minigithub.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vishnus1224.minigithub.R;
import com.vishnus1224.minigithub.listener.NavigationListener;
import com.vishnus1224.minigithub.model.User;
import com.vishnus1224.minigithub.ui.presenter.UserPresenter;
import com.vishnus1224.minigithub.ui.view.UserView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vishnu on 2/6/2016.
 */
public class UserFragment extends BaseFragment implements UserView {

    private UserPresenter userPresenter = new UserPresenter();

    private List<User> userList = new ArrayList<>();

    private NavigationListener navigationListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, container, false);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            navigationListener = (NavigationListener) activity;
        }catch (Exception e){
            throw new ClassCastException(activity.toString() + "must implement Navigation Listener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userPresenter.init(this);

        userPresenter.setUserList(userList);
    }

    @Override
    public void fetchData(String searchKeyword) {

    }

    @Override
    public void showUsers() {

    }

    @Override
    public void hideNoContentView() {

    }

    @Override
    public void showNoContentView() {

    }

    @Override
    public void addFooterView() {

    }

    @Override
    public void removeFooterView() {

    }

    @Override
    public void showFooterProgress() {

    }

    @Override
    public void hideFooterProgress() {

    }

    @Override
    public void showLoadMoreButton() {

    }

    @Override
    public void hideLoadMoreButton() {

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
