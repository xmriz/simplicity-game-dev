package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[25];
    FloatControl fc;
    int volumeScale = 1;
    float volume;

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/loadingscreen.wav");
        soundURL[1] = getClass().getResource("/sound/playstate.wav");
        soundURL[2] = getClass().getResource("/sound/door.wav");
        soundURL[3] = getClass().getResource("/sound/cursor.wav");
        soundURL[4] = getClass().getResource("/sound/dialog.wav");
        soundURL[5] = getClass().getResource("/sound/lagu1.wav");
        soundURL[6] = getClass().getResource("/sound/lagu2.wav");
        soundURL[7] = getClass().getResource("/sound/lagu3.wav");
        soundURL[8] = getClass().getResource("/sound/lagu4.wav");
        soundURL[9] = getClass().getResource("/sound/lagu5.wav");
        soundURL[10] = getClass().getResource("/sound/lagu6.wav");
        soundURL[11] = getClass().getResource("/sound/boker.wav");
        soundURL[12] = getClass().getResource("/sound/tidur.wav");
        soundURL[13] = getClass().getResource("/sound/nonton.wav");
        soundURL[14] = getClass().getResource("/sound/mandi.wav");
        soundURL[15] = getClass().getResource("/sound/makan.wav");
        soundURL[16] = getClass().getResource("/sound/shalat.wav");
        soundURL[17] = getClass().getResource("/sound/bacabuku.wav");
        soundURL[18] = getClass().getResource("/sound/siramtanaman.wav");
        soundURL[19] = getClass().getResource("/sound/maingame.wav");
        soundURL[20] = getClass().getResource("/sound/kerja.wav");
        soundURL[21] = getClass().getResource("/sound/masak.wav");
        soundURL[22] = getClass().getResource("/sound/olahraga.wav");
        soundURL[23] = getClass().getResource("/sound/credits.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch (Exception e) {

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

    public void setVolume(int volume) {
        this.volumeScale = volume;
    }

    public void checkVolume() {
        switch (volumeScale) {
            case 0:
                volume = -80f;
                break;
            case 1:
                volume = -20f;
                break;
            case 2:
                volume = -12f;
                break;
            case 3:
                volume = -5f;
                break;
            case 4:
                volume = 1f;
                break;
            case 5:
                volume = 6f;
                break;
        }
        fc.setValue(volume);
    }
}
