package com.hdemo.hnote.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = NoteTable.TABLE_NAME)
public class NoteEntity {
    @ColumnInfo(name = NoteTable.Columns.ID)
    @PrimaryKey(autoGenerate = true)
    public long _id;
    @ColumnInfo(name = NoteTable.Columns.TIME)
    public long time;
    @ColumnInfo(name = NoteTable.Columns.CONTENT)
    private String content;
    @ColumnInfo(name = NoteTable.Columns.SUBJECT)
    private String subject;
    @ColumnInfo(name = NoteTable.Columns.END_TIME)
    private long end_time;
    @ColumnInfo(name = NoteTable.Columns.FOLDER_ID)
    private int folder_id;
    @ColumnInfo(name = NoteTable.Columns.FAVORITE)
    private int favorite;
}
