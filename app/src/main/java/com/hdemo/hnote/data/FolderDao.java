package com.hdemo.hnote.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FolderDao {
    @Insert
    public long insertFolder(FolderEntity folderEntities);

    @Update
    public int upDataFolder(FolderEntity... folderEntities);

    @Delete
    public int deleteFolder(FolderEntity folderEntities);

    @Query(value = "SELECT * FROM folder ORDER BY id")
    public LiveData<List<FolderEntity>> getAllFolder();

    @Query(value = "SELECT * FROM folder WHERE id=:folderId")
    public FolderEntity getFolderById(long folderId);

}
