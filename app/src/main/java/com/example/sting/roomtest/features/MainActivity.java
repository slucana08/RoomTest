package com.example.sting.roomtest.features;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sting.roomtest.R;
import com.example.sting.roomtest.data.entities.Word;
import com.example.sting.roomtest.data.viewmodel.WordViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WordViewModel viewModel;
    private Button addWordButton;
    private EditText addEditText;
    private String wordToAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WordAdapter adapter = new WordAdapter(this,viewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getAllwords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable List<Word> words) {
                adapter.setWords(words);
            }
        });

        addEditText = findViewById(R.id.add_edit_text);

        addWordButton = findViewById(R.id.add_word_button);
        addWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordToAdd = addEditText.getText().toString();
                if (TextUtils.isEmpty(wordToAdd)){
                    Toast.makeText(MainActivity.this,"Type word to add",Toast.LENGTH_SHORT).show();
                } else {
                    Word word = new Word(wordToAdd,1);
                    viewModel.insert(word);
                    Toast.makeText(MainActivity.this,"Word added",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
