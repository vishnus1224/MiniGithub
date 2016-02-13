package com.vishnus1224.minigithub.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vishnus1224.minigithub.R;
import com.vishnus1224.minigithub.listener.ImageLoaderListener;
import com.vishnus1224.minigithub.model.Issue;
import com.vishnus1224.minigithub.utility.Utils;

import java.util.List;

/**
 * Created by Vishnu on 2/13/2016.
 */
public class IssueListAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<Issue> issueList;
    private ImageLoaderListener imageLoaderListener;

    public IssueListAdapter(LayoutInflater layoutInflater, List<Issue> issueList, ImageLoaderListener imageLoaderListener){

        this.layoutInflater = layoutInflater;
        this.issueList = issueList;
        this.imageLoaderListener = imageLoaderListener;
    }

    @Override
    public int getCount() {
        return issueList.size();
    }

    @Override
    public Object getItem(int i) {
        return issueList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){

            view = layoutInflater.inflate(R.layout.adapter_issue_list, viewGroup, false);

        }

        TextView issueTitleTextView = (TextView) view.findViewById(R.id.adapterIssueTitle);

        ImageView reporterAvatarImageView = (ImageView) view.findViewById(R.id.adapterIssueReporterAvatar);

        TextView issueReporterTextView = (TextView) view.findViewById(R.id.adapterIssueReporter);

        TextView issueStateTextView = (TextView) view.findViewById(R.id.adapterIssueState);

        TextView issueCreatedDateTextView = (TextView) view.findViewById(R.id.adapterIssueCreatedDate);

        Issue issue = issueList.get(i);

        issueTitleTextView.setText(issue.getTitle());

        issueReporterTextView.setText(issue.getReporter().getUsername());

        issueStateTextView.setText(issue.getState());

        if(imageLoaderListener != null){

            imageLoaderListener.loadImage(reporterAvatarImageView, issue.getReporter().getAvatarURL());

        }

        issueCreatedDateTextView.setText(Utils.formatDate(issue.getCreatedAt(), Utils.githubDateFormat, Utils.appDateFormat));

        return view;
    }

}
