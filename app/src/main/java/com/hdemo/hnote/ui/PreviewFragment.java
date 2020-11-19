package com.hdemo.hnote.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.hdemo.hnote.R;
import com.hdemo.hnote.base.BaseFragment;
import com.hdemo.hnote.data.DataSourceHelper;
import com.hdemo.hnote.data.NoteEntity;
import com.hdemo.hnote.databinding.FragmentPreviewLayoutBinding;
import com.hdemo.hnote.markdown.MDReader;
import com.hdemo.hnote.ui.widget.TitleBar;
import com.hdemo.hnote.utils.NoteUtils;

public class PreviewFragment extends BaseFragment<FragmentPreviewLayoutBinding> {

    private MDReader mMDReader;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_preview_layout;
    }

    @Override
    protected void initData() {
        NoteViewModel noteViewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);
        noteViewModel.getCurrentNote().observe(getViewLifecycleOwner(), noteEntity -> {
            mMDReader = new MDReader(noteEntity.getContent());
            mViewDataBinding.DisplayTextView.setTextKeepState(mMDReader.getFormattedContent(), TextView.BufferType.SPANNABLE);
        });
    }

    private void initTitleBar() {
        mViewDataBinding.titleBar.setBackIcon(R.drawable.back_btn);
        mViewDataBinding.titleBar.addMenuItem(new TitleBar.TitleMenuItem(1, R.drawable.image_btn, true));
        mViewDataBinding.titleBar.addMenuItem(new TitleBar.TitleMenuItem(2, R.drawable.delete, true));
        mViewDataBinding.titleBar.setMenuClickListener(titleMenuItem -> {
            switch (titleMenuItem.getId()) {
                case 1:
                    NavHostFragment.findNavController(this).popBackStack();
                    Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();



                    break;
                default:
                    break;
            }
        });
        mViewDataBinding.titleBar.setOnBackClickListener(view -> NavHostFragment.findNavController(this).popBackStack());

    }

    @Override
    protected void initView() {
        initTitleBar();
        mViewDataBinding.DisplayTextView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt(EditorFragment.KEY_WORK_CODE, EditorFragment.CODE_WORK_EDIT);
            Navigation.findNavController(requireActivity(), R.id.fragment).navigate(R.id.action_previewFragment_to_editorFragment, bundle);
        });
    }

    @Override
    protected void afterInitData() {

    }

    @Override
    protected void startWork() {

    }
}
