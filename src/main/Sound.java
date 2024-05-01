package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    Clip clip;
    File[] soundURL = new File[30];

    public Sound() {
        soundURL[SoundType.MUSIC.ordinal()] = new File("res/sound/bgMusic.wav");
        soundURL[SoundType.MINI_GAME_MUSIC.ordinal()] = new File("res/sound/song.wav");
        soundURL[SoundType.COIN.ordinal()] = new File("res/sound/coin.wav");
        soundURL[SoundType.FANFARE.ordinal()] = new File("res/sound/fanfare.wav");
        soundURL[SoundType.GAME_OVER.ordinal()] = new File("res/sound/gameover.wav");
        soundURL[SoundType.SPEAK.ordinal()] = new File("res/sound/speak.wav");
        soundURL[SoundType.MISSED.ordinal()] = new File("res/sound/blocked.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
