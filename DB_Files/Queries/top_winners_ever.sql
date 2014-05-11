SELECT username, COUNT(*) AS wins FROM players, wonmatches 
WHERE Players.playerId = wonmatches.Players_playerId GROUP BY username ORDER BY wins DESC;