package com.vishnus1224.minigithub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private RepositoryPresenter presenter;

    private List<Repository> repositoryList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_repository, container, false);

        setupViews(view);

        setListViewAdapter();

        hideProgress();

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


    private void setupViews(View view) {

        repositoryListView = (ListView) view.findViewById(R.id.repositoryListView);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        noContentTextView = (TextView) view.findViewById(R.id.noContentTextView);
    }


    private void setListViewAdapter() {

        repositoryListAdapter = new RepositoryListAdapter(LayoutInflater.from(getActivity()), repositoryList);

        repositoryListView.setAdapter(repositoryListAdapter);
    }

    @Override
    public void fetchData(String searchKeyword) {

        hideNoContentView();

        //fetch data only if the list is empty.
        if(repositoryList.isEmpty()) {

            showProgress();

            presenter.getRepositories(searchKeyword);

        }

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

            //check if repository list is empty and show the no content view.
            noMoreRepositories();

        }

    }

    @Override
    public void showRepositories(List<Repository> repositoryList) {

        //add new repositories to the list.
        this.repositoryList.addAll(repositoryList);

        //notify the adapter.
        repositoryListAdapter.notifyDataSetChanged();

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
    public void noMoreRepositories() {

        //if the repo list is empty then show the no content view.
        if(repositoryList.isEmpty()){

            showNoContentView();

        }

    }
}
