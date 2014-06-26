/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.JavaSoundAudioDevice;
import javazoom.jl.player.Player;

/**
 *
 * @author Dizel
 */
public class SoundPlayer extends JavaSoundAudioDevice
{

    private static class Play implements Runnable
    {

        private String track;

        public Play(String track)
        {
            this.track = track;
        }

        public void run()
        {
            try
            {
                
            InputStream dir_url = ClassLoader.getSystemResourceAsStream("Sound/tracks/" + track + ".mp3");
                
                Player p = new Player(dir_url);
                p.play();
                p.close();
            } catch (JavaLayerException ex)
            {
                Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public static void Play(String track)
    {
        Thread backgroundThread = new Thread(new SoundPlayer.Play(track));
        backgroundThread.start();
    }
}
