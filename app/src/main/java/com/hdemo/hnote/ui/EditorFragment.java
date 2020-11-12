package com.hdemo.hnote.ui;
import android.widget.Toast;

import androidx.navigation.Navigation;
import com.hdemo.hnote.R;
import com.hdemo.hnote.base.BaseFragment;
import com.hdemo.hnote.databinding.FragmentEditorLayoutBinding;
import com.hdemo.hnote.ui.widget.TitleBar;

public class EditorFragment extends BaseFragment<FragmentEditorLayoutBinding> {
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_editor_layout;
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initTitleBar();
    }

    private void initTitleBar() {
        mViewDataBinding.titleBar.setBackIcon(R.drawable.back_btn);
        mViewDataBinding.titleBar.addMenuItem(new TitleBar.TitleMenuItem(2, R.drawable.done_btn, true));
        mViewDataBinding.titleBar.addMenuItem(new TitleBar.TitleMenuItem(1, R.drawable.image_btn, true));
        mViewDataBinding.titleBar.setMenuClickListener(titleMenuItem -> {
            switch (titleMenuItem.getId()) {
                case 1:
                    Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(getActivity(), R.id.fragment).navigate(R.id.action_editorFragment_to_previewFragment);
                    break;
                default:
                    break;
            }
        });
        mViewDataBinding.titleBar.setOnBackClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_editorFragment_to_noteListFragment));
    }

    @Override
    protected void afterInitData() {

    }

    @Override
    protected void startWork() {

    }
}
