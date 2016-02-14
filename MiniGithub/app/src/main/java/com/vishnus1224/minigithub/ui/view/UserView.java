package com.vishnus1224.minigithub.ui.view;

/**
 * Created by Vishnu on 2/14/2016.
 */
public interface UserView extends BaseView {

    void showUsers();

    void hideNoContentView();

    void showNoContentView();

    void addFooterView();

    void removeFooterView();

    void showFooterProgress();

    void hideFooterProgress();

    void showLoadMoreButton();

    void hideLoadMoreButton();
}
