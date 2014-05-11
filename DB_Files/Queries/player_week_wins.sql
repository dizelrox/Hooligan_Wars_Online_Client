SELECT username, COUNT(*) AS wins FROM players, wonmatches
WHERE Players.username = 'bodya22'
AND Players.playerId = wonmatches.Players_playerId 
AND wonmatches.matchEndTime <= CURRENT_TIMESTAMP 
AND wonmatches.matchEndTime >= DATE_ADD(NOW(), INTERVAL -7 DAY) 
GROUP BY username ORDER BY wins DESC;