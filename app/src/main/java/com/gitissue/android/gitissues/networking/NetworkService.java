package com.gitissue.android.gitissues.networking;

import com.gitissue.android.gitissues.models.GitIssueItemResponse;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Anuja on 9/6/17.
 */

public interface NetworkService {
    //vmg/redcarpet
    @GET("{repoName}/{repoUser}/issues")
    Observable<ArrayList<GitIssueItemResponse>> getIssues(@Path("repoName") String repoName, @Path("repoUser") String repoUser);

}
