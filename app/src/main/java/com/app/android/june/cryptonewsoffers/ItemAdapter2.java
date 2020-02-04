package com.app.android.june.cryptonewsoffers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.android.june.cryptonewsoffers.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Habeex on 4/20/2017.
 */

public class ItemAdapter2 extends RecyclerView.Adapter<ItemAdapter2.ViewHolder> {
    private List<Item> Promoted;
    private Context context;
    String description, url;

    public ItemAdapter2(Context applicationContext, List<Item> itemArrayList) {
        this.context = applicationContext;
        this.Promoted = itemArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.title.setText(Promoted.get(i).getLogin());
        description = Promoted.get(i).getTitleDetail();
        url = Promoted.get(i).getHtmlUrl();
        if (description.length() > 200) {
            viewHolder.titleDetail.setText(description.substring(0, 200));
        }else {
            viewHolder.titleDetail.setText(description);
        }
        Picasso.with(context)
                .load(Promoted.get(i).getAvatarUrl())
                .placeholder(R.mipmap.ic_launcher_round)
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return Promoted.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView imageView;
       // ImageView null;
        private TextView titleDetail;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            imageView = (ImageView) view.findViewById(R.id.cover);
            titleDetail = (TextView)view.findViewById(R.id.titldetail);

            //on item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        String title = Promoted.get(pos).getLogin();
                        String detail = Promoted.get(pos).getTitleDetail();
                        String imgUrl = Promoted.get(pos).getAvatarUrl();
                        String htmlUrl = Promoted.get(pos).getHtmlUrl();
                        String source = Promoted.get(pos).getSource();
                        String date = Promoted.get(pos).getDate();
                        String img = Promoted.get(pos).getImg();
                        Intent intent = new Intent(v.getContext(), NewsActivity.class);
                        intent.putExtra("title", title);
                        intent.putExtra("detail", detail);
                        intent.putExtra("imgUrl", imgUrl);
                        intent.putExtra("htmlUrl", htmlUrl);
                        intent.putExtra("source", source);
                        intent.putExtra("date", date);
                        intent.putExtra("img", img);
                        v.getContext().startActivity(intent);
                    }
                }

            });
        }
    }
}
