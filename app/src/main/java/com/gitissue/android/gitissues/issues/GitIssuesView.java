package com.gitissue.android.gitissues.issues;

import com.gitissue.android.gitissues.models.GitIssueItemResponse;

import java.util.ArrayList;

/**
 * Created by Anuja on 9/6/17.
 */

public interface GitIssuesView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getIssuesListSuccess(ArrayList<GitIssueItemResponse> cityListResponse);
}
