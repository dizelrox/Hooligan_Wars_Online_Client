/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logic.Armor;
import Logic.GameInitialize; //import game logics
import Logic.InteractionWithDB;
import Logic.InteractionWithGameServer;
import Logic.Item;
import Logic.Player;
import Logic.Weapon;
import Sound.SoundPlayer;
import Sound.SoundVolume;
import Sound.TalkThread;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.FloatControl;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * <h2>This class represents the character creation frame.</h2>
 * In this window all character statistics,current wearing items and available
 * items in shop are shown.<br>
 * Here you can wear different objects and proceed to battle!
 *
 * @author Dima Ryskin
 * @author Bogdan Kirylyuk
 * @see {@link Armor} for more details about Armor objects.
 * @see {@link NewPlayerDialog} for more details about NewPlayerDialog class.
 * @see {@link Player} for more details about Player class.
 * @see {@link BattleForm} for more details about BattleForm class window.
 * @see {@link GameInitialize} for more details about GameInitialize object.
 */
public class CharacterFrame extends javax.swing.JFrame
{

    /**
     * Variable armArray that holds reference to all available
     * {@linkplain Armor}s in the game.
     */
    private Armor[] armArray;
    /**
     * Variable wepArray that holds reference to all {@linkplain Weapon}s in the
     * game.
     */
    private Weapon[] wepArray;
    /**
     * Variable player that holds instance of the current created
     * {@linkplain Player}.
     */
    public Player player;
    /**
     * Variable defaultIcon holds the default icon used when player is wearing
     * nothing on specific body part.
     */
    public Icon defaultIcon = new ImageIcon(getClass().getResource("default_big_image.jpg"));
    /**
     * Holds reference to the next window {@linkplain BattleForm} that will
     * create the battle window.
     */
//    private BattleForm battleForm;
    /**
     * Holds instance of {@linkplain GameInitialize} that instantiates the
     * objects necessary for the game.<br>
     * See class API for more details.
     */
    public GameInitialize game;

    private Thread speakThread;

    private boolean isOnline = true;

    /**
     * The constructor of the class that initializes all window properties.
     *
     * @param thisCreator receives as parameter instance of
     * {@linkplain MainMenu} that refers to the parent if thus window.
     */
    public CharacterFrame(Player player)
    {

        this.game = new GameInitialize(player.getCurrentArmorsArray(), player.getCurrentWeaponsArray());

        this.armArray = game.getArmor();
        this.wepArray = game.getWeapon();

        this.player = player;

        updateStockItemsInUseByPlayer();

        this.getContentPane().setBackground(Color.DARK_GRAY);
        initComponents();
        updateShopLabels();
        speakThread = new Thread(new TalkThread("Welcome " + player.getName() + ". Pick items wisely. Your enemy will show no mercy!"));
        speakThread.start();
    }

    private void updateStockItemsInUseByPlayer()
    {
        for (int i = 0; i < this.armArray.length; i++)
        {
            if ((player.getItem(Logic.Type.HEAD) != null && armArray[i].getName().equals(player.getItem(Logic.Type.HEAD).getName()))
                    || (player.getItem(Logic.Type.CHEST) != null && armArray[i].getName().equals(player.getItem(Logic.Type.CHEST).getName()))
                    || (player.getItem(Logic.Type.STOMACH) != null && armArray[i].getName().equals(player.getItem(Logic.Type.STOMACH).getName()))
                    || (player.getItem(Logic.Type.LEGS) != null && armArray[i].getName().equals(player.getItem(Logic.Type.LEGS).getName())))
            {
                player.setItem(armArray[i]);
                armArray[i].inUse = true;
            }
        }

        for (int i = 0; i < this.wepArray.length; i++)
        {
            if (player.getItem(Logic.Type.WEAPON) != null && wepArray[i].getName().equals(player.getItem(Logic.Type.WEAPON).getName()))
            {
                player.setItem(wepArray[i]);
                wepArray[i].inUse = true;
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        panelActiveDefence = new javax.swing.JPanel();
        defenceHead = new javax.swing.JLabel();
        defenceChest = new javax.swing.JLabel();
        defenceStomach = new javax.swing.JLabel();
        defenceLegs = new javax.swing.JLabel();
        panelShop = new javax.swing.JPanel();
        armorLabel1 = new javax.swing.JLabel();
        armorLabel2 = new javax.swing.JLabel();
        armorLabel3 = new javax.swing.JLabel();
        armorLabel4 = new javax.swing.JLabel();
        armorLabel5 = new javax.swing.JLabel();
        armorLabel6 = new javax.swing.JLabel();
        armorLabel7 = new javax.swing.JLabel();
        armorLabel8 = new javax.swing.JLabel();
        armorLabel9 = new javax.swing.JLabel();
        armorLabel10 = new javax.swing.JLabel();
        armorLabel11 = new javax.swing.JLabel();
        armorLabel12 = new javax.swing.JLabel();
        weaponLabel1 = new javax.swing.JLabel();
        weaponLabel2 = new javax.swing.JLabel();
        weaponLabel3 = new javax.swing.JLabel();
        weaponLabel4 = new javax.swing.JLabel();
        weaponLabel5 = new javax.swing.JLabel();
        weaponLabel6 = new javax.swing.JLabel();
        weaponLabel7 = new javax.swing.JLabel();
        weaponLabel8 = new javax.swing.JLabel();
        startBattleButton = new javax.swing.JButton();
        panelActiveWeaponContainer = new javax.swing.JPanel();
        weaponActive = new javax.swing.JLabel();
        panelStats = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        statsTextArea = new javax.swing.JTextArea();
        playerIconLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        backgroundSoundLabel = new javax.swing.JLabel();
        soundEffectsSlider = new javax.swing.JSlider();
        effectsSoundLabel = new javax.swing.JLabel();
        backgroundSlider = new javax.swing.JSlider();
        highScoreButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(GameInitialize.gameVersion);
        setIconImage(new ImageIcon(getClass().getResource("fistIcon.png")).getImage()
        );
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowActivated(java.awt.event.WindowEvent evt)
            {
                formWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt)
            {
                formWindowClosed(evt);
            }
        });

        panelActiveDefence.setBackground(new java.awt.Color(204, 0, 0));
        panelActiveDefence.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        defenceHead.setIcon(checkItemIfNull(player, Logic.Type.HEAD)
        );
        defenceHead.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        defenceHead.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                defenceHeadMouseClicked(evt);
            }
        });

        defenceChest.setIcon(checkItemIfNull(player, Logic.Type.CHEST)
        );
        defenceChest.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        defenceChest.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                defenceChestMouseClicked(evt);
            }
        });

        defenceStomach.setIcon(checkItemIfNull(player, Logic.Type.STOMACH)
        );
        defenceStomach.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        defenceStomach.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                defenceStomachMouseClicked(evt);
            }
        });

        defenceLegs.setIcon(checkItemIfNull(player, Logic.Type.LEGS)
        );
        defenceLegs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        defenceLegs.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                defenceLegsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelActiveDefenceLayout = new javax.swing.GroupLayout(panelActiveDefence);
        panelActiveDefence.setLayout(panelActiveDefenceLayout);
        panelActiveDefenceLayout.setHorizontalGroup(
            panelActiveDefenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActiveDefenceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelActiveDefenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(defenceStomach, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(defenceChest, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(defenceLegs, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelActiveDefenceLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(defenceHead, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelActiveDefenceLayout.setVerticalGroup(
            panelActiveDefenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActiveDefenceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(defenceHead, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(defenceChest, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(defenceStomach, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(defenceLegs, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelShop.setBackground(new java.awt.Color(182, 180, 171));
        panelShop.setPreferredSize(new java.awt.Dimension(570, 114));
        panelShop.setVerifyInputWhenFocusTarget(false);

        armorLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        armorLabel1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                armorLabel1MouseClicked(evt);
            }
        });

        armorLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        armorLabel2.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                armorLabel2MouseClicked(evt);
            }
        });

        armorLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        armorLabel3.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                armorLabel3MouseClicked(evt);
            }
        });

        armorLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        armorLabel4.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                armorLabel4MouseClicked(evt);
            }
        });

        armorLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        armorLabel5.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                armorLabel5MouseClicked(evt);
            }
        });

        armorLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        armorLabel6.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                armorLabel6MouseClicked(evt);
            }
        });

        armorLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        armorLabel7.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                armorLabel7MouseClicked(evt);
            }
        });

        armorLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        armorLabel8.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                armorLabel8MouseClicked(evt);
            }
        });

        armorLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        armorLabel9.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                armorLabel9MouseClicked(evt);
            }
        });

        armorLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        armorLabel10.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                armorLabel10MouseClicked(evt);
            }
        });

        armorLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        armorLabel11.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                armorLabel11MouseClicked(evt);
            }
        });

        armorLabel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        armorLabel12.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                armorLabel12MouseClicked(evt);
            }
        });

        weaponLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        weaponLabel1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                weaponLabel1MouseClicked(evt);
            }
        });

        weaponLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        weaponLabel2.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                weaponLabel2MouseClicked(evt);
            }
        });

        weaponLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        weaponLabel3.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                weaponLabel3MouseClicked(evt);
            }
        });

        weaponLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        weaponLabel4.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                weaponLabel4MouseClicked(evt);
            }
        });

        weaponLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        weaponLabel5.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                weaponLabel5MouseClicked(evt);
            }
        });

        weaponLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        weaponLabel6.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                weaponLabel6MouseClicked(evt);
            }
        });

        weaponLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        weaponLabel7.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                weaponLabel7MouseClicked(evt);
            }
        });

        weaponLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        weaponLabel8.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                weaponLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelShopLayout = new javax.swing.GroupLayout(panelShop);
        panelShop.setLayout(panelShopLayout);
        panelShopLayout.setHorizontalGroup(
            panelShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShopLayout.createSequentialGroup()
                        .addComponent(armorLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(armorLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelShopLayout.createSequentialGroup()
                        .addComponent(armorLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(armorLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShopLayout.createSequentialGroup()
                        .addComponent(armorLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(armorLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelShopLayout.createSequentialGroup()
                        .addComponent(armorLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(armorLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(panelShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShopLayout.createSequentialGroup()
                        .addComponent(armorLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(armorLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelShopLayout.createSequentialGroup()
                        .addComponent(armorLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(armorLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addGroup(panelShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelShopLayout.createSequentialGroup()
                        .addComponent(weaponLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(weaponLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(weaponLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(weaponLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelShopLayout.createSequentialGroup()
                        .addComponent(weaponLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(weaponLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(weaponLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(weaponLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelShopLayout.setVerticalGroup(
            panelShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelShopLayout.createSequentialGroup()
                            .addGroup(panelShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(armorLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(armorLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(armorLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(armorLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelShopLayout.createSequentialGroup()
                            .addGroup(panelShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(armorLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(armorLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(armorLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(armorLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelShopLayout.createSequentialGroup()
                        .addGroup(panelShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(armorLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(armorLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(weaponLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(weaponLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(weaponLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(weaponLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(weaponLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(armorLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(armorLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(weaponLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(weaponLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(weaponLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        startBattleButton.setBackground(java.awt.Color.lightGray);
        startBattleButton.setFont(new java.awt.Font("Trajan Pro", 1, 24)); // NOI18N
        startBattleButton.setText("Start Battle!");
        startBattleButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                startBattleButtonActionPerformed(evt);
            }
        });

        panelActiveWeaponContainer.setBackground(new java.awt.Color(204, 0, 0));
        panelActiveWeaponContainer.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        weaponActive.setIcon(checkItemIfNull(player, Logic.Type.WEAPON));
        weaponActive.setToolTipText("");
        weaponActive.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        weaponActive.setPreferredSize(new java.awt.Dimension(113, 113));
        weaponActive.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                weaponActiveMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelActiveWeaponContainerLayout = new javax.swing.GroupLayout(panelActiveWeaponContainer);
        panelActiveWeaponContainer.setLayout(panelActiveWeaponContainerLayout);
        panelActiveWeaponContainerLayout.setHorizontalGroup(
            panelActiveWeaponContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelActiveWeaponContainerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(weaponActive, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelActiveWeaponContainerLayout.setVerticalGroup(
            panelActiveWeaponContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActiveWeaponContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(weaponActive, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelStats.setBackground(new java.awt.Color(70, 91, 135));
        panelStats.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        panelStats.setForeground(new java.awt.Color(255, 255, 255));
        panelStats.setToolTipText("");

        statsTextArea.setEditable(false);
        statsTextArea.setColumns(8);
        statsTextArea.setFont(new java.awt.Font("Trajan Pro", 1, 12)); // NOI18N
        statsTextArea.setRows(15);
        statsTextArea.setWrapStyleWord(true);
        statsTextArea.setOpaque(false);
        jScrollPane1.setViewportView(statsTextArea);

        javax.swing.GroupLayout panelStatsLayout = new javax.swing.GroupLayout(panelStats);
        panelStats.setLayout(panelStatsLayout);
        panelStatsLayout.setHorizontalGroup(
            panelStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStatsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelStatsLayout.setVerticalGroup(
            panelStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStatsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
        );

        playerIconLabel.setIcon(player.getPlayerIcon());

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Trajan Pro", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Save Now!");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        backgroundSoundLabel.setFont(new java.awt.Font("Trajan Pro", 0, 11)); // NOI18N
        backgroundSoundLabel.setForeground(new java.awt.Color(255, 255, 255));
        backgroundSoundLabel.setText("Background:");

        soundEffectsSlider.setBackground(java.awt.Color.darkGray);
        soundEffectsSlider.setForeground(java.awt.Color.darkGray);
        soundEffectsSlider.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                soundEffectsSliderMouseReleased(evt);
            }
        });

        effectsSoundLabel.setFont(new java.awt.Font("Trajan Pro", 0, 11)); // NOI18N
        effectsSoundLabel.setForeground(new java.awt.Color(255, 255, 255));
        effectsSoundLabel.setText("Effects:");

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

        highScoreButton.setBackground(java.awt.Color.black);
        highScoreButton.setFont(new java.awt.Font("Trajan Pro", 0, 11)); // NOI18N
        highScoreButton.setForeground(java.awt.Color.white);
        highScoreButton.setText("High-Scores");
        highScoreButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                highScoreButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelShop, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(backgroundSoundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(backgroundSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(effectsSoundLabel)
                                .addGap(18, 18, 18)
                                .addComponent(soundEffectsSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(panelActiveDefence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(playerIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(startBattleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelActiveWeaponContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(highScoreButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelStats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(backgroundSoundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(effectsSoundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(backgroundSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(soundEffectsSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(highScoreButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelActiveWeaponContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelStats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelActiveDefence, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(playerIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(startBattleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelShop, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void startBattleButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_startBattleButtonActionPerformed
    {//GEN-HEADEREND:event_startBattleButtonActionPerformed
        if (isOnline)
        {
            InteractionWithGameServer instance;

            try
            {
            instance = new InteractionWithGameServer();
            Integer status = (Integer) instance.getObjectFromServer();

            if (status.intValue() == 1)
            {
                System.out.println("starting game");
                player.setHealth(100);
                player.setDefenceArea(null);
                player.setAttackArea(null);
                instance.sendCurrntPlayer(player);
                Player opponent = (Player) instance.getObjectFromServer();
                System.out.println(opponent.getName());
                BattleForm battleForm = new BattleForm(instance, player, opponent, this);
                speakThread.stop();
                battleForm.setVisible(true);
                this.setVisible(false);
            }
            }
            
            catch(IOException ex)
            {
            JOptionPane.showMessageDialog(null, "Server is not responing please try again later", "Connection Error", JOptionPane.ERROR_MESSAGE);
            }
            
            

        } else
        {
            player.setHealth(100);
            BattleForm battleForm = new BattleForm(null, player, game.getNewBot(), this);
            speakThread.stop();
            battleForm.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_startBattleButtonActionPerformed
    /**
     * This method is called when the close button is pressed. After pressing
     * the button {@linkplain ExitShopDialog} window will appear.
     */
    public void dispose()
    {
        ExitShopDialog confirm = new ExitShopDialog(this, true);
    }

    /**
     * Original dispose method but with other name.
     */
    public void disposeConfirmed()
    {
        speakThread.stop();
        super.dispose();
    }

    private void formWindowClosed(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosed
    {//GEN-HEADEREND:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed
    private void formWindowActivated(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowActivated
    {//GEN-HEADEREND:event_formWindowActivated
        setLocationToMiddle();
    }//GEN-LAST:event_formWindowActivated

    private void defenceHeadMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_defenceHeadMouseClicked
    {//GEN-HEADEREND:event_defenceHeadMouseClicked
        changeActiveItem(defenceHead, Logic.Type.HEAD);

    }//GEN-LAST:event_defenceHeadMouseClicked

    private void checkItemDressPlayer(JLabel label, Item thisItem)
    {
        if (player.getItem(thisItem.getItemType()) == null)
        {

            if (thisItem.isAvalible() && thisItem.inUse == false)
            {
                player.setItem(thisItem);
                changeLabelBigIcon(thisItem);
                changeLabelSmallIcon(label, thisItem);
                updateShopLabels();
            }

        }

    }

    private void changeActiveItem(JLabel label, Logic.Type itemType)
    {
        if (player.getItem(itemType) != null)
        {
            player.getItem(itemType).inUse = false;
            player.setItemNull(itemType);
            label.setIcon(defaultIcon);
            label.repaint();
            updateShopLabels();
            //repaintShopLabels();
        }
    }
    private void armorLabel1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_armorLabel1MouseClicked
    {//GEN-HEADEREND:event_armorLabel1MouseClicked
        checkItemDressPlayer(armorLabel1, armArray[0]);
        // TODO add your handling code here:
    }//GEN-LAST:event_armorLabel1MouseClicked

    private void armorLabel2MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_armorLabel2MouseClicked
    {//GEN-HEADEREND:event_armorLabel2MouseClicked
        checkItemDressPlayer(armorLabel2, armArray[1]);
// TODO add your handling code here:
    }//GEN-LAST:event_armorLabel2MouseClicked

    private void armorLabel3MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_armorLabel3MouseClicked
    {//GEN-HEADEREND:event_armorLabel3MouseClicked
        checkItemDressPlayer(armorLabel3, armArray[2]);
    }//GEN-LAST:event_armorLabel3MouseClicked

    private void armorLabel4MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_armorLabel4MouseClicked
    {//GEN-HEADEREND:event_armorLabel4MouseClicked
        checkItemDressPlayer(armorLabel4, armArray[3]);
    }//GEN-LAST:event_armorLabel4MouseClicked

    private void armorLabel5MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_armorLabel5MouseClicked
    {//GEN-HEADEREND:event_armorLabel5MouseClicked
        checkItemDressPlayer(armorLabel5, armArray[4]);
    }//GEN-LAST:event_armorLabel5MouseClicked

    private void armorLabel6MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_armorLabel6MouseClicked
    {//GEN-HEADEREND:event_armorLabel6MouseClicked
        checkItemDressPlayer(armorLabel6, armArray[5]);
    }//GEN-LAST:event_armorLabel6MouseClicked

    private void armorLabel7MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_armorLabel7MouseClicked
    {//GEN-HEADEREND:event_armorLabel7MouseClicked
        checkItemDressPlayer(armorLabel7, armArray[6]);
    }//GEN-LAST:event_armorLabel7MouseClicked

    private void armorLabel8MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_armorLabel8MouseClicked
    {//GEN-HEADEREND:event_armorLabel8MouseClicked
        checkItemDressPlayer(armorLabel8, armArray[7]);
    }//GEN-LAST:event_armorLabel8MouseClicked

    private void armorLabel9MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_armorLabel9MouseClicked
    {//GEN-HEADEREND:event_armorLabel9MouseClicked
        checkItemDressPlayer(armorLabel9, armArray[8]);
    }//GEN-LAST:event_armorLabel9MouseClicked

    private void armorLabel10MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_armorLabel10MouseClicked
    {//GEN-HEADEREND:event_armorLabel10MouseClicked
        checkItemDressPlayer(armorLabel10, armArray[9]);
    }//GEN-LAST:event_armorLabel10MouseClicked

    private void armorLabel11MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_armorLabel11MouseClicked
    {//GEN-HEADEREND:event_armorLabel11MouseClicked
        checkItemDressPlayer(armorLabel11, armArray[10]);
    }//GEN-LAST:event_armorLabel11MouseClicked

    private void armorLabel12MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_armorLabel12MouseClicked
    {//GEN-HEADEREND:event_armorLabel12MouseClicked
        checkItemDressPlayer(armorLabel2, armArray[11]);
    }//GEN-LAST:event_armorLabel12MouseClicked

    private void defenceChestMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_defenceChestMouseClicked
    {//GEN-HEADEREND:event_defenceChestMouseClicked
        changeActiveItem(defenceChest, Logic.Type.CHEST);
    }//GEN-LAST:event_defenceChestMouseClicked

    private void defenceStomachMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_defenceStomachMouseClicked
    {//GEN-HEADEREND:event_defenceStomachMouseClicked
        changeActiveItem(defenceStomach, Logic.Type.STOMACH);
    }//GEN-LAST:event_defenceStomachMouseClicked

    private void defenceLegsMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_defenceLegsMouseClicked
    {//GEN-HEADEREND:event_defenceLegsMouseClicked
        changeActiveItem(defenceLegs, Logic.Type.LEGS);
    }//GEN-LAST:event_defenceLegsMouseClicked

    private void weaponActiveMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_weaponActiveMouseClicked
    {//GEN-HEADEREND:event_weaponActiveMouseClicked
        changeActiveItem(weaponActive, Logic.Type.WEAPON);
    }//GEN-LAST:event_weaponActiveMouseClicked

    private void weaponLabel1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_weaponLabel1MouseClicked
    {//GEN-HEADEREND:event_weaponLabel1MouseClicked
        checkItemDressPlayer(weaponLabel1, wepArray[0]);
    }//GEN-LAST:event_weaponLabel1MouseClicked

    private void weaponLabel2MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_weaponLabel2MouseClicked
    {//GEN-HEADEREND:event_weaponLabel2MouseClicked
        checkItemDressPlayer(weaponLabel2, wepArray[1]);
    }//GEN-LAST:event_weaponLabel2MouseClicked

    private void weaponLabel3MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_weaponLabel3MouseClicked
    {//GEN-HEADEREND:event_weaponLabel3MouseClicked
        checkItemDressPlayer(weaponLabel3, wepArray[2]);
    }//GEN-LAST:event_weaponLabel3MouseClicked

    private void weaponLabel4MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_weaponLabel4MouseClicked
    {//GEN-HEADEREND:event_weaponLabel4MouseClicked
        checkItemDressPlayer(weaponLabel4, wepArray[3]);
    }//GEN-LAST:event_weaponLabel4MouseClicked

    private void weaponLabel5MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_weaponLabel5MouseClicked
    {//GEN-HEADEREND:event_weaponLabel5MouseClicked
        checkItemDressPlayer(weaponLabel5, wepArray[4]);
    }//GEN-LAST:event_weaponLabel5MouseClicked

    private void weaponLabel6MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_weaponLabel6MouseClicked
    {//GEN-HEADEREND:event_weaponLabel6MouseClicked
        checkItemDressPlayer(weaponLabel6, wepArray[5]);
    }//GEN-LAST:event_weaponLabel6MouseClicked

    private void weaponLabel7MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_weaponLabel7MouseClicked
    {//GEN-HEADEREND:event_weaponLabel7MouseClicked
        checkItemDressPlayer(weaponLabel7, wepArray[6]);
    }//GEN-LAST:event_weaponLabel7MouseClicked

    private void weaponLabel8MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_weaponLabel8MouseClicked
    {//GEN-HEADEREND:event_weaponLabel8MouseClicked
        checkItemDressPlayer(weaponLabel8, wepArray[7]);
    }//GEN-LAST:event_weaponLabel8MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed

        player.updatePlayerItemsByCurrentGame(armArray, wepArray);
        try
        {
            new InteractionWithDB().savePlayerToDatabase(player);
        } catch (CommunicationsException ex)
        {
            Logger.getLogger(CharacterFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(CharacterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void backgroundSliderMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_backgroundSliderMouseReleased
    {//GEN-HEADEREND:event_backgroundSliderMouseReleased
        FloatControl control = (FloatControl) MainMenu.b.sourceLine.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(backgroundSlider.getValue());
        System.out.println(backgroundSlider.getValue());
    }//GEN-LAST:event_backgroundSliderMouseReleased

    private void soundEffectsSliderMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_soundEffectsSliderMouseReleased
    {//GEN-HEADEREND:event_soundEffectsSliderMouseReleased
        (new SoundVolume()).setVolume((float) this.soundEffectsSlider.getValue() / 100);
        SoundPlayer.Play("Error");
    }//GEN-LAST:event_soundEffectsSliderMouseReleased

    private void highScoreButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_highScoreButtonActionPerformed
    {//GEN-HEADEREND:event_highScoreButtonActionPerformed
        new HighScoreStatsFrame(player.getLoginToDatabase()).setVisible(true);

    }//GEN-LAST:event_highScoreButtonActionPerformed

    private void setLocationToMiddle()
    {
        // Get the size of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int windowWidth = this.getSize().width;
        int windowHeight = this.getSize().height;
        int positionX = (screenSize.width - windowWidth) / 2;
        int positionY = (screenSize.height - windowHeight) / 2;

        // Move the window
        this.setLocation(positionX, positionY);
    }

    /**
     * This method is checking if player wearing any item on specific body part.
     * If yes returns icon of the specific item else default icon
     *
     * @param player receives instance of {@linkplain Player} that we currently
     * checking on.
     * @param type receives instance of {@linkplain Type} that describes body
     * part we currently checking.
     * @return {@linkplain Icon} of the currently wearing item or default image.
     */
    public Icon checkItemIfNull(Player player, Logic.Type type)
    {
        if (player.getItem(type) == null)
        {
            return defaultIcon;
        } else
        {
            return player.getItem(type).getBigIcon();
        }

    }

    /**
     * Method that changes the icons of the {@linkplain Item}s that the
     * {@linkplain Player} is currently wearing.
     *
     * @param thisItem receives instance of {@linkplain Item} that the user just
     * clicked to wear.
     */
    public void changeLabelBigIcon(Item thisItem)
    {
        Logic.Type type = thisItem.getItemType();
        switch (type)
        {
            case HEAD:
                defenceHead.setIcon(thisItem.getBigIcon());
                break;
            case CHEST:
                defenceChest.setIcon(thisItem.getBigIcon());
                break;

            case STOMACH:
                defenceStomach.setIcon(thisItem.getBigIcon());
                break;
            case LEGS:
                defenceLegs.setIcon(thisItem.getBigIcon());
                break;
            case WEAPON:
                weaponActive.setIcon(thisItem.getBigIcon());
                break;

        }

    }

    /**
     * Method used to refresh all icons on current screen.
     */
    void updateShopLabels()
    {

        changeLabelSmallIcon(armorLabel1, armArray[0]);
        changeLabelSmallIcon(armorLabel2, armArray[1]);
        changeLabelSmallIcon(armorLabel3, armArray[2]);
        changeLabelSmallIcon(armorLabel4, armArray[3]);
        changeLabelSmallIcon(armorLabel5, armArray[4]);
        changeLabelSmallIcon(armorLabel6, armArray[5]);
        changeLabelSmallIcon(armorLabel7, armArray[6]);
        changeLabelSmallIcon(armorLabel8, armArray[7]);
        changeLabelSmallIcon(armorLabel9, armArray[8]);
        changeLabelSmallIcon(armorLabel10, armArray[9]);
        changeLabelSmallIcon(armorLabel11, armArray[10]);
        changeLabelSmallIcon(armorLabel12, armArray[11]);

        changeLabelSmallIcon(weaponLabel1, wepArray[0]);
        changeLabelSmallIcon(weaponLabel2, wepArray[1]);
        changeLabelSmallIcon(weaponLabel3, wepArray[2]);
        changeLabelSmallIcon(weaponLabel4, wepArray[3]);
        changeLabelSmallIcon(weaponLabel5, wepArray[4]);
        changeLabelSmallIcon(weaponLabel6, wepArray[5]);
        changeLabelSmallIcon(weaponLabel7, wepArray[6]);
        changeLabelSmallIcon(weaponLabel8, wepArray[7]);
        updateStatsLabel();
    }

    private void changeLabelSmallIcon(JLabel label, Item item)
    {
        label.setIcon(item.getSmallIcon());
        if (item instanceof Armor)
        {
            label.setToolTipText(String.format("<html>" + "%s" + "<br>" + "<br>" + "Defence Factor: %.1f" + "</html>", item, ((Armor) item).getDefenceValue()));
        } else if (item instanceof Weapon)
        {
            label.setToolTipText(String.format("<html>" + "%s" + "<br>" + "<br>" + "Damage Value: %d" + "</html>", item, ((Weapon) item).getDamageValue()));
        }
    }

    private void updateStatsLabel()
    {
        String stats = player.getStats();
        statsTextArea.setText(stats);
        statsTextArea.repaint();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel armorLabel1;
    private javax.swing.JLabel armorLabel10;
    private javax.swing.JLabel armorLabel11;
    private javax.swing.JLabel armorLabel12;
    private javax.swing.JLabel armorLabel2;
    private javax.swing.JLabel armorLabel3;
    private javax.swing.JLabel armorLabel4;
    private javax.swing.JLabel armorLabel5;
    private javax.swing.JLabel armorLabel6;
    private javax.swing.JLabel armorLabel7;
    private javax.swing.JLabel armorLabel8;
    private javax.swing.JLabel armorLabel9;
    private javax.swing.JSlider backgroundSlider;
    private javax.swing.JLabel backgroundSoundLabel;
    private javax.swing.JLabel defenceChest;
    private javax.swing.JLabel defenceHead;
    private javax.swing.JLabel defenceLegs;
    private javax.swing.JLabel defenceStomach;
    private javax.swing.JLabel effectsSoundLabel;
    private javax.swing.JButton highScoreButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelActiveDefence;
    private javax.swing.JPanel panelActiveWeaponContainer;
    private javax.swing.JPanel panelShop;
    private javax.swing.JPanel panelStats;
    private javax.swing.JLabel playerIconLabel;
    private javax.swing.JSlider soundEffectsSlider;
    protected javax.swing.JButton startBattleButton;
    private javax.swing.JTextArea statsTextArea;
    private javax.swing.JLabel weaponActive;
    private javax.swing.JLabel weaponLabel1;
    private javax.swing.JLabel weaponLabel2;
    private javax.swing.JLabel weaponLabel3;
    private javax.swing.JLabel weaponLabel4;
    private javax.swing.JLabel weaponLabel5;
    private javax.swing.JLabel weaponLabel6;
    private javax.swing.JLabel weaponLabel7;
    private javax.swing.JLabel weaponLabel8;
    // End of variables declaration//GEN-END:variables
}
