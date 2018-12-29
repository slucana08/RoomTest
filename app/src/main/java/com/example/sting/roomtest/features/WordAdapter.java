package com.example.sting.roomtest.features;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sting.roomtest.R;
import com.example.sting.roomtest.data.entities.Word;
import com.example.sting.roomtest.data.viewmodel.WordViewModel;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    class WordViewHolder extends RecyclerView.ViewHolder {

        TextView wordItemView,amountTextView;
        Button addButton;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word_text_view);
            amountTextView = itemView.findViewById(R.id.amount_text_view);
            addButton = itemView.findViewById(R.id.add_button);
        }
    }

    private final LayoutInflater mInflater;
    private List<Word> mWords; // Cached copy of words
    private final WordViewModel viewModel;

    public WordAdapter(Context context,WordViewModel viewModel) {
        mInflater = LayoutInflater.from(context);
        this.viewModel = viewModel;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_list, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mWords != null) {
            final Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
            holder.amountTextView.setText(String.valueOf(current.getAmountWord()));
            holder.addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int amountWord = current.getAmountWord() + 1;
                    String word = current.getWord();
                    viewModel.updateAmount(amountWord,word);
                    notifyDataSetChanged();
                }
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
        }
    }

    void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }
}