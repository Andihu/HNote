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

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public int getFolder_id() {
        return folder_id;
    }

    public void setFolder_id(int folder_id) {
        this.folder_id = folder_id;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}
