package com.vishnus1224.minigithub.ui.presenter;

import com.vishnus1224.minigithub.model.Issue;
import com.vishnus1224.minigithub.ui.view.BaseView;
import com.vishnus1224.minigithub.ui.view.IssueView;

import java.util.List;

/**
 * Created by Vishnu on 2/13/2016.
 */
public class IssuePresenter implements Presenter {

    private IssueView issueView;

    private List<Issue> issueList;

    public void setIssueList(List<Issue> issueList) {
        this.issueList = issueList;
    }

    @Override
    public void init(BaseView view) {

        if(!(view instanceof IssueView)){
            throw new RuntimeException("View must be of type IssueView");
        }

        issueView = (IssueView) view;

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
