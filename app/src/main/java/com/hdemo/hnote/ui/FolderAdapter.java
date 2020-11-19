package com.hdemo.hnote.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hdemo.hnote.R;
import com.hdemo.hnote.data.FolderEntity;
import com.hdemo.hnote.data.NoteEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {
    private List<FolderEntity> data =new ArrayList<>();

    //更换数据
    public void setData(List<FolderEntity> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.folder_list_item,parent,false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FolderEntity bean = data.get(position);
        holder.title.setText(bean.foldName);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView title;
        private final ImageView current;

        public ViewHolder(View itemView) {
            super(itemView);
            title =  itemView.findViewById(R.id.name);
            current = itemView.findViewById(R.id.current);
        }

    }
}
