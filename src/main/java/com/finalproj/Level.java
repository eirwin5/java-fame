package com.finalproj;

import java.util.List;

public class Level {
    private int levelNumber;
    private List<Song> songs;

    public Level(int levelNumber, List<Song> songs) {
        this.levelNumber = levelNumber;
        this.songs = songs;
    }

    public Song getNextSong() {
        return this.songs.get(levelNumber % songs.size());
    }

    public Song getSong() {
        return this.songs.get(levelNumber % songs.size());
    }
}
