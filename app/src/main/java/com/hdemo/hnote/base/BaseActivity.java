package com.hdemo.hnote.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    private static List<Activity> sAllActivity = new CopyOnWriteArrayList<>();

    protected T mViewDataBinding;
    private static final int REQUEST_DEFAULT_SMS = 990;

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void afterInitData();

    protected abstract void startWork();

    public boolean useDataBinding() {
        return true;
    }

    public boolean subscribeDefaultSmsPermission() {
        return true;
    }

    protected List<String> permissions() {
        return null;
    }

    protected void permissionsAllGranted() {
    }

    protected void permissionsDenied() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        addActivity(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onCreate(savedInstanceState);

        if (processOnCreateBefore(savedInstanceState)) {
            return;
        }

        // force portrait
        try {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } catch (Throwable t) {
            Logger.e("baseActivity adapter android 8.0 err");
        }

        if (useDataBinding()) {
            attachViewWithDataBinding();
        } else {
            setContentView(getLayoutRes());
        }

        initView();
        initData();
        afterInitData();
        startWork();
    }

    protected boolean processOnCreateBefore(@Nullable Bundle savedInstanceState) {
        return false;
    }

    private void attachViewWithDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutRes());
        mViewDataBinding.setLifecycleOwner(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_DEFAULT_SMS) {
            if (resultCode == RESULT_OK) {

            } else {
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private static void addActivity(Activity activity) {
        sAllActivity.add(activity);
    }

    private static void removeActivity(Activity activity) {
        sAllActivity.remove(activity);
        if (sAllActivity.size() == 0) {
            System.exit(0);
        }
    }

    protected static void exitApp() {
        try {
            for (int i = 0, limit = sAllActivity.size(); i < limit; i++) {
                sAllActivity.get(i).finishAfterTransition();
            }
        } catch (Throwable e) {
            Logger.e("BaseActivity.exitApp error:" + e, e);
            System.exit(1);
        }
    }
}
