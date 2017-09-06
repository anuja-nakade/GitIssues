package com.gitissue.android.gitissues.issues;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.gitissue.android.gitissues.BaseApp;
import com.gitissue.android.gitissues.R;
import com.gitissue.android.gitissues.models.GitIssueItemResponse;
import com.gitissue.android.gitissues.networking.Service;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Anuja on 9/6/17.
 */

public class GetIssuesActivity extends BaseApp implements GitIssuesView {

    private RecyclerView list;
    @Inject
    public Service service;
    ProgressBar progressBar;
    private String repoName;
    private String repDetails;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        repoName = intent.getStringExtra("repoUser");
        repDetails = intent.getStringExtra("repoDirect");
        getGitComponent().inject(this);

        renderView();
        init();

        GitIssuesPresenter presenter = new GitIssuesPresenter(service, this);

        presenter.setRepoOwner(repDetails);
        presenter.setRepoName(repoName);

        presenter.getIssuesList();
    }

    private String getRepoName(){
        return repoName;
    }
        public void renderView() {
        setContentView(R.layout.activity_gitdata);
        list = (RecyclerView) findViewById(R.id.list);
        progressBar = (ProgressBar) findViewById(R.id.progress);
    }

    public void init() {
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }


    @Override
    public void getIssuesListSuccess(ArrayList<GitIssueItemResponse> gitIssueItemResponse) {

        GitIssuesAdapter adapter = new GitIssuesAdapter(getApplicationContext(), gitIssueItemResponse, null);
              /*  new GitIssuesAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(GitIssueItemResponse Item) {
                     *//*   Toast.makeText(getApplicationContext(), Item.getName(),
                                Toast.LENGTH_LONG).show();*//*
                    }
                });*/

        // list.setAdapter(adapter);

    }
}