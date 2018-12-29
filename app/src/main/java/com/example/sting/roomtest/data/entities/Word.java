package com.example.sting.roomtest.data.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    private int amountWord;

    public Word(String word,int amountWord) {
        this.mWord = word;
        this.amountWord = amountWord;
    }

    public String getWord(){
        return this.mWord;
    }

    public void setWord(String word){
        this.mWord = word;
    }

    public int getAmountWord(){
        return this.amountWord;
    }

    public void setAmountWord(int amountWord) {
        this.amountWord = amountWord;
    }
}