package com.wen_wen.firstsee.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wen_wen.firstsee.R;
import com.wen_wen.firstsee.mvp.model.e.bean.LinkEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WeLot on 2017/12/15.
 */

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.LinkViewHolder> {
    private Context context;
    private List<LinkEntity> linkEntityList;
    public LinkAdapter(Context context) {
        this.context = context;
        linkEntityList = new ArrayList<>();
        DisplayMetrics metrics = new DisplayMetrics();
    }
    @Override
    public LinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.link_item, parent, false);
        LinkViewHolder viewHolder = new LinkViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final LinkViewHolder holder, int position) {
        LinkEntity linkEntity = linkEntityList.get(position);
        if (linkEntity != null) {
            Glide.with(context)
                    .load(linkEntity.getImgUrl())
                    .asBitmap()
                    .centerCrop()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.cover);

            holder.content.setText(linkEntity.getContent());
            holder.link_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();

                    onItemClickListener.onItemClick(layoutPosition, holder.link_card);
                }
            });
        } else {
            Glide.clear(holder.cover);
            holder.cover.setImageDrawable(null);
        }


    }

    @Override
    public int getItemCount() {
        return linkEntityList.size();
    }

    //添加
    public void addAll(Collection<LinkEntity> collection) {

        addAll(linkEntityList.size(), collection);
    }

    public void addAll(int position, Collection<LinkEntity> collection) {
        if (collection == null && collection.size() == 0) {
            return;
        }

        int size = linkEntityList.size();
        linkEntityList.addAll(position, collection);
        notifyItemRangeInserted(size, collection.size());
    }

    public static class LinkViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.link_card_cover)
        ImageView cover;
        @BindView(R.id.link_card_content)
        TextView content;
        @BindView(R.id.link_card)
        CardView link_card;

        public LinkViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    private LinkAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
