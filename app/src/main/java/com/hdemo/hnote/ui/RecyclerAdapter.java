package com.hdemo.hnote.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.hdemo.hnote.R;
import com.hdemo.hnote.data.NoteEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<NoteEntity> data =new ArrayList<>();
    private OnNoteItemClickListener listener;
    public static String getDateStr(long milliseconds) {
        return new SimpleDateFormat("yyyy年MM月dd日 EEEE HH点mm分", Locale.CHINA).format(milliseconds);
    }
    public RecyclerAdapter( OnNoteItemClickListener l){
        this.listener = l;
    }

    //更换数据
    public void setData(List<NoteEntity> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_item,parent,false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NoteEntity bean = data.get(position);
        holder.title.setText(getDateStr(bean.getTime()));
        holder.content.setText(bean.getContent().substring(0, Math.min(bean.getContent().length(),30))+"");
        initListener(holder,position);

    }
    private void initListener(final ViewHolder vh,final int pos) {
        if(listener != null){
            vh.itemView.setOnClickListener(v -> listener.onNoteClick(data.get(pos)));
            vh.itemView.setOnLongClickListener(v -> listener.onLongClick(v,data.get(pos)));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView title;
        private final TextView content;

        public ViewHolder(View itemView) {
            super(itemView);
            title =  itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
        }

    }



    interface OnNoteItemClickListener {
        /**
         * item点击回调
         * @param note
         */
        void onNoteClick(NoteEntity note);
        /**
         *
         * @param note
         * @return  是否消费
         */
        boolean onLongClick(View v, NoteEntity note);
    }
}
