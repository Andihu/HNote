package com.hdemo.hnote.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.hdemo.hnote.R;
import com.hdemo.hnote.base.BaseFragment;
import com.hdemo.hnote.databinding.FragmentNoteListLayoutBinding;
import com.hdemo.hnote.ui.widget.TitleBar;


public class NoteListFragment extends BaseFragment<FragmentNoteListLayoutBinding> {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_note_list_layout;
    }

    private void initToolBar() {
        mViewDataBinding.titleBar.setTitle("便签");
        mViewDataBinding.titleBar.setBackIcon(R.drawable.setting);
        mViewDataBinding.titleBar.addMenuItem(new TitleBar.TitleMenuItem(1,R.drawable.edit,true));
        mViewDataBinding.titleBar.setMenuClickListener(titleMenuItem -> {
            switch (titleMenuItem.getId()){
                case 1:
                    Toast.makeText(getContext(),"1",Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(getActivity(),R.id.fragment).navigate(R.id.action_noteListFragment_to_editorFragment);
                    break;
                default:break;
            }
        });
        mViewDataBinding.titleBar.setOnBackClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_noteListFragment_to_editorFragment));
    }


    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        initToolBar();
    }

    @Override
    protected void afterInitData() {

    }

    @Override
    protected void startWork() {

    }
}
