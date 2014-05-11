package Sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Port;
import javax.sound.sampled.Port.Info;

public class SoundVolume
{

    // variables needed
    Info source = Port.Info.SPEAKER;

    // source = Port.Info.LINE_OUT;
    // source = Port.Info.HEADPHONE; // *** how do I change source midway? ***
    FloatControl volumeControl;

    // this is the constructor for volume
    public SoundVolume()
    {

        if (AudioSystem.isLineSupported(source))
        {
            try
            {
                Port outline = (Port) AudioSystem.getLine(source);
                outline.open();
                volumeControl = (FloatControl) outline
                        .getControl(FloatControl.Type.VOLUME);
            } catch (LineUnavailableException ex)
            {
                System.err.println("Error: source not supported");
                ex.printStackTrace();
            }
        }

    }

    public void setVolume(float newVolume)
    {
        volumeControl.setValue(newVolume);
    }

}
