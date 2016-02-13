package com.vishnus1224.minigithub.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.vishnus1224.minigithub.R;
import com.vishnus1224.minigithub.model.Repository;
import com.vishnus1224.minigithub.utility.Utils;

public class RepositoryDetailActivity extends AppCompatActivity {

    private TextView repositoryTitleTextView;
    private TextView repositoryDescTextView;
    private TextView repositoryLanguageTextView;
    private TextView repositoryUpdateDateTextView;
    private TextView repositoryWatcherCountTextView;
    private TextView repositoryOpenIssueCountTextView;
    private TextView repositoryHtmlURLTextView;

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_detail);

        setupViews();

        updateViews();

    }


    private void setupViews() {

        repositoryTitleTextView = (TextView) findViewById(R.id.repoDetailTitle);
        repositoryDescTextView = (TextView) findViewById(R.id.repoDetailDescription);
        repositoryLanguageTextView = (TextView) findViewById(R.id.repoDetailLanguage);
        repositoryUpdateDateTextView = (TextView) findViewById(R.id.repoDetailUpdateDate);
        repositoryWatcherCountTextView = (TextView) findViewById(R.id.repoDetailWatcherCount);
        repositoryOpenIssueCountTextView = (TextView) findViewById(R.id.repoDetailOpenIssueCount);
        repositoryHtmlURLTextView = (TextView) findViewById(R.id.repoDetailHtmlURL);

    }


    private void updateViews() {

        Bundle extra = getIntent().getExtras();

        if(extra != null && extra.containsKey(Utils.KEY_REPOSITORY)){

            repository = (Repository) extra.get(Utils.KEY_REPOSITORY);

            if(repository != null) {
                setDataToViews(repository);
            }

        }

    }

    private void setDataToViews(Repository repository) {

        repositoryTitleTextView.setText(repository.getName());

        repositoryDescTextView.setText(repository.getDescription());

        repositoryLanguageTextView.setText(repository.getLanguage());

        repositoryUpdateDateTextView.setText(repository.getUpdatedAt());

        repositoryWatcherCountTextView.setText("" + repository.getWatchersCount());

        repositoryOpenIssueCountTextView.setText("" + repository.getOpenIssuesCount());

        repositoryHtmlURLTextView.setText(repository.getHtmlURL());

    }

}
