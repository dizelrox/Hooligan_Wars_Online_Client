SELECT username, COUNT(*) AS amount FROM players, wonmatches 
WHERE Players.playerId = wonmatches.Players_playerId AND 
wonmatches.matchEndTime < CURRENT_TIMESTAMP AND 
wonmatches.matchEndTime >= DATE_ADD(NOW(), INTERVAL -7 DAY) 
GROUP BY username ORDER BY losses DESC;