package com.example.android.miwok;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_phrases, container, false);
        String[] englishPhrases = { "I know kannada", "Good Morning", "How are you?", "I am fine" };
        String[] kannadaPhrases = { "Nanage kannada barutte", "Shubha Munjane", "Neevu hegiddeeri?", "Naanu chennagiddeeni" };
        ArrayList<Word> words = new ArrayList<>();
        for(int i=1; i< englishPhrases.length; ++i){
            words.add(new Word(englishPhrases[i], kannadaPhrases[i]));
        }
        WordAdapter phrasesAdapter = new WordAdapter(getActivity(), words, R.color.category_phrases);
        ListView listView = (ListView) rootView.findViewById(R.id.phrasesList);
        listView.setAdapter(phrasesAdapter);
        return rootView;
    }
}
