package com.gitissue.android.gitissues;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gitissue.android.gitissues.issues.GitIssuesAdapter;
import com.gitissue.android.gitissues.issues.GitIssuesPresenter;
import com.gitissue.android.gitissues.issues.GitIssuesView;
import com.gitissue.android.gitissues.models.GitIssueItemResponse;
import com.gitissue.android.gitissues.networking.NetworkService;
import com.gitissue.android.gitissues.networking.Service;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class MainActivity extends BaseApp implements GitIssuesView {

    private RecyclerView list;
    private Button getData;
    private EditText getRepo;
    @Inject
    public Service service;

    private Subscription subscription;

    @Inject
    public NetworkService mNetworkService;
    GitIssuesPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getGitComponent().inject(this);
        initViews();

        presenter = new GitIssuesPresenter(service, this);

        subscription = RxTextView.textChanges(getRepo).debounce(100, TimeUnit.MILLISECONDS).flatMap(new Func1<CharSequence, Observable<ArrayList<GitIssueItemResponse>>>() {
            @Override
            public Observable<ArrayList<GitIssueItemResponse>> call(CharSequence charSequence) {
                String[] separated = String.valueOf(charSequence).split("/");
                if (separated.length == 2 && separated[0] != null && separated[1] != null)
                    return mNetworkService.getIssues(separated[0], separated[1]);

                return null;
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ArrayList<GitIssueItemResponse>>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "Invalid Repo", Toast.LENGTH_LONG);
                    }


                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "Invalid Repo", Toast.LENGTH_LONG);
                    }

                    @Override
                    public void onNext(ArrayList<GitIssueItemResponse> gitIssueItemResponses) {
                        getData.setVisibility(View.GONE);
                        getRepo.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                        removeWait();
                        GitIssuesAdapter adapter = new GitIssuesAdapter(getApplicationContext(), gitIssueItemResponses, null);
                        list.setAdapter(adapter);
                    }
                });

    }

    private void initViews() {
        getData = (Button) findViewById(R.id.fetchData);
        getRepo = (EditText) findViewById(R.id.gitRepo);
        list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));

    }

    private boolean verifyRepo(CharSequence text) {
        String[] separated = text.toString().split("/");
        if (separated.length == 2 && separated[0] != null && separated[1] != null)
            return true;
        return false;
    }

    @Override
    public void showWait() {

    }

    @Override
    public void removeWait() {

    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getIssuesListSuccess(ArrayList<GitIssueItemResponse> cityListResponse) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        subscription.unsubscribe();
    }
}
