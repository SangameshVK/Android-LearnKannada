package com.example.android.miwok;

public class Word {
    private String kannadaWord;
    private String englishWord;
    private int imageResoucreID;
    private int audioResourceID;
    private static final int NO_IMAGE_PROVIDED = -1;
    public Word(String englishWord, String kannadaWord) {
        this(englishWord, kannadaWord, NO_IMAGE_PROVIDED, R.raw.one);
    }
    public Word(String englishWord, String kannadaWord, int imageResoucreID) {
        this(englishWord, kannadaWord, imageResoucreID, R.raw.one);
    }
    public Word(String englishWord, String kannadaWord, int imageResoucreID, int audioResourceID){
        this.kannadaWord = kannadaWord;
        this.englishWord = englishWord;
        this.imageResoucreID = imageResoucreID;
        this.audioResourceID = audioResourceID;
    }

    public String getKannadaWord() {
        return kannadaWord;
    }
    public String getEnglishWord() {
        return englishWord;
    }
    public int getImageResoucreID() {
        return imageResoucreID;
    }
    public int getAudioResourceID() { return audioResourceID; }

    public boolean hasImage() {
        return imageResoucreID != NO_IMAGE_PROVIDED;
    }
}
