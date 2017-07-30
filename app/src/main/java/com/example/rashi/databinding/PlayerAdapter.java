package com.example.rashi.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by rashi on 29/7/17.
 */

public class PlayerAdapter extends RecyclerView.Adapter< RecyclerView.ViewHolder> {
    List<Player> playerList;
    LayoutInflater layoutInflater;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    public PlayerAdapter(List playerList){
        this.playerList = playerList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(layoutInflater==null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        if(viewType== VIEW_TYPE_LOADING){
            View view = layoutInflater.inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }else{
            ViewDataBinding playerDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_row, parent, false);
            return new PlayerViewHolder(playerDataBinding);
        }

    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof PlayerViewHolder ){
            ((PlayerViewHolder)holder).setBinding(playerList.get(position));
        }else if(holder instanceof LoadingViewHolder ){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);

        }

    }

    @Override
    public int getItemViewType(int position) {
        return playerList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    @Override
    public int getItemCount() {
        return playerList == null ? 0 : playerList.size();
    }


}
