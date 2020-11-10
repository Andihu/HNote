package com.hdemo.hnote.data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DataRepository {
    NoteDao wordDao;
    public DataRepository(Context context) {
        wordDao = NoteDataBase.getInstance(context).getNoteDao();
    }

    public long insertNote(NoteEntity... entities){
        return -1;
    }
    public long updateNote(NoteEntity... entities){
        return -1;
    }
    public LiveData<List<NoteEntity>> getAllNotes(){
        return null;
    }
    public LiveData<List<NoteEntity>> getAllFavoriteNotes(){
        return null;
    }
    public long deleteNotes(){
        return -1;
    }
}
