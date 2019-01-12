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

import com.app.android.june.cryptonewsoffers.model.Item2;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter2  extends RecyclerView.Adapter<ItemAdapter2.ViewHolder> {
    private List<Item2> Data;
    private Context context;
    String description, url;

    public ItemAdapter2(Context applicationContext, List<Item2> itemArrayList) {
        this.context = applicationContext;
        this.Data = itemArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list2, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.title.setText(Data.get(i).getLogin());
        description = Data.get(i).getTitleDetail();
        url = Data.get(i).getHtmlUrl();
        if (description.length() > 200) {
            viewHolder.titleDetail.setText(description.substring(0, 200));
        }else {
            viewHolder.titleDetail.setText(description);
        }
        Picasso.with(context)
                .load(Data.get(i).getAvatarUrl())
                .placeholder(R.mipmap.ic_launcher_round)
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView imageView;
        private TextView titleDetail;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title2);
            imageView = (ImageView) view.findViewById(R.id.cover2);
            titleDetail = (TextView)view.findViewById(R.id.titldetail2);

            //on Item2 click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Data.get(pos).getHtmlUrl()));
                        v.getContext().startActivity(intent);
                    }
                }

            });
        }
    }
}
