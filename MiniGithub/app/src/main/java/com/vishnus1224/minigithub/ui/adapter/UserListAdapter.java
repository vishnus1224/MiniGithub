package com.vishnus1224.minigithub.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vishnus1224.minigithub.R;
import com.vishnus1224.minigithub.listener.ImageLoaderListener;
import com.vishnus1224.minigithub.model.User;

import java.util.List;

/**
 * Created by Vishnu on 2/14/2016.
 */
public class UserListAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<User> userList;
    private ImageLoaderListener imageLoaderListener;

    public UserListAdapter(LayoutInflater layoutInflater, List<User> userList, ImageLoaderListener imageLoaderListener) {
        this.layoutInflater = layoutInflater;
        this.userList = userList;
        this.imageLoaderListener = imageLoaderListener;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){

            view = layoutInflater.inflate(R.layout.adapter_user_list, viewGroup, false);

        }

        TextView usernameTextView =(TextView) view.findViewById(R.id.adapterUserName);

        TextView userScoreTextView = (TextView) view.findViewById(R.id.adapterUserScore);

        ImageView userAvatarImageView = (ImageView) view.findViewById(R.id.adapterUserAvatar);

        User user = userList.get(i);

        usernameTextView.setText(user.getUsername());

        userScoreTextView.setText("" + user.getScore());

        if(imageLoaderListener != null){

            imageLoaderListener.loadImage(userAvatarImageView, user.getAvatarURL());

        }

        return view;
    }
}
