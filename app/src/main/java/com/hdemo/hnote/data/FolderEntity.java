package com.hdemo.hnote.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = FolderTable.TABLE_NAME)
public class FolderEntity {

    @ColumnInfo(name = FolderTable.Columns.ID)
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = FolderTable.Columns.FOLDER_NAME)
    public String foldName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoldName() {
        return foldName;
    }

    public void setFoldName(String foldName) {
        this.foldName = foldName;
    }

}
