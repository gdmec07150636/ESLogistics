package com.example.rynfar.eslogistics;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rynfar on 2017/5/17.
 */

public class MeRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<String> titles;
    private Context context;

    public MeRecyclerViewAdapter(Context context, List<String> titles) {
        this.titles = titles;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.me_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        params.height = RecyclerView.LayoutParams.WRAP_CONTENT;
        holder.itemView.setLayoutParams(params);
        holder.mTextView.setText(titles.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView mTextView;

    public MyViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.me_setting_title);
    }
}
