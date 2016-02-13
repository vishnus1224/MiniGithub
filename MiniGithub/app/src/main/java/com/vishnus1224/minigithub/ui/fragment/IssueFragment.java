package com.vishnus1224.minigithub.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vishnus1224.minigithub.R;
import com.vishnus1224.minigithub.listener.ImageLoaderListener;
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
public class IssueFragment extends BaseFragment implements AdapterView.OnItemClickListener, IssueView, ImageLoaderListener {

    private static final int IMAGE_WIDTH = 100;
    private static final int IMAGE_HEIGHT = 100;

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

        issueListAdapter = new IssueListAdapter(LayoutInflater.from(getActivity()), issueList, this);

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

        issuePresenter.searchIssues(searchKeyword);

    }

    private final View.OnClickListener loadMoreClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            issuePresenter.loadMoreIssues();

        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void showIssues() {

        issueListAdapter.notifyDataSetChanged();

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
        if(issueListView.getFooterViewsCount() == 0) {
            issueListView.addFooterView(listViewFooter);
        }
    }

    @Override
    public void removeFooterView() {

        if(listViewFooter != null && issueListView.getFooterViewsCount() > 0){

            issueListView.removeFooterView(listViewFooter);

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
    public void loadImage(ImageView imageView, String imageURL) {

        Picasso.with(getActivity()).load(imageURL).resize(IMAGE_WIDTH, IMAGE_HEIGHT).into(imageView);

    }
}
