package com.finalproj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Song {
    private String songTitle;
    private String songLink;

    public Song(String songTitle, String songLink) {
        this.songTitle = songTitle;
        this.songLink = songLink;
    }

    public String getTitle() {
        return songTitle;
    }

    public String getLink() {
        return songLink;
    }

    public String toString() {
        return "Song [ songTitle: " + songTitle + ", songLink: " + songLink + " ]";
    }

    public static List<Song> readSongsFromJson() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("songs/songs.json"));
            Gson gson = new Gson();
            List<Song> songs = gson.fromJson(reader, new TypeToken<List<Song>>() {
            }.getType());
            reader.close();
            return songs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
