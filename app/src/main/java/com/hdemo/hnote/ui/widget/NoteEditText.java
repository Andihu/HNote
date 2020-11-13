package com.hdemo.hnote.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.hdemo.hnote.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2015-2020
 * FileName: NoteEditext
 * Author: hujian
 * Date: 2020/11/13 16:48
 * History:
 * <author> <time> <version> <desc>
 */
public class NoteEditText extends RecyclerView {

    private List<String> texts = new ArrayList<>();

    private static final int MIN_LINE = 10;

    public NoteEditText(@NonNull Context context) {
        this(context,null);
    }

    public NoteEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NoteEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        texts.add("dcasdcasdc");
        texts.add("dcasdcasdc");
        texts.add("dcasdcasdc");
        texts.add("dcasdcasdc");
        setAdapter(new TextAdapter());

    }

    class TextAdapter extends RecyclerView.Adapter<TextHolder>{

       @NonNull
       @Override
       public TextHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           return new TextHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.edtitext_item,null));
       }

       @Override
       public void onBindViewHolder(@NonNull TextHolder holder, int position) {
           holder.editText.setText(texts.get(position));
       }

       @Override
       public int getItemCount() {
           return Math.max(texts.size(), MIN_LINE);
       }
   }

   static class TextHolder extends RecyclerView.ViewHolder{

        EditText editText;

       public TextHolder(@NonNull View itemView) {
           super(itemView);
           editText = itemView.findViewById(R.id.edit_text);
       }
   }
}
