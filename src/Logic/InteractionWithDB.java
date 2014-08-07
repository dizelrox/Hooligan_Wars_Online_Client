/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dizel
 */
public class InteractionWithDB
{
    Connection conn;
    public InteractionWithDB() throws CommunicationsException
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://dizel-services.ddns.net/hooligan_wars_schema", "root", "bogdan123");
        } catch (SQLException ex)
        {
            //Logger.getLogger(InteractionWithDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new CommunicationsException(null, 0, 0, ex);
        } 
    }

    public void addUser(String login, String password, Player playerForDb) throws SQLException
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/hooligan_wars_schema", "root", "root");
            
            Blob newPlayerBlob = conn.createBlob();
            //Serialize the player
            ByteArrayOutputStream bos  = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(playerForDb);
            
            byte[] objectInBytes = bos.toByteArray();
            //End object serialize
            newPlayerBlob.setBytes(1, objectInBytes);
            
            PreparedStatement stat = conn.prepareStatement("INSERT INTO `Players` (`username`, `password`, `playerObject`) VALUES ('" + login + "', '" + password + "', ?);");
            stat.setBlob(1, newPlayerBlob);
            
            stat.executeUpdate();
            conn.close();
            
        } catch (ClassNotFoundException | IOException ex)
        {
            Logger.getLogger(InteractionWithDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            conn.close();
        }
    }
    
    public void savePlayerToDatabase(Player currentPlayer) throws SQLException
    {
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/hooligan_wars_schema", "root", "root");
            
            Blob newPlayerBlob = conn.createBlob();
            //Serialize the player
            ByteArrayOutputStream bos  = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(currentPlayer);
            
            byte[] objectInBytes = bos.toByteArray();
            //End object serialize
            newPlayerBlob.setBytes(1, objectInBytes);
            
            PreparedStatement stat = conn.prepareStatement("UPDATE `Players` SET playerObject=? WHERE username='"+currentPlayer.getLoginToDatabase() + "';");
            stat.setBlob(1, newPlayerBlob);
            
            stat.executeUpdate();
            conn.close();
            
        } catch (ClassNotFoundException | IOException ex)
        {
            Logger.getLogger(InteractionWithDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            conn.close();
        }
    }

    
    public Player getUser(String login, String password) throws SQLException
    {
        Player playerFromDb = null;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/hooligan_wars_schema", "root", "root");
            PreparedStatement stat = conn.prepareStatement("SELECT username,password,playerObject FROM Players WHERE username='" + login + "';");
            ResultSet res = stat.executeQuery();
            
            if (res.next())
            {
                String dbUsername = res.getString("username");
                String dbPassword = res.getString("password");
                
                if (login.equalsIgnoreCase(dbUsername) 
                        && password.equalsIgnoreCase(dbPassword))
                {
                    Blob playerBlob = res.getBlob("playerObject");
                    byte[] playerInBytes = playerBlob.getBytes(1, (int) playerBlob.length());
                    
                    ByteArrayInputStream inputPlayerInBytes = new ByteArrayInputStream(playerInBytes);
                    ObjectInputStream inputObject = new ObjectInputStream(inputPlayerInBytes);
                    
                    playerFromDb  = (Player) inputObject.readObject();                    
                    
                }
            }
        } catch (ClassNotFoundException | IOException ex)
        {
            Logger.getLogger(InteractionWithDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            conn.close();
        }
        
        return playerFromDb;
    }
    
    public void updatePlayerStats(Player currentPlayer, boolean isWin) throws SQLException
    {
                try
        {
            
            Class.forName("com.mysql.jdbc.Driver");
            PreparedStatement stat;
            if(isWin)
            {
            stat = conn.prepareStatement
            ("INSERT INTO `sql544839`.wonmatches (Players_playerId) \n" +
            "(SELECT playerId FROM `sql544839`.Players WHERE username=?);");
            }
            else
            {
            stat = conn.prepareStatement
            ("INSERT INTO `sql544839`.lostmatches (Players_playerId) \n" +
            "(SELECT playerId FROM `sql544839`.Players WHERE username=?);");
            }
            stat.setString(1, currentPlayer.getLoginToDatabase());
            
            stat.executeUpdate();
                        
        } 
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(InteractionWithDB.class.getName()).log(Level.SEVERE, null, ex);
        }        finally
        {
            conn.close();
        }
                
        
    }
  
    public List getAllScore(boolean isWin, boolean isRecent) throws SQLException
    {
        String tableName = (isWin)? "wonmatches":"lostmatches";
        
               
        List <TableRow> resultsTable  = new ArrayList <TableRow>();
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            PreparedStatement stat;
        if(isRecent)
        {
            final String sqlStatement = 
        "SELECT username, COUNT(*) AS amount FROM players, {0} \n" +
        "WHERE Players.playerId = {1}.Players_playerId AND \n" +
        " {2}.matchEndTime < CURRENT_TIMESTAMP AND \n" +
        " {3}.matchEndTime >= DATE_ADD(NOW(), INTERVAL -7 DAY) \n" +
        "GROUP BY username ORDER BY amount DESC;";
            
            String readyQuery = MessageFormat.format
        (sqlStatement, tableName, tableName, tableName, tableName);
            
            stat = conn.prepareStatement(readyQuery);
        }
        else 
        {
            final String sqlStatement = "SELECT username, COUNT(*) AS amount FROM players, {0} \n" +
        "WHERE Players.playerId = {1}.Players_playerId GROUP BY username ORDER BY amount DESC;";
            String readyQuery = MessageFormat.format
        (sqlStatement, tableName, tableName);
            stat = conn.prepareStatement(readyQuery);
        }
            ResultSet res = stat.executeQuery();
            
            int maxResults = 5;
            while (res.next() && maxResults-- > 0 )
            {
                String username = res.getString("username");
                int amount = res.getInt("amount");
                
                resultsTable.add(new TableRow(username, amount));
               
            }
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(InteractionWithDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            conn.close();
            return resultsTable;
        }
        
    }
    
    public int getUserState(String username, boolean isWin, boolean isRecent) throws SQLException
    {
        String tableName = (isWin)? "wonmatches":"lostmatches";
        
        int amount = 0;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            PreparedStatement stat;
            if(isRecent)
            {
                final String sqlStatement = 
                    "SELECT username, COUNT(*) AS amount FROM players, {0}\n" +
                    "WHERE Players.username = ''{1}''\n" +
                    "AND Players.playerId = {2}.Players_playerId \n" +
                    "AND {3}.matchEndTime <= CURRENT_TIMESTAMP \n" +
                    "AND {4}.matchEndTime >= DATE_ADD(NOW(), INTERVAL -7 DAY) \n" +
                    "GROUP BY username ORDER BY amount DESC;";

                String readyQuery = MessageFormat.format
            (sqlStatement, tableName, username, tableName, tableName, tableName);

                stat = conn.prepareStatement(readyQuery);
            }
            else 
            {
                final String sqlStatement = 
                        "SELECT username, COUNT(*) as amount FROM players, {0} \n" +
                        "WHERE Players.playerId = {1}.Players_playerId\n" +
                        "AND Players.username = ''{2}'';";
                
                String readyQuery = MessageFormat.format
            (sqlStatement, tableName, tableName, username);
                stat = conn.prepareStatement(readyQuery);
            }
                ResultSet res = stat.executeQuery();
                
                res.next();
                
                amount =  res.getInt("amount");
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(InteractionWithDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            conn.close();
            return amount;
        }
        
    }
            
}
