package com.yoelrp.karatbarsvbc.News;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.yoelrp.karatbarsvbc.R;


import java.util.ArrayList;

/**
 * Created by YoelRP on 08/01/2019.
 */

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private Context mContext;
    private ArrayList<ExampleItem> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }


    public ExampleAdapter(Context context, ArrayList<ExampleItem> exampleList){
        mContext=context;
        mExampleList=exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.item,parent,false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        //Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_recycler_item_show);
        //holder.itemView.startAnimation(animation);

        ExampleItem currentItem=mExampleList.get(position);


        String creatorName= currentItem.getmCreator();
        String likeCount= currentItem.getmLikes();

        holder.mTextViewCreator.setText(creatorName);
        holder.mTextViewLikes.setText("PubDate:" +likeCount);


    }
    @Override
    public void onViewAttachedToWindow(ExampleViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        animateCircularReveal(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextViewCreator;
        public TextView mTextViewLikes;


        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView= (ImageView) itemView.findViewById(R.id.pic);
            mTextViewCreator=(TextView) itemView.findViewById(R.id.autor);
            mTextViewLikes= (TextView) itemView.findViewById(R.id.titulo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){

                        int position=getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }

                    }
                }
            });

        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void animateCircularReveal(View view){
        int centerX=0;
        int centerY=0;
        int starRadius=0;
        int endRadius=Math.max(view.getHeight(),view.getWidth());
        Animator animation= ViewAnimationUtils.createCircularReveal(view,centerX,centerY,starRadius,endRadius);
        view.setVisibility(View.VISIBLE);
        animation.start();
    }

}
