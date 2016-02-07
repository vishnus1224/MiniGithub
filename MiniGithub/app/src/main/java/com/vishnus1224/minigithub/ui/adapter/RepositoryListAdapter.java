package com.vishnus1224.minigithub.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vishnus1224.minigithub.R;
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

        if(view == null){

            view = layoutInflater.inflate(R.layout.adapter_repository_list, viewGroup, false);

        }

        TextView repoLanguageTextView = (TextView) view.findViewById(R.id.adapterRepositoryLanguage);

        TextView repoNameTextView = (TextView) view.findViewById(R.id.adapterRepositoryName);

        TextView repoDescTextView = (TextView) view.findViewById(R.id.adapterRepositoryDesc);

        Repository repository = repositoryList.get(i);

        repoLanguageTextView.setText(repository.getLanguage());

        repoNameTextView.setText(repository.getName());

        repoDescTextView.setText(repository.getDescription());

        return view;
    }
}
