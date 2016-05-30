package com.rohan.poc_mvp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rohan.poc_mvp.R;
import com.rohan.poc_mvp.model.domain.Repository;
import com.rohan.poc_mvp.utils.FontUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by rohan on 27/5/16.
 */
public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {

    private List<Repository> repositories;
    private Callback callback;

    public RepositoryAdapter() {
        this.repositories = Collections.emptyList();
    }

    public RepositoryAdapter(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_repository, parent, false);
        final RepositoryViewHolder viewHolder = new RepositoryViewHolder(itemView);
        viewHolder.contentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onItemClick(viewHolder.repository);
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        Repository repository = repositories.get(position);
        Context context = holder.tvRepositoryName.getContext();
        holder.repository = repository;
        holder.tvRepositoryName.setText(repository.name);
        holder.tvRepositoryDescription.setText(repository.description);
        holder.tvWatchers.setText(
                context.getResources().getString(R.string.text_watchers, repository.watchers));
        holder.tvStars.setText(
                context.getResources().getString(R.string.text_stars, repository.stars));
        holder.tvForks.setText(
                context.getResources().getString(R.string.text_forks, repository.forks));
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public static class RepositoryViewHolder extends RecyclerView.ViewHolder {
        public View contentLayout;
        public TextView tvRepositoryName;
        public TextView tvRepositoryDescription;
        public TextView tvWatchers;
        public TextView tvStars;
        public TextView tvForks;
        public Repository repository;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            contentLayout = itemView.findViewById(R.id.container_repository);
            tvRepositoryName = (TextView) itemView.findViewById(R.id.tv_repository_name);
            tvRepositoryDescription = (TextView) itemView.findViewById(R.id.tv_repository_description);
            tvWatchers = (TextView) itemView.findViewById(R.id.tv_repository_watchers);
            tvStars = (TextView) itemView.findViewById(R.id.tv_repository_stars);
            tvForks = (TextView) itemView.findViewById(R.id.tv_repository_forks);

            FontUtils.init(contentLayout);
        }
    }

    public interface Callback {
        void onItemClick(Repository repository);
    }
}
