/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import Sound.SoundPlayer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

/**
 * Class used as thread to make the delay between throwing hit and receiving back from opponent.
 * @author Dima Ryskin
 * @author Bogdan Kirylyuk.
 */
public final class WaitAndRefresh extends Thread implements Runnable
{
    private int time;
    private String text;
    private JTextPane textPane;
    private JButton attackButton;
    private JProgressBar playerHealthPBar;
    private int playerHealthPBarValue;
    
    public WaitAndRefresh(int time, String text, JTextPane textPane, JButton attackButton, JProgressBar playerHealthPBar, int playerHealthPBarValue)
    {
        this.text = text;
        this.time = time;
        this.textPane = textPane;
        this.attackButton = attackButton;
        this.playerHealthPBar = playerHealthPBar;
        this.playerHealthPBarValue = playerHealthPBarValue;
    }
    public void run() {
        try
        {
            this.sleep(time);
            textPane.setText(textPane.getText() + text);
            textPane.repaint();
            this.playerHealthPBar.setValue(playerHealthPBarValue);
            this.playerHealthPBar.repaint();
            
            
        } catch (InterruptedException ex)
        {
            Logger.getLogger(WaitAndRefresh.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally
        {
            this.interrupt();
        }
    }
    
}
