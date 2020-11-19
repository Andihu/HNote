package com.hdemo.hnote.ui.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hdemo.hnote.R;
import com.hdemo.hnote.data.FolderEntity;
import com.hdemo.hnote.ui.FolderAdapter;
import com.zyyoona7.popup.BasePopup;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2015-2020
 * FileName: ComplexPopup
 * Author: hujian
 * Date: 2020/11/19 14:27
 * History:
 * <author> <time> <version> <desc>
 */
public class ComplexPopup extends BasePopup<ComplexPopup> {

    private List<FolderEntity> data =new ArrayList<>();

    public Button edit,new_folder;

    private RecyclerView list;

    private FolderAdapter folderAdapter;

    public static ComplexPopup create(Context context){

        return new ComplexPopup(context);
    }

    protected ComplexPopup(Context context) {
        setContext(context);
        folderAdapter = new FolderAdapter();
    }


    @Override
    protected void initAttributes() {
        setContentView(R.layout.folder_layout, ViewGroup.LayoutParams.MATCH_PARENT,500);
        setFocusAndOutsideEnable(false)
                .setBackgroundDimEnable(true)
                .setDimValue(0.5f);
    }

    @Override
    protected void initViews(View view, ComplexPopup complexPopup) {
        list = findViewById(R.id.list);
        edit = findViewById(R.id.edit);
        new_folder = findViewById(R.id.new_folder);
        list.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        list.setAdapter(folderAdapter);
    }

    public void setData(List<FolderEntity> data){
        folderAdapter.setData(data);
    }

}
