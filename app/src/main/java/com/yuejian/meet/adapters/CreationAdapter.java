package com.yuejian.meet.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuejian.meet.R;
import com.yuejian.meet.bean.CreationEntity;
import com.yuejian.meet.widgets.RecommendView;

import java.util.ArrayList;
import java.util.List;

public class CreationAdapter extends BaseAdapter<CreationAdapter.ViewHolder, CreationEntity> {

    private int type;

    public CreationAdapter(RecyclerView recyclerView, Context context, int type) {
        super(recyclerView, context);
        this.type = type;
    }

    @Override
    public void refresh(List<CreationEntity> creationEntities) {
        if (data == null) {
            data = new ArrayList<>();
        }
        data = creationEntities;
        notifyDataSetChanged();
    }

    @Override
    public void Loadmore(List<CreationEntity> creationEntities) {
        if (data == null) {
            data = new ArrayList<>();
        }
        data.addAll(creationEntities);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(new RecommendView(context));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CreationEntity entity = data.get(position);

        if (!(holder.itemView instanceof RecommendView)) return;
        RecommendView rv = (RecommendView) holder.itemView;

        switch (type) {
            //海报模板
            case 3:
                rv.setViewStatus(RecommendView.ViewType.MOULD, itemHeight);
                Glide.with(context).load(entity.getPreviewUrl()).into(rv.poster_img);
                rv.poster_content.setText(entity.getContentTitle());
                TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.textview_tag, null);
                textView.setText(entity.getLabelName());
                rv.poster_tag.addView(textView);
                break;
            //视频
            case 2:
                rv.setViewStatus(RecommendView.ViewType.VIDEO_VERTICAL, itemHeight);
                Glide.with(context).load(entity.getPhotoAndVideoUrl()).into(rv.video_vertical_img);
                rv.setLike(RecommendView.ViewType.VIDEO_VERTICAL, false, entity.getFabulousNum() + "");
                rv.video_vertical_tag.setText(entity.getLabelName());
                rv.video_vertical_content.setText(entity.getContentTitle());

                break;
            //文章
            case 1:
                rv.setViewStatus(RecommendView.ViewType.ARTICLE, ViewGroup.LayoutParams.WRAP_CONTENT);
                Glide.with(context).load(entity.getPhotoAndVideoUrl()).into(rv.article_img);
                rv.setLike(RecommendView.ViewType.ARTICLE, false, entity.getFabulousNum() + "");
                rv.article_content.setText(entity.getContentTitle());
                rv.article_content.setText(entity.getLabelName());
                break;
        }


    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
