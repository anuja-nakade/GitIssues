package com.gitissue.android.gitissues.issues;

import com.gitissue.android.gitissues.models.GitIssueItemResponse;
import com.gitissue.android.gitissues.networking.NetworkError;
import com.gitissue.android.gitissues.networking.Service;

import java.util.ArrayList;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Anuja on 9/6/17.
 */

public class GitIssuesPresenter {

    private final Service service;
    private final GitIssuesView view;
    private CompositeSubscription subscriptions;
    private String mRepoName;
    private String mRepoOwner;

    public GitIssuesPresenter(Service service, GitIssuesView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getIssuesList() {
        view.showWait();

        service.setRepoName(mRepoName);
        service.setRepoOwner(mRepoOwner);
        Subscription subscription = service.getIssuesList(new Service.GetIssueListCallback() {
            @Override
            public void onSuccess(ArrayList<GitIssueItemResponse> gitIssueItemResponse) {
                view.removeWait();
                view.getIssuesListSuccess(gitIssueItemResponse);
            }


            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }

    public void setRepoName(String repoName) {
        mRepoName = repoName;
    }

    public void setRepoOwner(String repoOwner) {
        mRepoOwner = repoOwner;
    }
}
