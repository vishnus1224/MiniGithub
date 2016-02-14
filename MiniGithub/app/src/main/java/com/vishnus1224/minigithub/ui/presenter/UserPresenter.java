package com.vishnus1224.minigithub.ui.presenter;

import com.vishnus1224.minigithub.interactor.UserInteractor;
import com.vishnus1224.minigithub.interactor.UserInteractorImpl;
import com.vishnus1224.minigithub.listener.FetchUsersListener;
import com.vishnus1224.minigithub.listener.LoadMoreUsersListener;
import com.vishnus1224.minigithub.model.User;
import com.vishnus1224.minigithub.ui.view.BaseView;
import com.vishnus1224.minigithub.ui.view.UserView;
import com.vishnus1224.minigithub.utility.Utils;

import java.util.List;

/**
 * Created by Vishnu on 2/14/2016.
 */
public class UserPresenter implements Presenter {

    private UserView userView;

    private List<User> userList;

    //flag to indicate that search is currently going on.
    private boolean searchInProgress;

    //flag to check if load more request is in progress.
    private boolean loadingMore;

    //The keyword that was searched before the current one. Search will not take place if current and this keyword is same.
    //This will be used to know if the current keyword and the last one was same.
    private String lastSearchKeyword = "";

    //Name of the user being searched.
    private String currentUserName;

    private UserInteractor userInteractor = new UserInteractorImpl();

    //listener for getting callback when user fetch is completed.
    private FetchUsersListener fetchUsersListener;

    //listener for getting callback when load more users call is completed.
    private LoadMoreUsersListener loadMoreUsersListener;

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public void init(BaseView view) {

        if(!(view instanceof UserView)){
            throw new RuntimeException("View must be of type UserView");
        }

        this.userView = (UserView) view;

        fetchUsersListener = new FetchUsersListener(this);

        loadMoreUsersListener = new LoadMoreUsersListener(this);

    }

    @Override
    public void resume() {

        //hide the no content view if user list is not empty.
        if(!userList.isEmpty()){

            userView.hideNoContentView();

            userView.addFooterView();

        }

        if(searchInProgress){

            userView.hideNoContentView();

            userView.showProgress();

        }

        if(loadingMore){

            userView.showFooterProgress();

            userView.hideLoadMoreButton();

        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

        fetchUsersListener = null;

        loadMoreUsersListener = null;
    }

    //Search for the specified user name.
    public void searchUsers(String username){

        //if search is in progress, then notify view and return.
        if(searchInProgress || loadingMore){

            userView.showError("Search is in progress");

            return;
        }

        //if the last searched keyword and the new one are same.
        if(Utils.areStringsEqual(lastSearchKeyword, username)){

            if(userList.isEmpty()){

                userView.showNoContentView();

            }else{

                //if user list is not empty, then do not search.

                userView.showError("Results are shown for the current search query");

                userView.addFooterView();

            }

            return;
        }



        //set the current user name.
        currentUserName = username;

        searchInProgress = true;

        userView.hideNoContentView();

        userView.showProgress();

        userInteractor.fetchUsers(username, fetchUsersListener);
    }

    //load more users for the current search keyword.
    public void loadMoreUsers(){

        if(searchInProgress){

            userView.showError("Search is in progress");

            return;

        }

        userView.hideLoadMoreButton();

        userView.showFooterProgress();

        loadingMore = true;

        userInteractor.loadMoreUsers(lastSearchKeyword, loadMoreUsersListener);
    }

    //Fetched users successfully.
    public void userSearchSuccess(List<User> users){

        searchInProgress = false;

        lastSearchKeyword = currentUserName;

        userView.hideProgress();

        userList.clear();

        if(users.isEmpty()){

            userView.showUsers();

            userView.removeFooterView();

            //tell the view that there are no more results.
            userView.showNoContentView();

        }else {

            userList.addAll(users);

            //hide the no content text view.
            userView.hideNoContentView();

            //show the new users in the view.
            userView.showUsers();

            userView.addFooterView();

        }
    }

    //Failed to fetch users.
    public void userSearchFailed(String message){

        searchInProgress = false;

        userView.hideProgress();

        userView.showError(message);

        if(userList.isEmpty()) {
            userView.showNoContentView();
        }
    }

    //successfully loaded more users for the current search term.
    public void loadMoreUsersSuccess(List<User> users){

        loadingMore = false;

        if(users.isEmpty()){

            userView.showError("No more users found");

        }else{

            userList.addAll(users);

            userView.showUsers();

        }

        userView.hideFooterProgress();

        userView.showLoadMoreButton();
    }

    //failed to load more users for the current keyword.
    public void loadMoreUsersFailed(String message){

        loadingMore = false;

        userView.showError(message);

        userView.hideFooterProgress();

        userView.showLoadMoreButton();

    }
}
