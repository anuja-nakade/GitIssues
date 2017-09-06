package com.gitissue.android.gitissues;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gitissue.android.gitissues.networking.NetworkModule;

import java.io.File;

/**
 * Created by Anuja on 9/6/17.
 */
public class BaseApp  extends AppCompatActivity {
    GitComponent mGitComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGitComponent = DaggerGitComponent.builder().networkModule(new NetworkModule()).build();

    }

    public GitComponent getGitComponent() {
        return mGitComponent;
    }
}