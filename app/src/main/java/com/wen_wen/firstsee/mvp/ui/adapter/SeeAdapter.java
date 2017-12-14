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
import com.wen_wen.firstsee.mvp.model.e.bean.SeeEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WeLot on 2017/12/14.
 */

public class SeeAdapter extends RecyclerView.Adapter<SeeAdapter.SeeVieHolder> {
    private Context context;
    private List<SeeEntity> seeEntityList;

    public SeeAdapter(Context context) {
        this.context = context;
        seeEntityList = new ArrayList<>();
    }

    @Override
    public SeeVieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.see_item, parent, false);
        return new SeeVieHolder(view);
    }
    @Override
    public void onBindViewHolder(final SeeVieHolder holder, int position) {
        //展示部分数据
        SeeEntity entity = seeEntityList.get(position);
        if (entity != null) {
            Glide.with(context)
                    .load(entity.getImgUrl())
                    .asBitmap()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .skipMemoryCache(true)
                    .into(holder.seeIv);

            //标题
            holder.seeTitle.setText(entity.getTitle());
            //内容
            holder.seeContent.setText(entity.getDesc());
            //点击事件
            holder.seeCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    onItemClickListenr.onItemClick(layoutPosition, holder.seeCard);
                }
            });
        } else {
            Glide.clear(holder.seeIv);

            holder.seeIv.setImageDrawable(null);
        }
    }

    @Override
    public int getItemCount() {
        return seeEntityList.size();
    }
    //添加
    public void addAll(Collection<SeeEntity> collection) {
        addAll(seeEntityList.size(), collection);

    }
    //添加
    public void addAll(int position, Collection<SeeEntity> collection) {
        if (collection == null || collection.size() == 0) {
            return;
        }
        int size = seeEntityList.size();
        seeEntityList.addAll(position, collection);
        //刷新数据
        notifyItemRangeInserted(size, collection.size());
    }

    public static class SeeVieHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.see_card)
        CardView seeCard;
        @BindView(R.id.see_item_iv)
        ImageView seeIv;
        @BindView(R.id.see_item_title)
        TextView seeTitle;
        @BindView(R.id.see_item_content)
        TextView seeContent;

        public SeeVieHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //监听
    public interface onItemClickListenr {

        void onItemClick(int positon, View view);
    }

    private SeeAdapter.onItemClickListenr onItemClickListenr;

    public void setOnItemClickListenr(SeeAdapter.onItemClickListenr onItemClickListenr) {
        this.onItemClickListenr = onItemClickListenr;
    }
}
