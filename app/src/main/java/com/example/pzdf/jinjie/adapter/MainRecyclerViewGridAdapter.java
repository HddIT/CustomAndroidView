package com.example.pzdf.jinjie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.pzdf.jinjie.R;
import com.example.pzdf.jinjie.entity.GoodsInfoEntity;
import com.example.pzdf.jinjie.recyclerInterface.OnItemClickListener;
import com.example.pzdf.jinjie.recyclerInterface.OnItemLongClickListener;

import java.util.ArrayList;

/**
 * 功能描述:
 * Created by pzdf on 2018/11/13.
 */

public class MainRecyclerViewGridAdapter extends RecyclerView.Adapter<MainRecyclerViewGridAdapter.Holder> implements View.OnClickListener,View.OnLongClickListener {

    private ArrayList<GoodsInfoEntity> goodsInfoEntities = new ArrayList<>();
    private Context mc;
    private OnItemLongClickListener onItemLongClickListener;
    private OnItemClickListener onItemClickListener;


    public MainRecyclerViewGridAdapter(ArrayList<GoodsInfoEntity> goodsInfoEntities, Context mc) {
        this.goodsInfoEntities = goodsInfoEntities;
        this.mc = mc;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        view = LayoutInflater.from(mc).inflate(R.layout.recycler_grid_item_layout,null);

        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        GoodsInfoEntity infoEntity = goodsInfoEntities.get(i);
        Glide.with(mc).load(R.drawable.ic_launcher_background)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(holder.headImage);
        Glide.with(mc).load(R.drawable.ic_launcher_background)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(holder.addressImage);
        holder.address.setText(infoEntity.getAddress());
        holder.title.setText(infoEntity.getTitle());
        holder.content.setText(infoEntity.getContent());
        holder.desc.setText(infoEntity.getDesc());
        String htmlText = "<font color='#ff0000'>"+"¥"+infoEntity.getNewPrice()+"</font><font color='#ff0000'>"+"¥"+infoEntity.getOldPrice()+"</font>";
        holder.price.setText(Html.fromHtml(htmlText));
        holder.itemView.setTag(i);

    }

    @Override
    public int getItemCount() {
        return goodsInfoEntities.size();
    }


    @Override
    public void onClick(View view) {
        if (onItemClickListener != null){
            onItemClickListener.onItemClick(view, (Integer) view.getTag());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (onItemLongClickListener != null){
            onItemLongClickListener.onItemLongClick(view, (Integer) view.getTag());
        }
        return true;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener){
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView headImage;
        ImageView addressImage;
        TextView address;
        TextView title;
        TextView content;
        TextView desc;
        TextView price;

        public Holder(@NonNull View itemView) {
            super(itemView);
            headImage = itemView.findViewById(R.id.headImg);
            addressImage = itemView.findViewById(R.id.address_img);
            address = itemView.findViewById(R.id.address);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            desc = itemView.findViewById(R.id.desc);
            price = itemView.findViewById(R.id.price);
        }
    }
}
