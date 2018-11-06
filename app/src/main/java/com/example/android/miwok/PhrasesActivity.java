package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);
        String[] englishPhrases = { "I know kannada", "Good Morning", "How are you?", "I am fine" };
        String[] kannadaPhrases = { "Nanage kannada barutte", "Shubha Munjane", "Neevu hegiddeeri?", "Naanu chennagiddeeni" };
        ArrayList<Word> words = new ArrayList<>();
        for(int i=1; i< englishPhrases.length; ++i){
            words.add(new Word(englishPhrases[i], kannadaPhrases[i]));
        }
        WordAdapter phrasesAdapter = new WordAdapter(this, words, R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.phrasesList);
        listView.setAdapter(phrasesAdapter);
    }
}
