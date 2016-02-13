package com.vishnus1224.minigithub.ui.view;

/**
 * Created by Vishnu on 2/6/2016.
 */
public interface RepositoryView extends BaseView {

    //update the ui by notifying the adapter.
    void showRepositories();

    void hideNoContentView();

    void showNoContentView();

    void addFooterView();

    void removeFooterView();

    void showFooterProgress();

    void hideFooterProgress();

    void showLoadMoreButton();

    void hideLoadMoreButton();

}
