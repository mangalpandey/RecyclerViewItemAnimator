package com.example.mangal.recyclerviewitemanimator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class AnimatedAdapter extends RecyclerView.Adapter {
    private Activity activity;
    int lastAnimatedPosition = -1;
    public AnimatedAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.row_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        lastAnimatedPosition = animateItem((View)viewHolder.imageView.getParent(), position, lastAnimatedPosition, 500);
    }

    @Override
    public int getItemCount() {
        return 100;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    private  int animateItem(View view, int position, int lastAnimatedPosition, int height) {
        if(position==lastAnimatedPosition){
            lastAnimatedPosition = position - 1;
        }
        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator objectAnimatorAlpha = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 0.2f, 1.0f);
            ObjectAnimator objectAnimatorTranslate = ObjectAnimator.ofFloat(view, "translationY", height, 0f);
            animatorSet.playTogether(objectAnimatorAlpha, objectAnimatorTranslate);
            animatorSet.setDuration(800);
            animatorSet.setInterpolator(new LinearInterpolator());
            animatorSet.start();
        }
        return lastAnimatedPosition;
    }
}
