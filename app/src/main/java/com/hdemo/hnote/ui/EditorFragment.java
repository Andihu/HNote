package com.hdemo.hnote.ui;

import android.icu.lang.UCharacter;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
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

import java.util.Objects;

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
        noteViewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);
        current_folder = SpUtil.getInstance(getContext()).get("current_folder", 1);
        noteViewModel.getCurrentNote().observe(getViewLifecycleOwner(), noteEntity -> {
            if (workCode == CODE_WORK_EDIT)
                mViewDataBinding.editContext.setText(Objects.requireNonNull(noteViewModel.getCurrentNote().getValue()).getContent());
        });
    }

    @Override
    protected void initView() {
        initTitleBar();

        mMDWriter = new MDWriter(mViewDataBinding.editContext);

        mViewDataBinding.blod.setOnClickListener(view -> {
            mMDWriter.setAsBold();
            if (noteViewModel.getCurrentNote().getValue()!=null) {
                Objects.requireNonNull(noteViewModel.getCurrentNote().getValue()).setSubject(mMDWriter.getTitle());
                Objects.requireNonNull(noteViewModel.getCurrentNote().getValue()).setContent(mMDWriter.getContent());
            }
        });

        mViewDataBinding.heading.setOnClickListener(view -> {
            mMDWriter.setAsHeader();
            if (noteViewModel.getCurrentNote().getValue()!=null) {
                Objects.requireNonNull(noteViewModel.getCurrentNote().getValue()).setSubject(mMDWriter.getTitle());
                Objects.requireNonNull(noteViewModel.getCurrentNote().getValue()).setContent(mMDWriter.getContent());
            }
        });

        mViewDataBinding.blockquote.setOnClickListener(view -> {
            mMDWriter.setAsQuote();
            if (noteViewModel.getCurrentNote().getValue()!=null) {
                Objects.requireNonNull(noteViewModel.getCurrentNote().getValue()).setSubject(mMDWriter.getTitle());
                Objects.requireNonNull(noteViewModel.getCurrentNote().getValue()).setContent(mMDWriter.getContent());
            }
        });

        mViewDataBinding.justifyCenter.setOnClickListener(view -> {
            mMDWriter.setAsCenter();
            if (noteViewModel.getCurrentNote().getValue()!=null) {
                Objects.requireNonNull(noteViewModel.getCurrentNote().getValue()).setSubject(mMDWriter.getTitle());
                Objects.requireNonNull(noteViewModel.getCurrentNote().getValue()).setContent(mMDWriter.getContent());
            }
        });

        mViewDataBinding.unorderedList.setOnClickListener(view -> {
            mMDWriter.setAsList();
            if (noteViewModel.getCurrentNote().getValue()!=null) {
                Objects.requireNonNull(noteViewModel.getCurrentNote().getValue()).setSubject(mMDWriter.getTitle());
                Objects.requireNonNull(noteViewModel.getCurrentNote().getValue()).setContent(mMDWriter.getContent());
            }
        });

        mViewDataBinding.editContext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (noteViewModel.getCurrentNote().getValue() != null) {
                    Objects.requireNonNull(noteViewModel.getCurrentNote().getValue()).setSubject(mMDWriter.getTitle());
                    Objects.requireNonNull(noteViewModel.getCurrentNote().getValue()).setContent(mMDWriter.getContent());
                }
            }
        });
    }

    private void initTitleBar() {
        mViewDataBinding.titleBar.setBackIcon(R.drawable.back_btn);
        mViewDataBinding.titleBar.addMenuItem(new TitleBar.TitleMenuItem(2, R.drawable.done_btn, true));

        mViewDataBinding.titleBar.setMenuClickListener(titleMenuItem -> {
            switch (titleMenuItem.getId()) {
                case 2:
                    if (StringUtils.isNull(mMDWriter.getContent())) {
                        Toast.makeText(getContext(), "请输入内容", Toast.LENGTH_SHORT).show();
                    } else {
                        if (workCode == CODE_WORK_NEW) {
                            NoteUtils.INSTANCE(requireActivity().getApplication()).saveNewNote(current_folder, mMDWriter.getTitle(), mMDWriter.getContent(), id -> {
                                if (id > 0) {
                                    NoteUtils.INSTANCE(requireActivity().getApplication()).getNoteById(id, note -> {
                                        Navigation.findNavController(requireActivity(), R.id.fragment).navigate(R.id.action_editorFragment_to_previewFragment);
                                        if (note != null) {
                                            noteViewModel.setCurrentNote(note);
                                        }
                                        return null;
                                    });
                                }
                                return null;
                            });
                        } else {
                            NoteUtils.INSTANCE(requireActivity().getApplication()).updateNote(noteViewModel.getCurrentNote().getValue(), id -> {
                                if (id > 0) {
                                    Toast.makeText(getContext(), "更新成功", Toast.LENGTH_SHORT).show();
                                }
                                return null;
                            });
                        }
                    }
                    break;
                default:
                    break;
            }
        });
        mViewDataBinding.titleBar.setOnBackClickListener(view -> NavHostFragment.findNavController(this).popBackStack());
    }

    @Override
    protected void afterInitData() {

    }

    @Override
    protected void startWork() {

    }
}
