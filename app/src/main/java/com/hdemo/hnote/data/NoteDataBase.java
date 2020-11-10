package com.hdemo.hnote.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NoteEntity.class},version = 2,exportSchema = false)
public abstract class NoteDataBase extends RoomDatabase {

    private static NoteDataBase INSTANCE = null;

    static synchronized NoteDataBase getInstance(Context context){
        if (INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context,NoteDataBase.class,"note_database")
                    .build();
        }
        return INSTANCE;
    }

    public  abstract  NoteDao getNoteDao();

}
