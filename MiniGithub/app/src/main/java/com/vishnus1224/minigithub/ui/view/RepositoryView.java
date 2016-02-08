package com.vishnus1224.minigithub.ui.view;

import com.vishnus1224.minigithub.model.Repository;

import java.util.List;

/**
 * Created by Vishnu on 2/6/2016.
 */
public interface RepositoryView extends BaseView {

    //update the ui by notifying the adapter.
    void showRepositories();

    void hideNoContentView();

    void showNoContentView();

    void addFooterView();

    void enableLoadMoreButton();

    void disableLoadMoreButton();

    void showFooterProgress();

    void hideFooterProgress();

    void showLoadMoreButton();

    void hideLoadMoreButton();

}
