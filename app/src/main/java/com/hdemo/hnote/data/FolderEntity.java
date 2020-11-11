package com.hdemo.hnote.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = FolderTable.TABLE_NAME)
public class FolderEntity {
    @ColumnInfo(name = FolderTable.Columns.ID)
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = FolderTable.Columns.NOTE_ID)
    public long note_id;

    @ColumnInfo(name = FolderTable.Columns.FOLDER_NAME)
    public String foldName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getNote_id() {
        return note_id;
    }

    public void setNote_id(long note_id) {
        this.note_id = note_id;
    }

    public String getFoldName() {
        return foldName;
    }

    public void setFoldName(String foldName) {
        this.foldName = foldName;
    }
}
