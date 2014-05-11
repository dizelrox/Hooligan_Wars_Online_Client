package Logic;
import GUI.MainMenu;


/**
 * This is the entrance point of the program.
 * Thru this class the program opens the main menu.
 * @author Dima Ryskin
 * @author Bogdan Kirylyuk
 * @see {@link GUI.MainMenu}
 * @version 3.2
 */
public class Run
{
	/**
	 * This is the main and single method of the class.
	 * This method creates object {@link GUI.MainMenu}.
	 */
    public static void main(String[] args)
    {
         MainMenu menuFrame = new MainMenu(true);
    }
    
   
}
