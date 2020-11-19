package com.hdemo.hnote.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.hdemo.hnote.R;
import com.hdemo.hnote.databinding.TitleLayoutBinding;


public class TitleBar extends LinearLayout implements View.OnClickListener{

    private TitleLayoutBinding titleLayoutBinding;

    public TitleBar(Context context) {
        super(context);
        initView();
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        titleLayoutBinding= DataBindingUtil.inflate(((Activity) getContext()).getLayoutInflater(),R.layout.title_layout,null,false);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(titleLayoutBinding.getRoot().getRootView(), layoutParams);
        titleLayoutBinding.back.setOnClickListener(view -> {
            if (onBackClickListener!=null){
                onBackClickListener.onClick( titleLayoutBinding.back);
            }
        });
    }

    public void setTitle(String title,OnClickListener onClickListener){
        titleLayoutBinding.title.setText(title);
        titleLayoutBinding.title.setOnClickListener(onClickListener);
    }


    public void setBackIcon(@DrawableRes int res){
        titleLayoutBinding.back.setImageResource(res);
    }

    public void addMenuItem(TitleMenuItem item){
        TextView textView =new TextView( getContext());
        textView.setTag(item);
        textView.setBackgroundResource(item.getResourceId());
        textView.setOnClickListener(this::onClick);
        titleLayoutBinding.menu.addView(textView);
        addDivider();
    }
    public void addDivider(){
        LayoutParams layoutParams = new LayoutParams(20, ViewGroup.LayoutParams.MATCH_PARENT);
        View view =new View(getContext());
        titleLayoutBinding.menu.addView(view,layoutParams);
    }


    @Override
    public void onClick(View view) {
        TitleMenuItem item= (TitleMenuItem) view.getTag();
        if (menuClickListener!=null){
            menuClickListener.onClick(item);
        }
    }

    private onMenuClickListener menuClickListener;

    public void setMenuClickListener(onMenuClickListener menuClickListener) {
        this.menuClickListener = menuClickListener;
    }

    public interface onMenuClickListener{
        void onClick(TitleMenuItem titleMenuItem);
    }
    private onBackClickListener onBackClickListener;

    public void setOnBackClickListener(TitleBar.onBackClickListener onBackClickListener) {
        this.onBackClickListener = onBackClickListener;
    }

    public interface onBackClickListener{
        void onClick(View view);
    }
    public static class TitleMenuItem {
        int id;
        int resourceId;
        boolean isEnable = true;

        public TitleMenuItem(int id, int resourceId, boolean isEnable) {
            this.id = id;
            this.resourceId = resourceId;
            this.isEnable = isEnable;
        }

        public int getId() {
            return id;
        }

        public int getResourceId() {
            return resourceId;
        }

        public boolean isEnable() {
            return isEnable;
        }

        public void setEnable(boolean enable) {
            isEnable = enable;
        }
    }
}
