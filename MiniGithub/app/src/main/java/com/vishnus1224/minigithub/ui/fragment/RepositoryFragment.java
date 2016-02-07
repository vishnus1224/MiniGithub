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
import com.vishnus1224.minigithub.ui.presenter.RepositoryPresenter;
import com.vishnus1224.minigithub.ui.view.RepositoryView;

import java.util.List;

/**
 * Created by Vishnu on 2/6/2016.
 */
public class RepositoryFragment extends BaseFragment implements RepositoryView {

    private ListView repositoryListView;

    private ProgressBar progressBar;

    private TextView noContentTextView;

    private RepositoryPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_repository, container, false);

        setupViews(view);

        hideProgress();

        return view;
    }

    private void setupViews(View view) {

        repositoryListView = (ListView) view.findViewById(R.id.repositoryListView);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        noContentTextView = (TextView) view.findViewById(R.id.noContentTextView);
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

    @Override
    public void fetchData(String searchKeyword) {

        showProgress();

        presenter.getRepositories(searchKeyword);

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

    }

    @Override
    public void hideNoContentView() {

        noContentTextView.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showNoContentView() {

        noContentTextView.setVisibility(View.VISIBLE);

    }
}
