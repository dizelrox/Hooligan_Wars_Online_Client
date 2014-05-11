SELECT username, COUNT(*) as amount FROM players, wonmatches 
WHERE Players.playerId = wonmatches.Players_playerId
AND Players.username = ? GROUP BY Players.username;