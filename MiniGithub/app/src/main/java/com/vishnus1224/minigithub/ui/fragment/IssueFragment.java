package com.vishnus1224.minigithub.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vishnus1224.minigithub.R;
import com.vishnus1224.minigithub.listener.NavigationListener;
import com.vishnus1224.minigithub.model.Issue;
import com.vishnus1224.minigithub.ui.adapter.IssueListAdapter;
import com.vishnus1224.minigithub.ui.presenter.IssuePresenter;
import com.vishnus1224.minigithub.ui.view.IssueView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vishnu on 2/6/2016.
 */
public class IssueFragment extends BaseFragment implements AdapterView.OnItemClickListener, IssueView {

    private ListView issueListView;
    private IssueListAdapter issueListAdapter;

    private ProgressBar progressBar;

    private TextView noContentTextView;

    private View listViewFooter;

    private Button loadMoreButton;

    private ProgressBar footerProgressBar;

    private List<Issue> issueList = new ArrayList<>();

    private IssuePresenter issuePresenter = new IssuePresenter();

    private NavigationListener navigationListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_issue, container, false);

        setupViews(view);

        setListViewAdapter();

        setListItemClickListener();

        setupListViewFooter();

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{

            navigationListener = (NavigationListener) activity;

        }catch (ClassCastException e){

            throw new ClassCastException(activity.toString() + " must implement Navigation Listener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        issuePresenter.init(this);

        issuePresenter.setIssueList(issueList);

    }

    @Override
    public void onResume() {
        super.onResume();

        issuePresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();

        issuePresenter.pause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        issuePresenter.destroy();

    }

    private void setupViews(View view) {

        issueListView = (ListView) view.findViewById(R.id.issueListView);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        noContentTextView = (TextView) view.findViewById(R.id.noContentTextView);
    }

    private void setListViewAdapter() {

        issueListAdapter = new IssueListAdapter(LayoutInflater.from(getActivity()), issueList);

        issueListView.setAdapter(issueListAdapter);
    }

    private void setListItemClickListener() {

        issueListView.setOnItemClickListener(this);

    }

    private void setupListViewFooter() {

        listViewFooter = View.inflate(getActivity(), R.layout.footer_load_more, null);

        loadMoreButton = (Button) listViewFooter.findViewById(R.id.buttonLoadMore);

        footerProgressBar = (ProgressBar) listViewFooter.findViewById(R.id.progressBar);

        loadMoreButton.setOnClickListener(loadMoreClickListener);

    }

    @Override
    public void fetchData(String searchKeyword) {

    }

    private final View.OnClickListener loadMoreClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void showIssues() {

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
