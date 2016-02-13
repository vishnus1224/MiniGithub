package com.vishnus1224.minigithub.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vishnus1224.minigithub.R;
import com.vishnus1224.minigithub.model.Repository;
import com.vishnus1224.minigithub.utility.Utils;

public class RepositoryDetailActivity extends AppCompatActivity {

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_detail);

        Bundle extra = getIntent().getExtras();

        getRepositoryFromBundle(extra);
    }

    private void getRepositoryFromBundle(Bundle extra) {

        if(extra != null && extra.containsKey(Utils.KEY_REPOSITORY)){

            repository = (Repository) extra.get(Utils.KEY_REPOSITORY);

        }

    }

}
