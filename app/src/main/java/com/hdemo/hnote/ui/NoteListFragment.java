package com.hdemo.hnote.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hdemo.hnote.R;
import com.hdemo.hnote.base.BaseFragment;
import com.hdemo.hnote.data.FolderEntity;
import com.hdemo.hnote.data.NoteEntity;
import com.hdemo.hnote.databinding.FragmentNoteListLayoutBinding;
import com.hdemo.hnote.ui.widget.ComplexPopup;
import com.hdemo.hnote.ui.widget.TitleBar;
import com.hdemo.hnote.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;


public class NoteListFragment extends BaseFragment<FragmentNoteListLayoutBinding> {

    private RecyclerAdapter adapter;

    private NoteViewModel noteViewModel;

    private ComplexPopup complexPopup;

    private List<FolderEntity> folderEntities = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_note_list_layout;
    }

    private void initToolBar() {
        mViewDataBinding.titleBar.setTitle("便签", view -> {
            complexPopup = ComplexPopup.create(getContext());
            complexPopup.setData(folderEntities);
            complexPopup.setAnchorView(view);
            complexPopup.showAsDropDown();
        });
        mViewDataBinding.titleBar.setBackIcon(R.drawable.setting);
        mViewDataBinding.titleBar.addMenuItem(new TitleBar.TitleMenuItem(1, R.drawable.edit, true));
        mViewDataBinding.titleBar.setMenuClickListener(titleMenuItem -> {
            switch (titleMenuItem.getId()) {
                case 1:
                    Bundle bundle = new Bundle();
                    bundle.putInt(EditorFragment.KEY_WORK_CODE, EditorFragment.CODE_WORK_NEW);
                    Navigation.findNavController(requireActivity(), R.id.fragment).navigate(R.id.action_noteListFragment_to_editorFragment, bundle);
                    break;
                default:
                    break;
            }
        });
        mViewDataBinding.titleBar.setOnBackClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_noteListFragment_to_editorFragment));
    }


    @Override
    protected void initData() {

        adapter = new RecyclerAdapter(new RecyclerAdapter.OnNoteItemClickListener() {
            @Override
            public void onNoteClick(NoteEntity note) {
                Navigation.findNavController(requireActivity(), R.id.fragment).navigate(R.id.action_noteListFragment_to_previewFragment);
                noteViewModel.setCurrentNote(note);
            }

            @Override
            public boolean onLongClick(View v, NoteEntity note) {
                return false;
            }
        });
        mViewDataBinding.list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        mViewDataBinding.list.setAdapter(adapter);

        noteViewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);

        int current_folder = SpUtil.getInstance(getContext()).get("current_folder", 1);

        noteViewModel.getNoteByFolderId(current_folder).observe(getViewLifecycleOwner(), noteEntities -> {

            adapter.setData(noteEntities);

        });

        noteViewModel.getAllFolder().observe(getViewLifecycleOwner(),folders->{
            folderEntities = folders;
            if (complexPopup!=null)
            complexPopup.setData(folders);
        });

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
