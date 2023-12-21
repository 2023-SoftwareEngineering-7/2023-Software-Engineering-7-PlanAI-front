package com.example.planai_front;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FriendlistRecyclerAdapter extends RecyclerView.Adapter<FriendlistRecyclerAdapter.MyViewHolder> {

    private Context context;
    private List<FriendlistItem> itemList;

    public FriendlistRecyclerAdapter(Context context, List<FriendlistItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.addlist_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FriendlistItem currentItem = itemList.get(position);

    }
    // 버튼에 대한 클릭 리스너 설정 등 추가적인 설정 가능
    // holder.button.setOnClickListener(v -> { /* 버튼 클릭 시 동작 */ });
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        Button button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.friend_profile);
            textView = itemView.findViewById(R.id.name_item);
            button = itemView.findViewById(R.id.namebutton);
        }
    }
}