SELECT username, COUNT(*) as amount FROM players, lostmatches 
WHERE Players.playerId = lostmatches.Players_playerId
AND Players.username = ? GROUP BY Players.username;