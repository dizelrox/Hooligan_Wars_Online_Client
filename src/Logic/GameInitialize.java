package Logic;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Class used to create all game objects necessary for the gameplay.
 * In this class both player and bot are created,all ingame armors and weapons.
 * @author Dima Ryskin
 * @author Bogdan Kirylyuk
 */
public class GameInitialize
{
	/**
	 * Static variable defining current build version.
	 * {@value}
	 */
    public static final String gameVersion = "Hooligan Wars v3.2";
    /**
     * Array that holds all ingame armors.
     * @see {@linkplain Armor}
     */
    private Armor armArray[] = new Armor[12];
    /**
     * Array that holds all ingame weapons.
     * @see {@linkplain Weapon}
     */
    private Weapon wepArray[] = new Weapon[8];
    /**
     * Variable that holds instance of class {@linkplain Player} that represents users character.
     */
    private Player player;
    /**
     * Variable holds instance of class {@linkplain Player} that represents computer's character.
     */
    private Player bot;
    

        
     /**
      * The default constructor used to initialize all object properties.   
      */
    public GameInitialize(boolean[] currentArmorArray, boolean[] currentWeaponArray)
    {
        
        armArray[0] = new Armor("Adidas Cap", currentArmorArray[0], Type.HEAD, 0.1,"helmet1");
        armArray[1] = new Armor("Biker Mohawk", currentArmorArray[1], Type.HEAD, 0.2,"helmet2");
        armArray[2] = new Armor("Real Men's Hat", currentArmorArray[2], Type.HEAD, 0.3,"Helmet");
        armArray[3] = new Armor("Adidas Original", currentArmorArray[3], Type.CHEST, 0.1,"armor1");
        armArray[4] = new Armor("Motorcycle Armor", currentArmorArray[4], Type.CHEST, 0.2,"armor2");
        armArray[5] = new Armor("Poseidon Armor", currentArmorArray[5], Type.CHEST, 0.3,"Armor");
        armArray[6] = new Armor("Summer Shorts", currentArmorArray[6], Type.STOMACH, 0.1,"pants1");
        armArray[7] = new Armor("Motorcycle Pants", currentArmorArray[7], Type.STOMACH, 0.2,"pants2");
        armArray[8] = new Armor("Real Men's Pants", currentArmorArray[8], Type.STOMACH, 0.3,"Belt");
        armArray[9] = new Armor("Russian Style", currentArmorArray[9], Type.LEGS, 0.1,"shoes1");
        armArray[10] = new Armor("Boots", currentArmorArray[10], Type.LEGS, 0.2,"shoes2");
        armArray[11] = new Armor("Army Boots", currentArmorArray[11], Type.LEGS, 0.3,"Legs");

        wepArray[0] = new Weapon("Butterfly", currentWeaponArray[0], 10,"weapon1");
        wepArray[1] = new Weapon("Brace Knuckles", currentWeaponArray[1], 10,"weapon2");
        wepArray[2] = new Weapon("Maccabi Bottle", currentWeaponArray[2], 10,"weapon3");
        wepArray[3] = new Weapon("Motorcycle Chain", currentWeaponArray[3], 15,"weapon4");
        wepArray[4] = new Weapon("Pepper Spray", currentWeaponArray[4], 15,"weapon5");
        wepArray[5] = new Weapon("Brick", currentWeaponArray[5], 10,"weapon6");
        wepArray[6] = new Weapon("Molotov", currentWeaponArray[6], 20,"weapon7");
        wepArray[7] = new Weapon("Sword", currentWeaponArray[7], 20,"Sword");
        
        bot = new Player(wepArray, armArray);
        
    }
    
    
    /**
     * Method used to unlock 2 random items in case of player's win.
     * @return String value describing the items just unlocked.
     */
    public String getUnlockedItems()
    {
        String out = "";
        Armor arm = (Armor) unlockRandomItem(armArray);
        if (arm != null)
            out += String.format("%s is now unlocked!\n", arm.getName());
        
        Weapon wep = (Weapon) unlockRandomItem(wepArray);
        
        if (wep != null)
            out += String.format("%s is now unlocked!\n", wep.getName());
        
        return out;
    }
    
    /**
     * Method that gambles single locked item and unlocks it.
     * @param items receives array of derived class of {@linkplain Item}s
     * @return {@linkplain Item} object that represents the unlocked item.
     */
    private Item unlockRandomItem(Item[] items)
    {
        boolean isLocked = false;
        for (Item item : items)
        {
            if (!item.isAvalible())
            {
                isLocked = true;
                break;
            }
        }
        
        if (isLocked)
        {
            int i = 0;
            while(items[i].isAvalible())
                i = (int) (Math.random()*items.length);
            
            items[i].setAvalible(true);
            
            return items[i];
            
        }
        return null;
    }
    
    /**
     * Method used to create new player character.
     * @param name String value containing desired player name.
     * @return object of class {@linkplain Player}
     * @see Player
     */
    public Player getNewPlayer(String name)
    {
        return new Player(name);
    }
    
    /**
     * Method used to create new bot character.
     * @return instance of {@linkplain Player} built with dedicated constructor for bots.
     */
    public Player getNewBot()
    {
        return new Player(wepArray, armArray);
    }
    /**
     * Method used to get weapons array.
     * @return array of {@linkplain Weapon}s
     */
    public Weapon[] getWeapon()
    {
        return wepArray;
    }
    /**
     * Method used to get armors array.
     * @return array of {@linkplain Armor}s
     */
    public Armor[] getArmor()
    {
        return armArray;
    }
    /**
     * Method used to get the player character.
     * @return object of class {@linkplain Player}.
     */
    public Player getPlayer()
    {
        return player;
    }
    /**
     * Method used to get bot character.
     * @return object of class {@linkplain Player}.
     */
    public Player getBot()
    {
        return bot;
    }
    
}

