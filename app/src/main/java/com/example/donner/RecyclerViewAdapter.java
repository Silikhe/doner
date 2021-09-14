package com.example.donner;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<Blogs> blogsArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<Blogs> blogsArrayList) {
        this.context = context;
        this.blogsArrayList = blogsArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_blog_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //get data
        Blogs blogs = blogsArrayList.get(position);


        holder.blogContent.setText(blogs.content);
        holder.blogTitle.setText(blogs.title);
        holder.blogName.setText(blogs.displayName);
        holder.blogImg.setImageURI(Uri.parse(blogs.img_url));

        String authorName = blogs.getDisplayName();
        String contents = blogs.getContent();
        String title = blogs.getTitle();
        String url = blogs.getImg_url();


//change content descrp from html format
        Document document = (Document) Jsoup.parse(contents);
        Elements elements = document.head().select("img");
        String image = elements.get(0).attr("src");
        Picasso .get()
    }

    @Override
    public int getItemCount() {
        return blogsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView blogContent;
        ImageView blogImg;
        TextView blogName;
        TextView blogTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            blogContent = itemView.findViewById(R.id.blogContent);
            blogImg = itemView.findViewById(R.id.blogImg);
            blogName = itemView.findViewById(R.id.blogName);
            blogTitle = itemView.findViewById(R.id.blogTile);

        }
    }
}
