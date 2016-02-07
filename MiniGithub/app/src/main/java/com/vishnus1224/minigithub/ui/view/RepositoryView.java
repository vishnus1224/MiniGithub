package com.vishnus1224.minigithub.ui.view;

import com.vishnus1224.minigithub.model.Repository;

import java.util.List;

/**
 * Created by Vishnu on 2/6/2016.
 */
public interface RepositoryView extends BaseView {

    //update the ui using the list of repositories provided.
    void showRepositories(List<Repository> repositoryList);

    void hideNoContentView();

    void showNoContentView();

}
