/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
            File file = new File(getClass().getResource("tracks/" + track + ".mp3").getFile());
            FileInputStream stream = null;
            try
            {
                stream = new FileInputStream(file);
            } catch (FileNotFoundException ex)
            {
                Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
            try
            {
                Player p = new Player(stream);
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
