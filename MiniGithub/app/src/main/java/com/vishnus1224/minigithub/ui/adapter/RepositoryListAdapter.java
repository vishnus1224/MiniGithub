package com.vishnus1224.minigithub.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.vishnus1224.minigithub.model.Repository;

import java.util.List;

/**
 * Created by Vishnu on 2/7/2016.
 */
public class RepositoryListAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<Repository> repositoryList;

    public RepositoryListAdapter(LayoutInflater layoutInflater, List<Repository> repositoryList){
        this.layoutInflater = layoutInflater;
        this.repositoryList = repositoryList;
    }

    @Override
    public int getCount() {
        return repositoryList.size();
    }

    @Override
    public Object getItem(int i) {
        return repositoryList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
