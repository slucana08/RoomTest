package com.example.sting.roomtest.data.source;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.sting.roomtest.data.entities.Word;
import com.example.sting.roomtest.data.source.local.AppDatabase;
import com.example.sting.roomtest.data.source.local.dao.WordDao;

import java.util.List;

public class DataSourceRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public DataSourceRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);

        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void updateAmount(int amountWord, String word){
        new updateTask(mWordDao).execute(amountWord,word);
    }

    private static class updateTask extends AsyncTask<Object,Void,Void>{

        private WordDao mAsyncTaskDao;

        updateTask(WordDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Object... objects) {
            int amountWord = (int) objects[0];
            String word = (String) objects[1];
            mAsyncTaskDao.updateAmount(amountWord,word);
            return null;
        }
    }


}
