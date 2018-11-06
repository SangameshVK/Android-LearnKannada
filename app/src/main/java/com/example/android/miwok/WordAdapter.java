package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.content.ContextCompat;

import java.util.List;

public class WordAdapter extends ArrayAdapter {

    private int _colorResourceId;
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        final LinearLayout translations = (LinearLayout) listItemView.findViewById(R.id.translations_layout);
        int backgroundColor = ContextCompat.getColor(getContext(), _colorResourceId);
        translations.setBackgroundColor(backgroundColor);
        final ImageView playImage = (ImageView) listItemView.findViewById(R.id.play_image);
        playImage.setBackgroundColor(backgroundColor);

        final Word currentWord = (Word)getItem(position);
        TextView englishTextView = (TextView) listItemView.findViewById(R.id.english_text_view);
        englishTextView.setText(currentWord.getEnglishWord());

        TextView kannadaTextView = (TextView) listItemView.findViewById(R.id.kannada_text_view);
        kannadaTextView.setText(currentWord.getKannadaWord());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.list_image_view);
        if ( currentWord.hasImage() ) {
            imageView.setImageResource(currentWord.getImageResoucreID());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        /*translations.setOnClickListener(new View.OnClickListener() {
            MediaPlayer audioPlayer = MediaPlayer.create(translations.getContext(), currentWord.getAudioResourceID());
            @Override
            public void onClick(View view) {
                if (audioPlayer.isPlaying()) {
                    audioPlayer.pause();
                }
                else {
                    audioPlayer.start();
                }
            }
        });*/
        /*imageView.setWillNotDraw(true);
        imageView.setMinimumWidth(0);
        imageView.setMaxWidth(0);*/

        return listItemView;
    }

    public WordAdapter(Activity context, List<Word> words, int colorResourcID) {
        super(context, 0, words);
        _colorResourceId = colorResourcID;
    }
}
