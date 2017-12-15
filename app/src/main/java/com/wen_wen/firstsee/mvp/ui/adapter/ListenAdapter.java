package com.wen_wen.firstsee.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wen_wen.firstsee.R;
import com.wen_wen.firstsee.mvp.model.e.bean.ListenEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WeLot on 2017/12/15.
 */

public class ListenAdapter extends RecyclerView.Adapter<ListenAdapter.ListenViewHolder> {
    private Context context;
    private List<ListenEntity> listenEntityList;

    public ListenAdapter(Context context) {
        this.context = context;
        listenEntityList = new ArrayList<>();
    }

    @Override
    public ListenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listen_item, parent, false);
        ListenViewHolder viewHolder = new ListenViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ListenViewHolder holder, int position) {
        ListenEntity listenEntity = listenEntityList.get(position);

        if (listenEntity != null) {
            Glide.with(context)
                    .load(listenEntity.getUrl())
                    .asBitmap()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.cover);

            holder.content.setText(listenEntity.getDesc());
            holder.listen_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();

                    onItemClickListener.onItemClick(layoutPosition, holder.listen_card);
                }
            });
        } else {

            Glide.clear(holder.cover);
            holder.cover.setImageDrawable(null);
        }

    }

    @Override
    public int getItemCount() {
        return listenEntityList.size();
    }

    //添加
    public void addAll(Collection<ListenEntity> collection) {
        addAll(listenEntityList.size(), collection);
    }

    public void addAll(int positon, Collection<ListenEntity> collection) {
        if (collection == null && collection.size() == 0) {
            return;
        }

        int size = listenEntityList.size();
        listenEntityList.addAll(positon, collection);
        notifyItemRangeInserted(size, collection.size());

    }

    public static class ListenViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.listen_card_cover)
        ImageView cover;
        @BindView(R.id.link_card_content)
        TextView content;
        @BindView(R.id.listen_card)
        CardView listen_card;

        public ListenViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    private ListenAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
