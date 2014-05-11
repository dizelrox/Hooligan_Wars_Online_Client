SELECT username, COUNT(*) AS wins FROM players, lostmatches 
WHERE Players.username = 'bodya22'
AND Players.playerId = lostmatches.Players_playerId 
AND lostmatches.matchEndTime <= CURRENT_TIMESTAMP 
AND lostmatches.matchEndTime >= DATE_ADD(NOW(), INTERVAL -7 DAY) 
GROUP BY username ORDER BY wins DESC;