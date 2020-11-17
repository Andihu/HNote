package com.hdemo.hnote.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.hdemo.hnote.R;
import com.hdemo.hnote.base.BaseFragment;
import com.hdemo.hnote.data.DataSourceHelper;
import com.hdemo.hnote.data.NoteEntity;
import com.hdemo.hnote.databinding.FragmentPreviewLayoutBinding;
import com.hdemo.hnote.markdown.MDReader;
import com.hdemo.hnote.ui.widget.TitleBar;
import com.hdemo.hnote.utils.NoteUtils;

public class PreviewFragment extends BaseFragment<FragmentPreviewLayoutBinding> {

    private NoteViewModel noteViewModel;
    private MDReader mMDReader;
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_preview_layout;
    }

    @Override
    protected void initData() {
        noteViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(NoteViewModel.class);
        noteViewModel.getCurrentNote().observe(getActivity(), new Observer<NoteEntity>() {
            @Override
            public void onChanged(NoteEntity noteEntity) {
                mMDReader = new MDReader(noteEntity.getContent());
                mViewDataBinding.DisplayTextView.setTextKeepState(mMDReader.getFormattedContent(), TextView.BufferType.SPANNABLE);
            }
        });
    }
    private void initTitleBar() {
        mViewDataBinding.titleBar.setBackIcon(R.drawable.back_btn);
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

    }
    @Override
    protected void initView() {
        initTitleBar();
        mViewDataBinding.DisplayRootView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt(EditorFragment.KEY_WORK_CODE, EditorFragment.CODE_WORK_EDIT);
            Navigation.findNavController(getActivity(), R.id.fragment).navigate(R.id.action_editorFragment_to_previewFragment,bundle);
        });
    }

    @Override
    protected void afterInitData() {

    }

    @Override
    protected void startWork() {

    }
}
