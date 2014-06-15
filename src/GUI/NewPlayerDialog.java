/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logic.GameInitialize;
import Logic.InteractionWithDB;
import Logic.Player;
import Sound.SoundPlayer;
import Sound.TalkThread;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * <h2>Class that pops a window to ask for new player name.</h2>
 * Window used to get input from the user asking for new player name.
 *
 * @author Dima Ryskin
 * @author Bogdan Kirylyuk
 */
public class NewPlayerDialog extends javax.swing.JDialog
{

    /**
     * Holds reference to the previous {@linkplain MainMenu} window.
     */
    private java.awt.Frame parent;
    private Thread speakThread;

    /**
     * <h2>The constructor of the class.</h2>Used to create the window and
     * initialize all its components.
     *
     * @param parent receives object of class {@linkplain Frame} that represents
     * the parent window.
     * @param boolean variable that defines if current window will be modal or
     * no.
     */
    public NewPlayerDialog(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        this.parent = parent;
        initComponents();
        this.getContentPane().setBackground(Color.DARK_GRAY);
        speakThread = new Thread(new TalkThread("Please type desired player name."));
        speakThread.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        playerNameText = new javax.swing.JTextField();
        newPlayerDialogText = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        newPlayerDialogText1 = new javax.swing.JLabel();
        newPlayerDialogText2 = new javax.swing.JLabel();
        playerLoginText = new javax.swing.JTextField();
        playerPasswordText = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(GameInitialize.gameVersion);
        setBackground(java.awt.Color.darkGray);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(new ImageIcon(getClass().getResource("fistIcon.png")).getImage()
        );
        setResizable(false);

        playerNameText.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        playerNameText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        playerNameText.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                playerNameTextKeyReleased(evt);
            }
        });

        newPlayerDialogText.setFont(new java.awt.Font("Trajan Pro", 1, 18)); // NOI18N
        newPlayerDialogText.setForeground(new java.awt.Color(255, 255, 255));
        newPlayerDialogText.setText("Enter password");

        okButton.setBackground(java.awt.Color.lightGray);
        okButton.setFont(new java.awt.Font("Trajan Pro", 1, 18)); // NOI18N
        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                okButtonActionPerformed(evt);
            }
        });

        newPlayerDialogText1.setFont(new java.awt.Font("Trajan Pro", 1, 18)); // NOI18N
        newPlayerDialogText1.setForeground(new java.awt.Color(255, 255, 255));
        newPlayerDialogText1.setText("Enter new player name:");

        newPlayerDialogText2.setFont(new java.awt.Font("Trajan Pro", 1, 18)); // NOI18N
        newPlayerDialogText2.setForeground(new java.awt.Color(255, 255, 255));
        newPlayerDialogText2.setText("Enter login");

        playerLoginText.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        playerLoginText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        playerLoginText.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                playerLoginTextKeyReleased(evt);
            }
        });

        playerPasswordText.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(newPlayerDialogText2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(playerNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(newPlayerDialogText1)
                                .addGap(15, 15, 15)))))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(playerPasswordText)
                    .addComponent(playerLoginText)
                    .addComponent(newPlayerDialogText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(86, 86, 86))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(newPlayerDialogText1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(newPlayerDialogText2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(playerLoginText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newPlayerDialogText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerPasswordText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(okButton)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void validatePlayer()
    {
        if (!"".equals(playerNameText.getText()) && playerNameText.getText().length() < 15)
        {
            if (!"".equals(playerLoginText.getText()) && playerLoginText.getText().length() < 12 && playerLoginText.getText().length() > 5)
            {
                if (!"".equals(playerPasswordText.getText()) && playerPasswordText.getText().length() < 12 && playerPasswordText.getText().length() > 5)
                {
                    try
                    {
                        Player newPlayer = new Player(playerNameText.getText());
                        newPlayer.setLoginToDatabase(playerLoginText.getText());

                        new InteractionWithDB().addUser(playerLoginText.getText(), playerPasswordText.getText(), newPlayer);
                        speakThread.stop();
                        (new CharacterFrame(newPlayer)).setVisible(true);
                        parent.dispose();
                        this.dispose();
                        
                        
                    } catch (MySQLIntegrityConstraintViolationException ex)
                    {
                        JOptionPane.showMessageDialog(this, "Login already taken.");
                    } catch (SQLException ex)
                    {
                        Logger.getLogger(NewPlayerDialog.class.getName()).log(Level.SEVERE, null, ex);
                    } 

                } else
                {
                    SoundPlayer.Play("Error");
                    JOptionPane.showMessageDialog(this, "Illegal password! Must be between 5 to 12 characters!");
                }
            } else
            {
                SoundPlayer.Play("Error");
                JOptionPane.showMessageDialog(this, "Illegal login! Must be between 5 to 12 characters!");
            }

        } else
        {
            SoundPlayer.Play("Error");
            JOptionPane.showMessageDialog(this, "Illegal Player Name!");
        }

    }

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_okButtonActionPerformed
    {//GEN-HEADEREND:event_okButtonActionPerformed
        validatePlayer();
    }//GEN-LAST:event_okButtonActionPerformed

    private void playerNameTextKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_playerNameTextKeyReleased
    {//GEN-HEADEREND:event_playerNameTextKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            validatePlayer();
        }
    }//GEN-LAST:event_playerNameTextKeyReleased

    private void playerLoginTextKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_playerLoginTextKeyReleased
    {//GEN-HEADEREND:event_playerLoginTextKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            validatePlayer();
        }
    }//GEN-LAST:event_playerLoginTextKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel newPlayerDialogText;
    private javax.swing.JLabel newPlayerDialogText1;
    private javax.swing.JLabel newPlayerDialogText2;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField playerLoginText;
    private javax.swing.JTextField playerNameText;
    private javax.swing.JPasswordField playerPasswordText;
    // End of variables declaration//GEN-END:variables
}
