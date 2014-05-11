/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import us.monoid.web.BinaryResource;
import us.monoid.web.Resty;

/**
 *
 * @author Dizel
 */
public class TalkThread implements Runnable
{
    private String textToSpeak;
    private static final String BASE_URL =
			"http://translate.google.com/translate_tts?ie={0}&q={1}&tl={2}&prev=input";
    public TalkThread(String textToSpeak)
    {
        this.textToSpeak = textToSpeak;
    }
    @Override
    public void run()
    {
        String encoding = "UTF-8"; // create a String variable to hold the chosen encoding type
		
		// create a String variable to hold the chosen language of the sound and text
		String locale = "en";
		
		try {
			// create a temporary file which will hold the sound received from google
			File file = new File("speech.mp3");
			
			// create a String variable to hold the encoded String argument received by speak(String) 
			String sentence = URLEncoder.encode(textToSpeak, encoding);
			
			// create the formated String from which you create the URI
			String urlString = MessageFormat.format(BASE_URL, encoding, sentence, locale);
			
			// create the binary resource object that will hold the response from google translate
			BinaryResource binaryResource = new Resty().bytes(new URI(urlString));
			
			// save the sound to the file
			binaryResource.save(file);
			
			// create a file input stream from the file
			FileInputStream stream = new FileInputStream(file);
			
			// create an instance of a mp3 player which will play the stream created earlier
			Player player = new Player(stream);
                        
			
			// get the player to play the stream
			// this method will wait for the player to finish before moving to the next line
			
                        player.play();
			
			// close the player after it has finished playing the stream
			player.close();
			
			// delete the temporary file before exit
			file.delete();
		}
		
		catch (NullPointerException e){
			e.printStackTrace();
		}
		
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		catch (JavaLayerException e) {
			e.printStackTrace();
		}
		}
    }
    
