package com.example.rynfar.eslogistics;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rynfar on 2017/5/17.
 */

public class MeRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<String> titles;
    private Context context;
    private List<Integer> icons;

    public MeRecyclerViewAdapter(Context context, List<String> titles, List<Integer> icons) {
        this.titles = titles;
        this.context = context;
        this.icons = icons;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.me_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        params.height = RecyclerView.LayoutParams.WRAP_CONTENT;
        holder.itemView.setLayoutParams(params);
        holder.mTextView.setText(titles.get(position));
        holder.mImageView.setImageResource(icons.get(position));
        holder.viewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(),titles.get(position),Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        Intent intent = new Intent(v.getContext(), OrderAllActivity.class);
                        v.getContext().startActivity(intent);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView mTextView;
    ImageView mImageView;
    View viewList;

    public MyViewHolder(View itemView) {
        super(itemView);
        viewList = itemView;
        mTextView = (TextView) itemView.findViewById(R.id.me_setting_title);
        mImageView = (ImageView) itemView.findViewById(R.id.me_setting_icon);
    }
}
