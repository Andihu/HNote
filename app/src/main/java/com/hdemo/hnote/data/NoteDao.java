package com.hdemo.hnote.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    public long insertNote(NoteEntity noteEntities);

    @Update
    public int updateNote(NoteEntity... noteEntities);

    @Query("SELECT * FROM note ORDER BY ID")
    LiveData<List<NoteEntity>> queryAllNotes();

    @Delete
    public int deleteNote(NoteEntity noteEntities);

    @Query("SELECT * FROM note WHERE favorite == 1 ORDER BY ID")
    LiveData<List<NoteEntity>> queryAllFavoriteNotes();

    @Query("SELECT * FROM note WHERE folder_id=:id")
    LiveData<NoteEntity> queryNoteByFolderId(long id);

}
