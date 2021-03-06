package com.vishnus1224.minigithub.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vishnus1224.minigithub.R;
import com.vishnus1224.minigithub.di.component.DaggerFragmentComponent;
import com.vishnus1224.minigithub.di.component.FragmentComponent;
import com.vishnus1224.minigithub.di.module.FragmentModule;
import com.vishnus1224.minigithub.listener.ImageLoaderListener;
import com.vishnus1224.minigithub.listener.NavigationListener;
import com.vishnus1224.minigithub.model.User;
import com.vishnus1224.minigithub.ui.adapter.UserListAdapter;
import com.vishnus1224.minigithub.ui.presenter.UserPresenter;
import com.vishnus1224.minigithub.ui.view.UserView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Vishnu on 2/6/2016.
 */
public class UserFragment extends BaseFragment implements UserView, ImageLoaderListener {
    
    private static final int IMAGE_WIDTH = 100;
    private static final int IMAGE_HEIGHT = 100;

    private ListView userListView;
    private UserListAdapter userListAdapter;

    private ProgressBar progressBar;

    private TextView noContentTextView;

    private View listViewFooter;

    private Button loadMoreButton;

    private ProgressBar footerProgressBar;

    @Inject
    UserPresenter userPresenter;

    private FragmentComponent fragmentComponent;

    private List<User> userList = new ArrayList<>();

    private NavigationListener navigationListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){

            fragmentComponent = DaggerFragmentComponent.builder()
                    .fragmentModule(new FragmentModule())
                    .netComponent(getNetComponent())
                    .build();

            fragmentComponent.inject(this);

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, container, false);

        setupViews(view);

        setListViewAdapter();

        setupListViewFooter();

        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            navigationListener = (NavigationListener) activity;
        }catch (Exception e){
            throw new ClassCastException(activity.toString() + "must implement Navigation Listener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userPresenter.init(this);

        userPresenter.setUserList(userList);
    }

    @Override
    public void onResume() {
        super.onResume();

        userPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();

        userPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        userPresenter.destroy();
    }

    private void setupViews(View view) {

        userListView = (ListView) view.findViewById(R.id.userListView);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        noContentTextView = (TextView) view.findViewById(R.id.noContentTextView);
    }

    private void setListViewAdapter() {

        userListAdapter = new UserListAdapter(LayoutInflater.from(getActivity()), userList, this);

        userListView.setAdapter(userListAdapter);

    }

    private void setupListViewFooter() {

        listViewFooter = View.inflate(getActivity(), R.layout.footer_load_more, null);

        loadMoreButton = (Button) listViewFooter.findViewById(R.id.buttonLoadMore);

        footerProgressBar = (ProgressBar) listViewFooter.findViewById(R.id.progressBar);

        loadMoreButton.setOnClickListener(loadMoreClickListener);

    }

    private final View.OnClickListener loadMoreClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            userPresenter.loadMoreUsers();

        }
    };


    @Override
    public void fetchData(String searchKeyword) {

        userPresenter.searchUsers(searchKeyword);

    }

    @Override
    public void showUsers() {

        userListAdapter.notifyDataSetChanged();

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
        if(userListView.getFooterViewsCount() == 0) {
            userListView.addFooterView(listViewFooter);
        }

    }

    @Override
    public void removeFooterView() {

        if(listViewFooter != null && userListView.getFooterViewsCount() > 0){

            userListView.removeFooterView(listViewFooter);

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
