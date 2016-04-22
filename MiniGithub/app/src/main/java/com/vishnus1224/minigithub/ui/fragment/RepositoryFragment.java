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
import android.widget.Toast;

import com.vishnus1224.minigithub.R;
import com.vishnus1224.minigithub.di.component.DaggerFragmentComponent;
import com.vishnus1224.minigithub.di.component.FragmentComponent;
import com.vishnus1224.minigithub.listener.NavigationListener;
import com.vishnus1224.minigithub.model.Repository;
import com.vishnus1224.minigithub.ui.adapter.RepositoryListAdapter;
import com.vishnus1224.minigithub.ui.presenter.RepositoryPresenter;
import com.vishnus1224.minigithub.ui.view.RepositoryView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Vishnu on 2/6/2016.
 */
public class RepositoryFragment extends BaseFragment implements RepositoryView, AdapterView.OnItemClickListener {

    private ListView repositoryListView;
    private RepositoryListAdapter repositoryListAdapter;

    private ProgressBar progressBar;

    private TextView noContentTextView;

    private View listViewFooter;

    private Button loadMoreButton;

    private ProgressBar footerProgressBar;

    @Inject
    RepositoryPresenter presenter;

    private List<Repository> repositoryList = new ArrayList<>();

    private NavigationListener navigationListener;

    private FragmentComponent fragmentComponent;

    public RepositoryFragment(){
        setRetainInstance(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //perform injections only for the first time the fragment is created
        if(savedInstanceState == null) {

            fragmentComponent = DaggerFragmentComponent.builder().build();

            fragmentComponent.inject(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_repository, container, false);

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



    private void setListItemClickListener() {

        repositoryListView.setOnItemClickListener(this);

    }


    private void setupListViewFooter() {

        listViewFooter = View.inflate(getActivity(), R.layout.footer_load_more, null);

        loadMoreButton = (Button) listViewFooter.findViewById(R.id.buttonLoadMore);

        footerProgressBar = (ProgressBar) listViewFooter.findViewById(R.id.progressBar);

        loadMoreButton.setOnClickListener(loadMoreClickListener);

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
    public void showRepositories() {

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
    public void addFooterView() {

        //Add footer view if one does not already exist.
        if(repositoryListView.getFooterViewsCount() == 0) {
            repositoryListView.addFooterView(listViewFooter);
        }

    }

    @Override
    public void removeFooterView() {

        if(listViewFooter != null && repositoryListView.getFooterViewsCount() > 0){

            repositoryListView.removeFooterView(listViewFooter);

        }

    }

    @Override
    public void showFooterProgress() {

        footerProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideFooterProgress() {

        footerProgressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showLoadMoreButton() {

        loadMoreButton.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoadMoreButton() {

        loadMoreButton.setVisibility(View.INVISIBLE);

    }

    private View.OnClickListener loadMoreClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            presenter.loadMoreRepositories();

        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if(navigationListener != null){

            navigationListener.navigateToRepositoryDetail(repositoryList.get(i));

        }

    }
}
