package com.app.android.june.cryptonewsoffers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class postAdapter extends RecyclerView.Adapter<postAdapter.MyviewHolder> {

    Context context;
    ArrayList<Post> posts;
    public postAdapter(Context c, ArrayList<Post>s){
        context = c;
        posts = s;
    }
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyviewHolder(LayoutInflater.from(context).inflate(R.layout.posts,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        holder.title.setText(posts.get(position).getTitle());
        holder.descr.setText(posts.get(position).getDescr());
        holder.link.setText(posts.get(position).getLink());
        holder.name.setText(posts.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder {
        TextView title, descr, link, name;
        String url;
        public MyviewHolder(View view) {
            super(view);
            title = (TextView)view.findViewById(R.id.post_title);
            descr = (TextView)view.findViewById(R.id.post_text);
            link = (TextView) view.findViewById(R.id.post_link);
            name = (TextView) view.findViewById(R.id.post_username);
            //view = (CardView) view.findViewById(R.id.card);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        url = posts.get(pos).getLink();
                        if (!url.startsWith("https://") && !url.startsWith("http://")) {
                            url = "http://" + url;
                            try {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                v.getContext().startActivity(intent);
                            } catch (Exception e) {
                                Toast.makeText(context, "Invalid URL", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            try {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                v.getContext().startActivity(intent);
                            } catch (Exception e) {
                                Toast.makeText(context, "Invalid URL", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }
            });
        }


    }
}