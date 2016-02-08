package com.vishnus1224.minigithub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnus1224.minigithub.R;
import com.vishnus1224.minigithub.model.Repository;
import com.vishnus1224.minigithub.ui.adapter.RepositoryListAdapter;
import com.vishnus1224.minigithub.ui.presenter.RepositoryPresenter;
import com.vishnus1224.minigithub.ui.view.RepositoryView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vishnu on 2/6/2016.
 */
public class RepositoryFragment extends BaseFragment implements RepositoryView {

    private ListView repositoryListView;
    private RepositoryListAdapter repositoryListAdapter;

    private ProgressBar progressBar;

    private TextView noContentTextView;

    private View listViewFooter;

    private Button loadMoreButton;

    private ProgressBar footerProgressBar;

    private RepositoryPresenter presenter = new RepositoryPresenter();

    private List<Repository> repositoryList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_repository, container, false);

        setupViews(view);

        setListViewAdapter();

        setupListViewFooter();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //initialize the presenter by passing it the view.
        presenter.init(this);

        //pass the repository list to the presenter.
        presenter.setRepositories(repositoryList);
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


    private void setupViews(View view) {

        repositoryListView = (ListView) view.findViewById(R.id.repositoryListView);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        noContentTextView = (TextView) view.findViewById(R.id.noContentTextView);
    }


    private void setListViewAdapter() {

        repositoryListAdapter = new RepositoryListAdapter(LayoutInflater.from(getActivity()), repositoryList);

        repositoryListView.setAdapter(repositoryListAdapter);
    }

    private void setupListViewFooter() {

        listViewFooter = View.inflate(getActivity(), R.layout.footer_load_more, null);

        loadMoreButton = (Button) listViewFooter.findViewById(R.id.buttonLoadMore);

        footerProgressBar = (ProgressBar) listViewFooter.findViewById(R.id.progressBar);

    }

    @Override
    public void fetchData(String searchKeyword) {

        presenter.searchRepositories(searchKeyword);

    }

    @Override
    public void showProgress() {

        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {

        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showError(String message) {

        //Show a toast with the error message if the activity is not destroyed.
        if(getActivity() != null){

            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void showRepositories(List<Repository> repositoryList) {

        //notify the adapter.
        repositoryListAdapter.notifyDataSetChanged();

        addFooterView();

    }

    @Override
    public void hideNoContentView() {

        noContentTextView.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showNoContentView() {

        noContentTextView.setVisibility(View.VISIBLE);

    }

    @Override
    public void addFooterView() {

        repositoryListView.addFooterView(listViewFooter);

    }

    @Override
    public void enableLoadMoreButton() {

    }

    @Override
    public void disableLoadMoreButton() {

    }

    @Override
    public void showFooterProgress() {

    }

    @Override
    public void hideFooterProgress() {

    }

}
