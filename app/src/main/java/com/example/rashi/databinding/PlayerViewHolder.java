package com.example.rashi.databinding;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by rashi on 29/7/17.
 */

public class PlayerViewHolder extends RecyclerView.ViewHolder {
    ViewDataBinding playerDataBinding;
    public PlayerViewHolder(ViewDataBinding playerDataBinding) {
        super(playerDataBinding.getRoot());
        this.playerDataBinding = playerDataBinding;
    }

    public void  setBinding(Player player){
        playerDataBinding.setVariable(com.example.rashi.databinding.BR.player,player);
        playerDataBinding.executePendingBindings();
    }


}
