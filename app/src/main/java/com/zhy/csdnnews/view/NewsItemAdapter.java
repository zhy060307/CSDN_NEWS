package com.zhy.csdnnews.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.zhy.bean.NewsItem;
import com.zhy.csdnnews.R;

import java.util.List;

public class NewsItemAdapter extends BaseAdapter {

    private Context context;
    private List<NewsItem> newsItemList;

    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;


    public NewsItemAdapter(Context context, List<NewsItem> newsItemList) {
        this.context = context;
        this.newsItemList = newsItemList;
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.images)
                .showImageForEmptyUri(R.drawable.images)
                .showImageOnFail(R.drawable.images)
                .cacheInMemory()
                .cacheOnDisc()
                        //.displayer(new RoundedBitmapDisplayer(20))
                .displayer(new FadeInBitmapDisplayer(300))
                .build();
    }

    public void addAll(List<NewsItem> datas) {
        newsItemList.addAll(datas);
    }

    public void setNewList(List<NewsItem> newsItemList) {
        this.newsItemList = newsItemList;
    }

    @Override
    public int getCount() {
        return newsItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.news_item, null);
            holder.mTitle = (TextView) convertView.findViewById(R.id.id_title);
            holder.mContent = (TextView) convertView.findViewById(R.id.id_content);
            holder.mDate = (TextView) convertView.findViewById(R.id.id_date);
            holder.mImg = (ImageView) convertView.findViewById(R.id.id_newsImg);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NewsItem item = newsItemList.get(position);
        holder.mTitle.setText(item.getTitle());
        holder.mContent.setText(item.getContent());
        holder.mDate.setText(item.getDate());
        String imgLink = item.getImgLink();

        if (TextUtils.isEmpty(imgLink)) {
            holder.mImg.setVisibility(View.GONE);
        } else {
            holder.mImg.setVisibility(View.VISIBLE);
            imageLoader.displayImage(imgLink, holder.mImg, options);
        }
        return convertView;
    }

    static class ViewHolder {
        TextView mTitle;
        TextView mContent;
        ImageView mImg;
        TextView mDate;
    }

}
