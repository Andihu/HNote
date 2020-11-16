package com.hdemo.hnote.ui;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hdemo.hnote.data.DataSourceHelper;
import com.hdemo.hnote.data.NoteEntity;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

class NoteViewModel extends AndroidViewModel {

    private DataSourceHelper dataSourceHelper;

    private MutableLiveData<NoteEntity> mCurrentNote;

    public NoteViewModel(@NonNull Application application) {

        super(application);

        dataSourceHelper = DataSourceHelper.getInstance(application);

        mCurrentNote = new MutableLiveData<>();

    }

    public MutableLiveData<NoteEntity> getCurrentNote() {
        return mCurrentNote;
    }

    public void setCurrentNote(int id){ mCurrentNote = (MutableLiveData<NoteEntity>) dataSourceHelper.getNoteById(id); }
}
