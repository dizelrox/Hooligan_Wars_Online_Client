/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logic.GameInitialize;
import Logic.InteractionWithDB;
import Logic.InteractionWithGameServer;
import Logic.Player;
import Logic.Weapon;
import Logic.WaitAndRefresh;
import Sound.SoundPlayer;
import Sound.SoundVolume;
import Sound.TalkThread;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * <h2>Class that creates the battle window.</h2>
 * In this class both players are shown.Their items,names,health and options to
 * attack and defend.<br>
 * Also the main game action takes place here.The battle itself.<br>
 * The user can pick attack and defense area,hit the attack button and wait for
 * opponent to respond.
 *
 * @author Dima Ryskin
 * @author Bogdan Kirylyuk
 * @see {@link Player} for more details about Player class.
 * @see {@link CharacterFrame} for more details about CharacterFrame class.
 *
 */
public class BattleForm extends javax.swing.JFrame
{

    /**
     * Holds reference to the player instance of class {@linkplain Player}. Its
     * the user character which created in previous {@linkplain CharacterFrame}
     * window.
     */
    volatile Player player;
    /**
     * Holds reference to the bot instance of class {@linkplain Player} but with
     * different constructor.
     */
    volatile Player bot;

    /**
     * Holds reference to the previous window.
     *
     * @see CharacterFrame
     */
    CharacterFrame charFrame;

    Thread speakThread;

    InteractionWithGameServer instance;

    volatile Boolean turn;

    /**
     * The main constructor of the class that initializes all class properties
     * and creates the window.
     *
     * @param player : {@linkplain Player} holds reference to the player created
     * in {@linkplain CharacterFrame}.
     * @param bot : {@linkplain Player} holds reference to bot passed from
     * {@linkplain CharacterFrame}.
     * @param charFrame : {@linkplain CharacterFrame} holds reference to the
     * previous window.
     */
    public BattleForm(InteractionWithGameServer instance, Player player, Player bot, CharacterFrame charFrame)
    {
        this.instance = instance;
        this.player = player;
        this.bot = bot;
        this.charFrame = charFrame;
        this.getContentPane().setBackground(Color.DARK_GRAY);

        initComponents();

        turn = (Boolean) instance.getObjectFromServer();

        if (!turn)
        {
            waitForHit();
        }

        speakThread = new Thread(new TalkThread("Choose your attack and defence areas."));
        speakThread.start();
    }

    private void waitForHit()
    {
        Thread wait = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                while (player.getDefenceArea() == null)
                {

                }
                instance.sendObjectToServer(player.getDefenceArea());
                bot.setAttackArea((Logic.Type) instance.getObjectFromServer());
                bot.throwHit((Weapon) bot.getItem(Logic.Type.WEAPON), bot.getStrengthFactor(), bot.getAttackArea(), player);
                turn = !turn;
                battleConsole.setText(battleConsole.getText() + "[" + player.getCurrentTimeStamp() + "]" + "You have" + player.getConsoleText());
                battleConsole.repaint();
                playerHealthPBar.setValue(player.getHealth());
                playerHealthPBar.repaint();
                String number = Integer.toString((int) (Math.random() * 7 + 1));
                SoundPlayer.Play("hit" + number);
                if (player.getHealth() <= 0)
                {
                    try
                    {
                        new InteractionWithDB().updatePlayerStats(player, false);
                    } catch (CommunicationsException ex)
                    {
                        Logger.getLogger(BattleForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex)
                    {
                        WinOrLoseDialog endDialog = new WinOrLoseDialog(BattleForm.this, true, "You've lost the battle!", "Error updating score", charFrame);
                        endDialog.setVisible(true);
                        Logger.getLogger(BattleForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    WinOrLoseDialog endDialog = new WinOrLoseDialog(BattleForm.this, true, "You've lost the battle!", "", charFrame);
                    endDialog.setVisible(true);
                }
                nullPlayerAndGuiFields();
                defineAttackButtonAvailability();
            }
        });
        wait.start();
    }

    private void nullPlayerAndGuiFields()
    {
        player.setAttackArea(null);
        player.setDefenceArea(null);
        defenceRadios.clearSelection();
        attackRadios.clearSelection();
    }

    @Override
    public void dispose()
    {
        JOptionPane.showMessageDialog(this, "Cant exit during battle!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void realDispose()
    {
        super.dispose();
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

        defenceRadios = new javax.swing.ButtonGroup();
        attackRadios = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        playerHealthPBar = new javax.swing.JProgressBar();
        defendChest = new javax.swing.JRadioButton();
        defendHead = new javax.swing.JRadioButton();
        defendStomach = new javax.swing.JRadioButton();
        defendLegs = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        botHealthPBar = new javax.swing.JProgressBar();
        jLabel11 = new javax.swing.JLabel();
        attackLegs = new javax.swing.JRadioButton();
        attackChest = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        attackHead = new javax.swing.JRadioButton();
        attackStomach = new javax.swing.JRadioButton();
        botNameLabel = new javax.swing.JLabel();
        playerNameLabel = new javax.swing.JLabel();
        attackButton = new javax.swing.JButton();
        botIcon = new javax.swing.JLabel();
        playerIcon = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        battleConsole = new javax.swing.JTextPane();
        backgroundSoundLabel = new javax.swing.JLabel();
        backgroundSlider = new javax.swing.JSlider();
        effectsSoundLabel = new javax.swing.JLabel();
        soundEffectsSlider = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(GameInitialize.gameVersion);
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(new ImageIcon(getClass().getResource("fistIcon.png")).getImage()
        );
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel2.setIcon(this.charFrame.checkItemIfNull(player, Logic.Type.HEAD));

        jLabel3.setIcon(this.charFrame.checkItemIfNull(player, Logic.Type.CHEST));

        jLabel4.setIcon(this.charFrame.checkItemIfNull(player, Logic.Type.STOMACH));

        jLabel5.setIcon(this.charFrame.checkItemIfNull(player, Logic.Type.LEGS));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        playerHealthPBar.setForeground(new java.awt.Color(255, 0, 0));
        playerHealthPBar.setValue(player.getHealth());

        defendChest.setBackground(java.awt.Color.darkGray);
        defenceRadios.add(defendChest);
        defendChest.setFont(new java.awt.Font("Trajan Pro", 0, 11)); // NOI18N
        defendChest.setForeground(new java.awt.Color(255, 255, 255));
        defendChest.setText("Defend Chest");
        defendChest.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                defendChestActionPerformed(evt);
            }
        });

        defendHead.setBackground(java.awt.Color.darkGray);
        defenceRadios.add(defendHead);
        defendHead.setFont(new java.awt.Font("Trajan Pro", 0, 11)); // NOI18N
        defendHead.setForeground(new java.awt.Color(255, 255, 255));
        defendHead.setText("Defend Head");
        defendHead.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                defendHeadActionPerformed(evt);
            }
        });

        defendStomach.setBackground(java.awt.Color.darkGray);
        defenceRadios.add(defendStomach);
        defendStomach.setFont(new java.awt.Font("Trajan Pro", 0, 11)); // NOI18N
        defendStomach.setForeground(new java.awt.Color(255, 255, 255));
        defendStomach.setText("Defend Stomach");
        defendStomach.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                defendStomachActionPerformed(evt);
            }
        });

        defendLegs.setBackground(java.awt.Color.darkGray);
        defenceRadios.add(defendLegs);
        defendLegs.setFont(new java.awt.Font("Trajan Pro", 0, 11)); // NOI18N
        defendLegs.setForeground(new java.awt.Color(255, 255, 255));
        defendLegs.setText("Defend Legs");
        defendLegs.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                defendLegsActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 0, 0));

        jLabel1.setIcon(this.charFrame.checkItemIfNull(player, Logic.Type.WEAPON));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 0, 0));

        jLabel6.setIcon(this.charFrame.checkItemIfNull(bot, Logic.Type.WEAPON));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        jLabel7.setIcon(this.charFrame.checkItemIfNull(bot, Logic.Type.HEAD));

        jLabel8.setIcon(this.charFrame.checkItemIfNull(bot, Logic.Type.CHEST));

        jLabel9.setIcon(this.charFrame.checkItemIfNull(bot, Logic.Type.STOMACH));

        jLabel10.setIcon(this.charFrame.checkItemIfNull(bot, Logic.Type.LEGS));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botHealthPBar.setForeground(new java.awt.Color(255, 0, 0));
        botHealthPBar.setValue(bot.getHealth());

        jLabel11.setBackground(java.awt.Color.darkGray);
        jLabel11.setFont(new java.awt.Font("Trajan Pro", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Choose Your Defence:");

        attackLegs.setBackground(java.awt.Color.darkGray);
        attackRadios.add(attackLegs);
        attackLegs.setFont(new java.awt.Font("Trajan Pro", 0, 11)); // NOI18N
        attackLegs.setForeground(new java.awt.Color(255, 255, 255));
        attackLegs.setText("Attack Legs");
        attackLegs.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                attackLegsActionPerformed(evt);
            }
        });

        attackChest.setBackground(java.awt.Color.darkGray);
        attackRadios.add(attackChest);
        attackChest.setFont(new java.awt.Font("Trajan Pro", 0, 11)); // NOI18N
        attackChest.setForeground(new java.awt.Color(255, 255, 255));
        attackChest.setText("Attack Chest");
        attackChest.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                attackChestActionPerformed(evt);
            }
        });

        jLabel12.setBackground(java.awt.Color.darkGray);
        jLabel12.setFont(new java.awt.Font("Trajan Pro", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Choose Your Attack:");

        attackHead.setBackground(java.awt.Color.darkGray);
        attackRadios.add(attackHead);
        attackHead.setFont(new java.awt.Font("Trajan Pro", 0, 11)); // NOI18N
        attackHead.setForeground(new java.awt.Color(255, 255, 255));
        attackHead.setText("Attack Head");
        attackHead.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                attackHeadActionPerformed(evt);
            }
        });

        attackStomach.setBackground(java.awt.Color.darkGray);
        attackRadios.add(attackStomach);
        attackStomach.setFont(new java.awt.Font("Trajan Pro", 0, 11)); // NOI18N
        attackStomach.setForeground(new java.awt.Color(255, 255, 255));
        attackStomach.setText("Attack Stomach");
        attackStomach.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                attackStomachActionPerformed(evt);
            }
        });

        botNameLabel.setFont(new java.awt.Font("Trajan Pro", 1, 18)); // NOI18N
        botNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        botNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botNameLabel.setText(bot.getName());
        botNameLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        playerNameLabel.setFont(new java.awt.Font("Trajan Pro", 1, 18)); // NOI18N
        playerNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        playerNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerNameLabel.setText(player.getName());
        playerNameLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        attackButton.setBackground(new java.awt.Color(255, 0, 0));
        attackButton.setFont(new java.awt.Font("Trajan Pro", 1, 30)); // NOI18N
        attackButton.setForeground(new java.awt.Color(240, 240, 240));
        attackButton.setText("ATTACK");
        attackButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                attackButtonActionPerformed(evt);
            }
        });

        botIcon.setIcon(bot.getBattleIcon());

        playerIcon.setIcon(player.getBattleIcon());

        battleConsole.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jScrollPane2.setViewportView(battleConsole);

        backgroundSoundLabel.setFont(new java.awt.Font("Trajan Pro", 0, 11)); // NOI18N
        backgroundSoundLabel.setForeground(new java.awt.Color(255, 255, 255));
        backgroundSoundLabel.setText("Background:");

        backgroundSlider.setBackground(java.awt.Color.darkGray);
        backgroundSlider.setForeground(java.awt.Color.darkGray);
        backgroundSlider.setMaximum(6);
        backgroundSlider.setMinimum(-50);
        backgroundSlider.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                backgroundSliderMouseReleased(evt);
            }
        });

        effectsSoundLabel.setFont(new java.awt.Font("Trajan Pro", 0, 11)); // NOI18N
        effectsSoundLabel.setForeground(new java.awt.Color(255, 255, 255));
        effectsSoundLabel.setText("Effects:");

        soundEffectsSlider.setBackground(java.awt.Color.darkGray);
        soundEffectsSlider.setForeground(java.awt.Color.darkGray);
        soundEffectsSlider.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                soundEffectsSliderMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(playerHealthPBar, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botHealthPBar, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(playerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(backgroundSoundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(backgroundSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(effectsSoundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(soundEffectsSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(playerIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(defendHead)
                                            .addComponent(defendChest)
                                            .addComponent(defendStomach)
                                            .addComponent(defendLegs)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(attackHead)
                                            .addComponent(attackChest)
                                            .addComponent(attackStomach)
                                            .addComponent(attackLegs)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(attackButton)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(botNameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(playerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(backgroundSoundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(backgroundSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(effectsSoundLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(soundEffectsSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(playerHealthPBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botHealthPBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(defendHead)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(defendChest)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(defendStomach)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(defendLegs)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(attackHead)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(attackChest)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(attackStomach)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(attackLegs)
                                .addGap(32, 32, 32)
                                .addComponent(attackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(playerIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(botIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        attackButton.setEnabled(false);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void defendLegsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_defendLegsActionPerformed
    {//GEN-HEADEREND:event_defendLegsActionPerformed
        player.setDefenceArea(Logic.Type.LEGS);
        defineAttackButtonAvailability();
    }//GEN-LAST:event_defendLegsActionPerformed

    private void attackLegsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_attackLegsActionPerformed
    {//GEN-HEADEREND:event_attackLegsActionPerformed
        player.setAttackArea(Logic.Type.LEGS);
        defineAttackButtonAvailability();
    }//GEN-LAST:event_attackLegsActionPerformed


    private void attackButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_attackButtonActionPerformed
    {//GEN-HEADEREND:event_attackButtonActionPerformed
        Thread attackThread = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                attackButton.setEnabled(false);
                turn = !turn;
                instance.sendObjectToServer(player.getAttackArea());
                bot.setDefenceArea((Logic.Type) instance.getObjectFromServer());
                player.throwHit((Weapon) player.getItem(Logic.Type.WEAPON), player.getStrengthFactor(), player.getAttackArea(), bot);
                battleConsole.setText(battleConsole.getText() + "[" + player.getCurrentTimeStamp() + "]" + bot.getName() + " has" + bot.getConsoleText());
                battleConsole.repaint();
                String number = Integer.toString((int) (Math.random() * 7 + 1));
                SoundPlayer.Play("hit" + number);
                botHealthPBar.setValue(bot.getHealth());
                botHealthPBar.repaint();
                if (bot.getHealth() <= 0)
                {
                    try
                    {
                        new InteractionWithDB().updatePlayerStats(player, true);
                    } catch (CommunicationsException ex)
                    {
                        Logger.getLogger(BattleForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex)
                    {
                        WinOrLoseDialog endDialog = new WinOrLoseDialog(BattleForm.this, true, "Congratulations!\nYou're victorious!", charFrame.game.getUnlockedItems() + "\nError updating score", charFrame);
                        endDialog.setVisible(true);
                        Logger.getLogger(BattleForm.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    WinOrLoseDialog endDialog = new WinOrLoseDialog(BattleForm.this, true, "Congratulations!\nYou're victorious!", charFrame.game.getUnlockedItems(), charFrame);
                    endDialog.setVisible(true);
                }
                nullPlayerAndGuiFields();
                defineAttackButtonAvailability();
                waitForHit();
            }
        });
        attackThread.start();

    }//GEN-LAST:event_attackButtonActionPerformed

    private void defineAttackButtonAvailability()
    {
        if (player.getAttackArea() != null && player.getDefenceArea() != null && turn)
        {
            attackButton.setEnabled(true);
        } else
        {
            attackButton.setEnabled(false);
        }
    }

    private void defendHeadActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_defendHeadActionPerformed
    {//GEN-HEADEREND:event_defendHeadActionPerformed
        player.setDefenceArea(Logic.Type.HEAD);
        defineAttackButtonAvailability();
    }//GEN-LAST:event_defendHeadActionPerformed

    private void defendChestActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_defendChestActionPerformed
    {//GEN-HEADEREND:event_defendChestActionPerformed
        player.setDefenceArea(Logic.Type.CHEST);
        defineAttackButtonAvailability();
    }//GEN-LAST:event_defendChestActionPerformed

    private void defendStomachActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_defendStomachActionPerformed
    {//GEN-HEADEREND:event_defendStomachActionPerformed
        player.setDefenceArea(Logic.Type.STOMACH);
        defineAttackButtonAvailability();
    }//GEN-LAST:event_defendStomachActionPerformed

    private void attackHeadActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_attackHeadActionPerformed
    {//GEN-HEADEREND:event_attackHeadActionPerformed
        player.setAttackArea(Logic.Type.HEAD);
        defineAttackButtonAvailability();
    }//GEN-LAST:event_attackHeadActionPerformed

    private void attackChestActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_attackChestActionPerformed
    {//GEN-HEADEREND:event_attackChestActionPerformed
        player.setAttackArea(Logic.Type.CHEST);
        defineAttackButtonAvailability();
    }//GEN-LAST:event_attackChestActionPerformed

    private void attackStomachActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_attackStomachActionPerformed
    {//GEN-HEADEREND:event_attackStomachActionPerformed
        player.setAttackArea(Logic.Type.STOMACH);
        defineAttackButtonAvailability();
    }//GEN-LAST:event_attackStomachActionPerformed

    private void backgroundSliderMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_backgroundSliderMouseReleased
    {//GEN-HEADEREND:event_backgroundSliderMouseReleased
        FloatControl control = (FloatControl) MainMenu.b.sourceLine.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(backgroundSlider.getValue());
    }//GEN-LAST:event_backgroundSliderMouseReleased

    private void soundEffectsSliderMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_soundEffectsSliderMouseReleased
    {//GEN-HEADEREND:event_soundEffectsSliderMouseReleased
        (new SoundVolume()).setVolume((float) this.soundEffectsSlider.getValue() / 100);
        SoundPlayer.Play("Error");
    }//GEN-LAST:event_soundEffectsSliderMouseReleased

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton attackButton;
    private javax.swing.JRadioButton attackChest;
    private javax.swing.JRadioButton attackHead;
    private javax.swing.JRadioButton attackLegs;
    private javax.swing.ButtonGroup attackRadios;
    private javax.swing.JRadioButton attackStomach;
    private javax.swing.JSlider backgroundSlider;
    private javax.swing.JLabel backgroundSoundLabel;
    private javax.swing.JTextPane battleConsole;
    private javax.swing.JProgressBar botHealthPBar;
    private javax.swing.JLabel botIcon;
    private javax.swing.JLabel botNameLabel;
    private javax.swing.ButtonGroup defenceRadios;
    private javax.swing.JRadioButton defendChest;
    private javax.swing.JRadioButton defendHead;
    private javax.swing.JRadioButton defendLegs;
    private javax.swing.JRadioButton defendStomach;
    private javax.swing.JLabel effectsSoundLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JProgressBar playerHealthPBar;
    private javax.swing.JLabel playerIcon;
    private javax.swing.JLabel playerNameLabel;
    private javax.swing.JSlider soundEffectsSlider;
    // End of variables declaration//GEN-END:variables
}
