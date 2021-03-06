/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Logic.GameInitialize;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * Class that pops win or loose dialog at the end of the battle.
 * @author Dima Ryskin
 * @author Bogdan Kirylyuk
 */
public class WinOrLoseDialog extends javax.swing.JDialog
{
	/**
	 * Holds reference to the previous (battle) window.
	 */
    private BattleForm parent;
    /**
     * Holds reference to the next window.Brings you back to character frame.
     */
    CharacterFrame store;
    /**
     * Text that displays the unlocked items if any.
     */
    private String unLockedItemsText;
    /**
     * The constructor of the class that initializes all its properties and displays the window.
     * @param parent receives instance of {@linkplain Frame} that refers to this window parent.
     * @param modal is a boolean variable that defines whether this window will be modal or not.
     * @param text receives String that will be displayed at the header of the window.
     * @param unLockedItemsText receives string describing the unlocked items if any.
     * @param store receives instance of {@linkplain CharacterFrame} that refers to the next window.
     */
    public WinOrLoseDialog(BattleForm parent, boolean modal, String text, String unLockedItemsText, CharacterFrame store)
    {
        super(parent, modal);
        this.unLockedItemsText = unLockedItemsText;
        initComponents();
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.parent = parent;
        this.store = store;
        winOrLoseText.setText(text);
        winOrLoseText.repaint();
       
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        winOrLoseText = new javax.swing.JLabel();
        backToStoreButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(GameInitialize.gameVersion);
        setAlwaysOnTop(true);
        setIconImage(new ImageIcon(getClass().getResource("fistIcon.png")).getImage()
        );
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        winOrLoseText.setBackground(java.awt.Color.darkGray);
        winOrLoseText.setFont(new java.awt.Font("Trajan Pro", 1, 14)); // NOI18N
        winOrLoseText.setForeground(new java.awt.Color(255, 255, 255));
        winOrLoseText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        winOrLoseText.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        backToStoreButton.setBackground(java.awt.Color.lightGray);
        backToStoreButton.setFont(new java.awt.Font("Trajan Pro", 1, 14)); // NOI18N
        backToStoreButton.setText("Back to Store");
        backToStoreButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                backToStoreButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(java.awt.Color.darkGray);
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        jTextPane1.setBackground(java.awt.Color.darkGray);
        jTextPane1.setFont(new java.awt.Font("Trajan Pro", 1, 12)); // NOI18N
        jTextPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTextPane1.setText(this.unLockedItemsText);
        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 124, Short.MAX_VALUE)
                .addComponent(backToStoreButton, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(winOrLoseText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(winOrLoseText, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backToStoreButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleParent(this);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backToStoreButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_backToStoreButtonActionPerformed
    {//GEN-HEADEREND:event_backToStoreButtonActionPerformed
        parent.realDispose();
        
        store.setVisible(true);
        store.updateShopLabels();
        this.dispose();
    }//GEN-LAST:event_backToStoreButtonActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backToStoreButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel winOrLoseText;
    // End of variables declaration//GEN-END:variables
}
