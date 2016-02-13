package com.vishnus1224.minigithub.ui.view;

/**
 * Created by Vishnu on 2/13/2016.
 */
public interface IssueView extends BaseView {

    void showIssues();

    void hideNoContentView();

    void showNoContentView();

    void addFooterView();

    void removeFooterView();

    void showFooterProgress();

    void hideFooterProgress();

    void showLoadMoreButton();

    void hideLoadMoreButton();
}
