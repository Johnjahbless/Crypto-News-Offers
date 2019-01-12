package com.app.android.june.cryptonewsoffers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.android.june.cryptonewsoffers.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Habeex on 4/20/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> Promoted;
    private Context context;
    String description, url;

    public ItemAdapter(Context applicationContext, List<Item> itemArrayList) {
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
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Promoted.get(pos).getHtmlUrl()));
                        v.getContext().startActivity(intent);
                    }
                }

            });
        }
    }
}
