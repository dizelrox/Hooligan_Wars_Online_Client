SELECT username, COUNT(*) AS amount FROM players, lostmatches 
WHERE Players.playerId = lostmatches.Players_playerId AND 
lostmatches.matchEndTime < CURRENT_TIMESTAMP AND 
lostmatches.matchEndTime >= DATE_ADD(NOW(), INTERVAL -7 DAY) 
GROUP BY username ORDER BY losses DESC;