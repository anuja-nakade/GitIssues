package com.gitissue.android.gitissues;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gitissue.android.gitissues.issues.GetIssuesActivity;

public class MainActivity extends AppCompatActivity {

    private Button getData;
    private EditText getRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        getData = (Button) findViewById(R.id.fetchData);
        getRepo = (EditText) findViewById(R.id.gitRepo);

        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getData.getText() != null && verifyRepo(getData.getText() + "")) {
                    {
                        Intent gitIntent = new Intent(MainActivity.this, GetIssuesActivity.class);
                        String[] separated = getRepo.getText().toString().split("/");
                        gitIntent.putExtra("repoUser", separated[0]);
                        gitIntent.putExtra("repoDirect", separated[1]);
                        startActivity(gitIntent);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a valid repo", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private boolean verifyRepo(CharSequence text) {
        String[] separated = text.toString().split("/");
        if (separated.length == 2 && separated[0] != null && separated[1] != null)
            return true;
        return false;
    }
}
