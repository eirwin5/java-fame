package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    Clip clip;
    File[] soundURL = new File[30];

    public Sound() {
        soundURL[SoundType.MUSIC.ordinal()] = new File("res/sound/BlueBoyAdventure.wav");
        soundURL[SoundType.COIN.ordinal()] = new File("res/sound/coin.wav");
        soundURL[SoundType.UNLOCK.ordinal()] = new File("res/sound/unlock.wav");
        soundURL[SoundType.FANFARE.ordinal()] = new File("res/sound/fanfare.wav");
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
