package com.hdemo.hnote.ui;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.Navigation;
import com.hdemo.hnote.R;
import com.hdemo.hnote.base.BaseFragment;
import com.hdemo.hnote.databinding.FragmentEditorLayoutBinding;
import com.hdemo.hnote.markdown.MDWriter;
import com.hdemo.hnote.ui.widget.TitleBar;

public class EditorFragment extends BaseFragment<FragmentEditorLayoutBinding> {

    private MDWriter mMDWriter;

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

//        mMDWriter = new MDWriter(mViewDataBinding.editContext);

        /*mViewDataBinding.blod.setOnClickListener(view -> mMDWriter.setAsBold());

        mViewDataBinding.heading.setOnClickListener(view -> mMDWriter.setAsHeader());

        mViewDataBinding.blockquote.setOnClickListener(view -> mMDWriter.setAsQuote());

        mViewDataBinding.justifyCenter.setOnClickListener(view -> mMDWriter.setAsCenter());

        mViewDataBinding.unorderedList.setOnClickListener(view -> mMDWriter.setAsList());*/
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
