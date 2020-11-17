package com.hdemo.hnote.ui;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.room.util.StringUtil;

import com.hdemo.hnote.R;
import com.hdemo.hnote.base.BaseFragment;
import com.hdemo.hnote.data.DataSourceHelper;
import com.hdemo.hnote.data.NoteEntity;
import com.hdemo.hnote.databinding.FragmentEditorLayoutBinding;
import com.hdemo.hnote.markdown.MDWriter;
import com.hdemo.hnote.ui.widget.TitleBar;
import com.hdemo.hnote.utils.NoteUtils;
import com.hdemo.hnote.utils.SpUtil;
import com.hdemo.hnote.utils.StringUtils;

public class EditorFragment extends BaseFragment<FragmentEditorLayoutBinding> {

    private MDWriter mMDWriter;


    public static final String KEY_WORK_CODE = "WORK_CODE";

    public static final int CODE_WORK_NEW = 0;

    public static final int CODE_WORK_EDIT = 1;

    private int workCode = -1;
    private NoteViewModel noteViewModel;
    private int current_folder;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_editor_layout;
    }

    @Override
    protected void initData() {
        assert getArguments() != null;
        workCode = getArguments().getInt(KEY_WORK_CODE);
        noteViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(NoteViewModel.class);
        current_folder = SpUtil.getInstance(getContext()).get("current_folder", 0);
        noteViewModel.getCurrentNote().observe(getActivity(), new Observer<NoteEntity>() {
            @Override
            public void onChanged(NoteEntity noteEntity) {
                Log.e("huajin", "onChanged: " );
                if (workCode==CODE_WORK_EDIT)
                mViewDataBinding.editContext.setText(noteViewModel.getCurrentNote().getValue().getContent());
            }
        });
    }

    @Override
    protected void initView() {
        initTitleBar();

        mMDWriter = new MDWriter(mViewDataBinding.editContext);

        mViewDataBinding.blod.setOnClickListener(view -> mMDWriter.setAsBold());

        mViewDataBinding.heading.setOnClickListener(view -> mMDWriter.setAsHeader());

        mViewDataBinding.blockquote.setOnClickListener(view -> mMDWriter.setAsQuote());

        mViewDataBinding.justifyCenter.setOnClickListener(view -> mMDWriter.setAsCenter());

        mViewDataBinding.unorderedList.setOnClickListener(view -> mMDWriter.setAsList());


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
                    if (StringUtils.isNull(mMDWriter.getContent())) {
                        Toast.makeText(getContext(), "请输入内容", Toast.LENGTH_SHORT).show();
                    } else {
                        NoteUtils.INSTANCE(getActivity().getApplication()).saveNewNote(current_folder, mMDWriter.getTitle(), mMDWriter.getContent(), id -> {
                            if (id > 0) {
                             NoteUtils.INSTANCE(getActivity().getApplication()).getNoteById(id, note -> {
                                 if (note!=null){
                                     noteViewModel.setCurrentNote(note);
                                 }
                                 Navigation.findNavController(getActivity(), R.id.fragment).navigate(R.id.action_editorFragment_to_previewFragment);
                                 return null;
                             });
                            }
                            return null;
                        });
                    }
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
