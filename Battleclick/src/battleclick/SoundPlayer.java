package net.panda.ActionConsole.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/*
 * @author Yarden zamir
 */
public class SoundPlayer {

    public static void loopSound(File sound) {
        loop l = new loop(sound);
        l.start();
    }

    public static void playSound(File sound) {
        try {
            java.io.InputStream in = new FileInputStream(sound);

            AudioStream audioStream = new AudioStream(in);
            AudioPlayer.player.start(audioStream);
        } catch (IOException eX) {
            System.err.println(eX);
        }
    }
}

class loop extends Thread {

    static java.io.InputStream in;
    static AudioStream audioStream;
    public File FS;

    public loop(File sound) {
        try {
            FS = sound;
            in = new FileInputStream(sound);
            audioStream = new AudioStream(in);
            AudioPlayer.player.start(audioStream);

        } catch (IOException eX) {
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (audioStream.available() <= 0) {
                    try {
                        in = new FileInputStream(FS);
                        audioStream = new AudioStream(in);
                        audioStream.mark(audioStream.available() + 1);
                        AudioPlayer.player.start(audioStream);
                    } catch (IOException eX) {
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(loop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
