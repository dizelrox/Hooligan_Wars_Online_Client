/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dima
 */
public class InteractionWithGameServer 
{
    public static final int WAITING_FOR_OPPONENT = 1;
    public static final int STARTING_GAME = 2;
    public static final int TURN_STATUS_YOUR_TURN = 11;
    public static final int TURN_STATUS_NOT_YOOUR_TURN = 12;
    public final static int WAITING_FOR_PLAYER_OBJECT = 13;
    ObjectInputStream receiveObjectFromServer;
    ObjectOutputStream sendObjectToServer;
    
    Scanner inputSatusFromServer;
    
    Socket clientSocket;    
    public InteractionWithGameServer() throws IOException
    {
            clientSocket = new Socket ("127.0.0.1", 55555);
            System.out.println(clientSocket);
            //inputSatusFromServer = new Scanner(inputFromServer);
            sendObjectToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            
            receiveObjectFromServer = new ObjectInputStream(clientSocket.getInputStream());
            
        
    }
    
    public boolean opponentConnected()
    {
        try
        {
            if(((Integer) receiveObjectFromServer.readObject()).intValue() == 1)
                return true;
        } catch (IOException ex)
        {
            Logger.getLogger(InteractionWithGameServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(InteractionWithGameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Object getObjectFromServer()
    {
        Object objFromServer = null;
        try
        {
            objFromServer = receiveObjectFromServer.readObject();
            
        } catch (IOException ex)
        {
            Logger.getLogger(InteractionWithGameServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(InteractionWithGameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
        return objFromServer;
        }
    }
    
    public void closeConnection()
    {
        try
        {
            clientSocket.close();
        } catch (IOException ex)
        {
            Logger.getLogger(InteractionWithGameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Player getOpponent()
    {
        Player opponent = null;
        try
        {
            opponent = (Player) receiveObjectFromServer.readObject();
        } catch (IOException ex)
        {
            Logger.getLogger(InteractionWithGameServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(InteractionWithGameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            return opponent;
        }
    }
    
    public void sendObjectToServer(Object object)
    {
        try
        {
            sendObjectToServer.writeObject(object);
        } catch (IOException ex)
        {
            Logger.getLogger(InteractionWithGameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendCurrntPlayer(Player player)
    {
        try
        {
            sendObjectToServer.writeObject(player);
        } catch (IOException ex)
        {
            Logger.getLogger(InteractionWithGameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
