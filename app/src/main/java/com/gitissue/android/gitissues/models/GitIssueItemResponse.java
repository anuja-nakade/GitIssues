package com.gitissue.android.gitissues.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anuja on 9/6/17.
 */

/*
url: "https://api.github.com/repos/vmg/redcarpet/issues/627",
        repository_url: "https://api.github.com/repos/vmg/redcarpet",
        labels_url: "https://api.github.com/repos/vmg/redcarpet/issues/627/labels{/name}",
        comments_url: "https://api.github.com/repos/vmg/redcarpet/issues/627/comments",
        events_url: "https://api.github.com/repos/vmg/redcarpet/issues/627/events",
        html_url: "https://github.com/vmg/redcarpet/issues/627",
        id: 251414504,
        number: 627,
        title: "Hard wrap does not work inside indented lists",
*/

public class GitIssueItemResponse implements Parcelable {

    @SerializedName("url")
    String url;

    @SerializedName("repository_url")
    String repoUrl;

    @SerializedName("comments_url")
    String commentsUrl;

    @SerializedName("id")
    Integer id;

    @SerializedName("number")
    Integer number;

    @SerializedName("title")
    String title;

    protected GitIssueItemResponse(Parcel in) {
        url = in.readString();
        repoUrl = in.readString();
        commentsUrl = in.readString();
        title = in.readString();
    }

    public static final Creator<GitIssueItemResponse> CREATOR = new Creator<GitIssueItemResponse>() {
        @Override
        public GitIssueItemResponse createFromParcel(Parcel in) {
            return new GitIssueItemResponse(in);
        }

        @Override
        public GitIssueItemResponse[] newArray(int size) {
            return new GitIssueItemResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
        parcel.writeString(repoUrl);
        parcel.writeString(commentsUrl);
        parcel.writeString(title);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
