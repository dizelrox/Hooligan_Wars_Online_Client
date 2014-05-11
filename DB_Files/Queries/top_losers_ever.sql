SELECT username, COUNT(*) AS wins FROM players, lostmatches 
WHERE Players.playerId = lostmatches.Players_playerId GROUP BY username ORDER BY wins DESC;