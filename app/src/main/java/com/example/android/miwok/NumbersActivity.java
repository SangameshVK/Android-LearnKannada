package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer audioPlayer;
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                audioPlayer.pause();
                //audioPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                audioPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                if (audioPlayer != null) {
                    audioPlayer.release();
                    audioPlayer = null;
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        Word[] wordsArray = { new Word("One","Ondu", R.drawable.number_one, R.raw.number_one),
                new Word("Two", "Eradu", R.drawable.number_two, R.raw.two),
                new Word("Three", "Mooru", R.drawable.number_three, R.raw.three),
                new Word("Four", "Aidu", R.drawable.number_four, R.raw.four),
                new Word("Five" , "Aidu", R.drawable.number_five, R.raw.five),
                new Word("Six", "Aaru", R.drawable.number_six, R.raw.six),
                new Word("Seven", "Elu", R.drawable.number_seven, R.raw.seven),
                new Word("Eight", "Entu", R.drawable.number_eight),
                new Word("Nine", "Ombattu", R.drawable.number_nine),
                new Word("Ten", "Hattu", R.drawable.number_ten)};
        final ArrayList<Word> words = new ArrayList<>();
        for (Word word: wordsArray) {
            words.add(word);
        }
        /*LinearLayout linearLayout = (LinearLayout) findViewById(R.id.numbersRootView);
        for (String word: words) {
            TextView textView = new TextView(this);
            textView.setText(word);
            linearLayout.addView(textView);
        }*/
        final WordAdapter numbersAdapter = new WordAdapter(this, words, R.color.category_numbers);
        final ListView listView = (ListView) findViewById(R.id.numbersList);
        listView.setAdapter(numbersAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word currentWord = words.get(i);
                if (audioPlayer != null) {
                    audioPlayer.release();
                    audioPlayer = null;
                }
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    audioPlayer = MediaPlayer.create(NumbersActivity.this, currentWord.getAudioResourceID());
                    audioPlayer.start();
                    audioPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            audioPlayer.release();
                            audioPlayer = null;
                            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
                        }
                    });
                }
            }
        });
//        GridView gridView = (GridView) findViewById(R.id.numbersList);
//        gridView.setAdapter(numbersAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (audioPlayer != null) {
            audioPlayer.release();
            audioPlayer = null;
        }
        mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
    }
}
