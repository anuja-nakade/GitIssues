package com.gitissue.android.gitissues;

import com.gitissue.android.gitissues.issues.GetIssuesActivity;
import com.gitissue.android.gitissues.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Anuja on 9/6/17.
 */

@Singleton
@Component(modules = {NetworkModule.class,})
public interface GitComponent {

    void inject(GetIssuesActivity issuesActivity);

    void inject(MainActivity mainActivity);

}
