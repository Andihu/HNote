package com.hdemo.hnote.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hdemo.hnote.data.DataSourceHelper;
import com.hdemo.hnote.data.FolderEntity;
import com.hdemo.hnote.data.NoteEntity;
import com.hdemo.hnote.utils.NoteUtils;

import java.net.MalformedURLException;
import java.util.List;


public class NoteViewModel extends AndroidViewModel {

    private Application application;

    private DataSourceHelper dataSourceHelper;


    private LiveData<FolderEntity> mCurrentFolder;

    private LiveData<List<NoteEntity>> mNoteEntitys;

    private LiveData<List<FolderEntity>> mAllFolder;

    private MutableLiveData<NoteEntity> mCurrentNote;


    public NoteViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        dataSourceHelper = DataSourceHelper.getInstance(application);
        mCurrentNote = new MutableLiveData<>();
    }

    public void setCurrentNote(NoteEntity note) {
        mCurrentNote.setValue(note);
    }

    public MutableLiveData<NoteEntity> getCurrentNote() {
        return mCurrentNote;
    }

    public LiveData<FolderEntity> getCurrentFolder() {
        return mCurrentFolder;
    }

    public void setCurrentFolder(long id) {
        if (mCurrentFolder == null)
            mCurrentFolder = dataSourceHelper.getFolderById(id);
    }

    public  LiveData<List<NoteEntity>> getNoteByFolderId(int id){
        if (mNoteEntitys==null){
             mNoteEntitys = dataSourceHelper.getNoteByFolderId(id);
        }
        return mNoteEntitys;
    }


    public LiveData<List<FolderEntity>> getAllFolder() {
        if (mAllFolder == null)
            mAllFolder = dataSourceHelper.getAllFolder();
        return mAllFolder;
    }

    public void deleteFolder(FolderEntity folderEntity, Function<Long, Void> result) {
        NoteUtils.INSTANCE(application).deleteFolder(folderEntity, result);
    }

    public void deleteNote(NoteEntity noteEntity, Function<Long, Void> result) {
        NoteUtils.INSTANCE(application).deleteNote(noteEntity, result);
    }

    public void updateNote(NoteEntity noteEntity, Function<Long, Void> result) {
        NoteUtils.INSTANCE(application).updateNote(noteEntity, result);
    }

    public void updateFolder(FolderEntity folderEntity, Function<Long, Void> result) {
        NoteUtils.INSTANCE(application).updateFolder(folderEntity, result);
    }

    public void insertFolder(FolderEntity noteEntity, Function<Long, Void> result) {
        NoteUtils.INSTANCE(application).insertFolder(noteEntity, result);
    }

    public void saveNotes(int folderId, String subject, String content, Function<Long, Void> result) {
        NoteUtils.INSTANCE(application).saveNewNote(folderId, subject, content, result);
    }

}
