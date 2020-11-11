package com.hdemo.hnote.ui;

import android.widget.Toast;

import androidx.navigation.Navigation;

import com.hdemo.hnote.R;
import com.hdemo.hnote.base.BaseFragment;
import com.hdemo.hnote.databinding.FragmentPreviewLayoutBinding;
import com.hdemo.hnote.ui.widget.TitleBar;

public class PreviewFragment extends BaseFragment<FragmentPreviewLayoutBinding> {
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_preview_layout;
    }

    @Override
    protected void initData() {

    }
    private void initTitleBar() {
        mViewDataBinding.titleBar.setBackIcon(R.drawable.back);
        mViewDataBinding.titleBar.addMenuItem(new TitleBar.TitleMenuItem( 1,R.drawable.send,true));
        mViewDataBinding.titleBar.addMenuItem(new TitleBar.TitleMenuItem(2,R.drawable.delete,true));
        mViewDataBinding.titleBar.setMenuClickListener(titleMenuItem -> {
            switch (titleMenuItem.getId()){
                case 1:
                    Toast.makeText(getContext(),"1",Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(getContext(),"2",Toast.LENGTH_SHORT).show();
                    break;
                default:break;
            }
        });
        mViewDataBinding.titleBar.setOnBackClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_previewFragment_to_editorFragment));
    }
    @Override
    protected void initView() {
        initTitleBar();
    }

    @Override
    protected void afterInitData() {

    }

    @Override
    protected void startWork() {

    }
}
