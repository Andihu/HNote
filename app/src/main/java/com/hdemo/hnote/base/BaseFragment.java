package com.hdemo.hnote.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {

    protected T mViewDataBinding;

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void initData();

    protected abstract void initView();

    public boolean useDataBinding(){
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getView() != null){
            getView().setFocusableInTouchMode(true);
            getView().requestFocus();
            getView().setOnKeyListener((v, keyCode, event) -> {
                return onBackPressed();
            });
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(useDataBinding()){
            attachViewWithDataBinding(inflater, container);
            return mViewDataBinding.getRoot();
        } else {
            return inflater.inflate(getLayoutRes(), container, false);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        afterInitData();
        startWork();
    }

    private void attachViewWithDataBinding(LayoutInflater inflater, ViewGroup container){
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        mViewDataBinding.setLifecycleOwner(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract void afterInitData();

    protected abstract void startWork();

    protected boolean onBackPressed(){
        return false;
    }
}
