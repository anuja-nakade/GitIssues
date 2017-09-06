package com.gitissue.android.gitissues.issues;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.gitissue.android.gitissues.R;
import com.gitissue.android.gitissues.models.GitIssueItemResponse;

import java.util.ArrayList;

/**
 * Created by Anuja on 9/6/17.
 */

public class GitIssuesAdapter extends RecyclerView.Adapter<GitIssuesAdapter.ViewHolder> {
    private final AdapterView.OnItemClickListener listener;
    private ArrayList<GitIssueItemResponse> data;
    private Context context;

    public GitIssuesAdapter(Context context, ArrayList<GitIssueItemResponse> data, AdapterView.OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
    }


    @Override
    public GitIssuesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.issue_item, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(GitIssuesAdapter.ViewHolder holder, int position) {
        //  holder.click(data.get(position), listener);
        holder.isNo.setText(data.get(position).getNumber().toString());
        holder.isUrl.setText(data.get(position).getUrl());
        holder.isTitle.setText(data.get(position).getTitle());
        holder.isId.setText(data.get(position).getId().toString());


    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    /* public interface OnItemClickListener {
         void onClick(CityListData Item);
     }
 */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView isId, isNo, isTitle, isUrl;

        public ViewHolder(View itemView) {
            super(itemView);
            isId = (TextView) itemView.findViewById(R.id.issueId);
            isTitle = (TextView) itemView.findViewById(R.id.issueTitle);
            isUrl = (TextView) itemView.findViewById(R.id.gitUrl);
            isNo = (TextView) itemView.findViewById(R.id.issueNo);


        }


        public void click(final GitIssueItemResponse cityListData, final AdapterView.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //listener.onClick(cityListData);
                }
            });
        }
    }


}