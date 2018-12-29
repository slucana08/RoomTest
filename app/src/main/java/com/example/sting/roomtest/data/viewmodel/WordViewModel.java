package com.example.sting.roomtest.data.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.sting.roomtest.data.entities.Word;
import com.example.sting.roomtest.data.source.DataSourceRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private DataSourceRepository mRepository;
    private LiveData<List<Word>> mAllwords;

    public WordViewModel(Application application){
        super(application);
        mRepository = new DataSourceRepository(application);
        mAllwords = mRepository.getAllWords();
    }

    public LiveData<List<Word>> getAllwords(){
        return this.mAllwords;
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }

    public void updateAmount(int amounWord, String word){
        mRepository.updateAmount(amounWord,word);
    }
}
