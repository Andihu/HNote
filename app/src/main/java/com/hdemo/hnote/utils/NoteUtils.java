package com.hdemo.hnote.utils;

import android.app.Application;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;

import com.hdemo.hnote.data.DataSourceHelper;
import com.hdemo.hnote.data.FolderEntity;
import com.hdemo.hnote.data.NoteEntity;

import java.util.List;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

/**
 * Copyright (C), 2015-2020
 * FileName: NoteUtils
 * Author: hujian
 * Date: 2020/11/17 11:28
 * History:
 * <author> <time> <version> <desc>
 */
public class NoteUtils {

    private static NoteUtils INSTANCE = null;

    private DataSourceHelper dataSourceHelper ;

    public static synchronized NoteUtils INSTANCE(Application application) {
        if (INSTANCE==null){
            INSTANCE = new NoteUtils(application);
        }
        return INSTANCE;
    }

    public NoteUtils(Application application) {
        this.dataSourceHelper = new DataSourceHelper(application);
    }


    public void getNoteById(long id, Function<NoteEntity, Void> result){
        Observable.create((ObservableOnSubscribe<NoteEntity>) emitter -> {
            emitter.onNext( dataSourceHelper.getNoteById(id));
            emitter.onComplete();
        }).compose(RxSchedulersUtils.io2main()).subscribe(noteEntity -> {
            if (result != null) {
                result.apply(noteEntity);
            }
        }, throwable -> {
            if (result != null) {
                result.apply(null);
            }
        });
    }


    public void saveNewNote(int folderId,String subject, String content , Function<Long, Void> result){
        Observable.create((ObservableOnSubscribe<Long>) emitter -> {
            long id = -1;
           FolderEntity folderById = dataSourceHelper.getFolderById(folderId);
            if (folderById==null){
                FolderEntity folderEntity = new FolderEntity();
                folderEntity.setFoldName("新建文件夹");
                id = dataSourceHelper.insertFolder(folderEntity);
            }else {
                id = folderId;
            }
            NoteEntity noteEntity = new NoteEntity();
            noteEntity.setContent(content);
            noteEntity.setSubject(subject);
            noteEntity.setTime(System.currentTimeMillis());
            noteEntity.setFolder_id((int) id);
            emitter.onNext( dataSourceHelper.insertNote(noteEntity));
            emitter.onComplete();
        }).compose(RxSchedulersUtils.io2main()).subscribe(id -> {
            if (result != null) {
                result.apply(id);
            }
        }, throwable -> {
            if (result != null) {
                result.apply(-1L);
            }
        });
    }

    public void updateNote(NoteEntity noteEntity, Function<Long, Void> result){
        Observable.create((ObservableOnSubscribe<Long>) emitter -> {
            emitter.onNext( dataSourceHelper.updateNote(noteEntity));
            emitter.onComplete();
        }).compose(RxSchedulersUtils.io2main()).subscribe(id -> {
            if (result != null) {
                result.apply(id);
            }
        }, throwable -> {
            if (result != null) {
                result.apply(-1L);
            }
        });
    }

    public void insertFolder(FolderEntity noteEntity, Function<Long, Void> result){
        Observable.create((ObservableOnSubscribe<Long>) emitter -> {
            emitter.onNext( dataSourceHelper.insertFolder(noteEntity));
            emitter.onComplete();
        }).compose(RxSchedulersUtils.io2main()).subscribe(id -> {
            if (result != null) {
                result.apply(id);
            }
        }, throwable -> {
            if (result != null) {
                result.apply(-1L);
            }
        });
    }


    public void updateFolder(FolderEntity folderEntity, Function<Long, Void> result){
        Observable.create((ObservableOnSubscribe<Long>) emitter -> {
            emitter.onNext( dataSourceHelper.upDataFolder(folderEntity));
            emitter.onComplete();
        }).compose(RxSchedulersUtils.io2main()).subscribe(id -> {
            if (result != null) {
                result.apply(id);
            }
        }, throwable -> {
            if (result != null) {
                result.apply(-1L);
            }
        });
    }

    public void deleteNote(NoteEntity noteEntity, Function<Long, Void> result){
        Observable.create((ObservableOnSubscribe<Long>) emitter -> {
            emitter.onNext( dataSourceHelper.deleteNotes(noteEntity));
            emitter.onComplete();
        }).compose(RxSchedulersUtils.io2main()).subscribe(id -> {
            if (result != null) {
                result.apply(id);
            }
        }, throwable -> {
            if (result != null) {
                result.apply(-1L);
            }
        });
    }

    public void deleteFolder(FolderEntity folderEntity, Function<Long, Void> result){
        Observable.create((ObservableOnSubscribe<Long>) emitter -> {
            LiveData<List<NoteEntity>> noteByFolderId = dataSourceHelper.getNoteByFolderId(folderEntity.getId());
            for (NoteEntity noteEntity : noteByFolderId.getValue()) {
                dataSourceHelper.deleteNotes(noteEntity);
            }
            emitter.onNext(dataSourceHelper.deleteFolder(folderEntity) );
            emitter.onComplete();
        }).compose(RxSchedulersUtils.io2main()).subscribe(id -> {
            if (result != null) {
                result.apply(id);
            }
        }, throwable -> {
            if (result != null) {
                result.apply(-1L);
            }
        });
    }
}
