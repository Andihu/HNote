package com.hdemo.hnote.data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DataSourceHelper {

    private final NoteDao noteDao;

    private final FolderDao folderDao;

    private static DataSourceHelper INSTANCE = null;

    public static synchronized DataSourceHelper getInstance(Context context) {
        if (null == INSTANCE) {
            INSTANCE = new DataSourceHelper(context);
        }
        return INSTANCE;
    }

    public DataSourceHelper(Context context) {
        noteDao = NoteDataBase.getInstance(context).getNoteDao();
        folderDao = NoteDataBase.getInstance(context).getFolderDao();
    }

    public long insertNote(NoteEntity entities) {
        return noteDao.insertNote(entities);
    }

    public long updateNote(NoteEntity... entities) {
        return noteDao.updateNote(entities);
    }

    public LiveData<List<NoteEntity>> getAllNotes() {
        return noteDao.queryAllNotes();
    }

    public LiveData<List<NoteEntity>> getAllFavoriteNotes() {
        return noteDao.queryAllFavoriteNotes();
    }

    public long deleteNotes(NoteEntity noteEntity) {
        return noteDao.deleteNote(noteEntity);
    }

    public long insertFolder(FolderEntity folderEntities) {
        return folderDao.insertFolder(folderEntities);
    }

    public long upDataFolder(FolderEntity... folderEntities) {
        return folderDao.upDataFolder(folderEntities);
    }

    public long deleteFolder(FolderEntity folderEntity) {
        return folderDao.deleteFolder(folderEntity);
    }

    public LiveData<List<FolderEntity>> getAllFolder() {
        return folderDao.getAllFolder();
    }

}
