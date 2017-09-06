package com.gitissue.android.gitissues.networking;

import com.gitissue.android.gitissues.models.GitIssueItemResponse;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Anuja on 9/6/17.
 */

public class Service {
    private final NetworkService networkService;
    private String mRepoName;
    private String mRepoOwner;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getIssuesList(final GetIssueListCallback callback) {

        return networkService.getIssues(mRepoName , mRepoOwner)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends ArrayList<GitIssueItemResponse>>>() {
                    @Override
                    public Observable<? extends ArrayList<GitIssueItemResponse>> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<ArrayList<GitIssueItemResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(ArrayList<GitIssueItemResponse> issueListResponse) {
                        callback.onSuccess(issueListResponse);

                    }
                });
    }

    public void setRepoName(String repoName) {
        mRepoName = repoName;
    }

    public void setRepoOwner(String repoOwner) {
        mRepoOwner = repoOwner;
    }

    public interface GetIssueListCallback {
        void onSuccess(ArrayList<GitIssueItemResponse> gitIssueItemResponse);

        void onError(NetworkError networkError);
    }

}