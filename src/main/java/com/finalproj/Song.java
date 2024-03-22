package com.finalproj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.media.MediaPlayer;
import com.google.gson.reflect.TypeToken;

public class Song {
    private String songTitle;
    private String songLink;
    private int songDuration;

    public Song() {
    }

    public String getTitle() {
        return songTitle;
    }

    public void setTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getLink() {
        return songLink;
    }

    public void setLink(String songLink) {
        this.songLink = songLink;
    }

    public int getDuration() {
        return songDuration;
    }

    public void setDuration(int songDuration) {
        this.songDuration = songDuration;
    }

    public String toString() {
        return "Song [ songTitle: " + songTitle + ", songLink: " + songLink + ", songDuration: " + songDuration + " ]";
    }

    public static List<Song> readSongsFromJson() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("songs.json"));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String jsonString = stringBuilder.toString();

            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();

            Gson gson = builder.create();
            List<Song> songs = gson.fromJson(jsonString, new TypeToken<List<Song>>() {
            }.getType());

            reader.close();

            return songs;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void playMusic(MediaPlayer mediaPlayer) {
        try {
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopMusic(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
