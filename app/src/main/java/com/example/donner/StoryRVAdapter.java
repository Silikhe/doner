package com.example.donner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StoryRVAdapter extends RecyclerView.Adapter<StoryRVAdapter.MyViewHolder> {
    private ArrayList<StoryModel> storyModels;
    private Context context;
    int lastPos = -1;
    private StoryClickInterface storyClickInterface;

    public StoryRVAdapter(ArrayList<StoryModel> storyModels, Context context, StoryClickInterface storyClickInterface) {
        this.storyModels = storyModels;
        this.context = context;
        this.storyClickInterface = storyClickInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_story_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        StoryModel storyModel = storyModels.get(position);
        holder.donName.setText(storyModel.getStoryTitle());
        holder.donContent.setText(storyModel.getStoryDesc());
        Picasso.get().load(storyModel.getStoryImage()).into(holder.donImg);
        setAnimation(holder.itemView, position);

        Toast.makeText(context, ""+Picasso.get().load(storyModel.getStoryImage()), Toast.LENGTH_SHORT).show();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storyClickInterface.onStoryClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return storyModels.size();
    }
    private void setAnimation(View itemView, int position) {
        if (position > lastPos) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView donName, donContent;
        private ImageView donImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            donName = itemView.findViewById(R.id.donName);
            donContent = itemView.findViewById(R.id.donContent);
            donImg = itemView.findViewById(R.id.donImg);
        }
    }

    public interface StoryClickInterface {
        void onStoryClick(int position);
    }
}
