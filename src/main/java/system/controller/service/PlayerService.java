package system.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.model.classes.games.Game;
import system.model.dao.PlayerDao;
import system.model.classes.games.Player;

import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    PlayerDao dao;
    @Autowired
    GameService gameService;

    public LinkedList<Player> getAll() {
        return dao.getPlayers();
    }

    String updatedPlayerName;
    public String getNewPlayerNameForGame(String gameId, String playerName) {
        Game game = gameService.get(gameId);
        LinkedList<Player> gamePlayers = getAll()
                .stream()
                .filter(p -> game.getPlayers().contains(p.getId()))
                .collect(Collectors.toCollection(LinkedList::new));
        int numberToAddToPlayer = 0;
        updatedPlayerName = playerName;
        while(gamePlayers.stream().anyMatch(p -> updatedPlayerName.equals(p.getName()))) {
            numberToAddToPlayer ++;
            updatedPlayerName = playerName + numberToAddToPlayer;
        }
        return updatedPlayerName;
    }

    public void addPlayer(Player p) {
        dao.getPlayers().add(p);
    }

    public Player get(String id) {
        Optional<Player> foundPlayer = dao.getPlayers().stream().filter(p -> id.equals(p.getId())).findAny();
        return foundPlayer.orElse(null);
    }

    public void removePlayer(String id) {
        dao.getPlayers().removeIf(p -> id.equals(p.getId()));
    }

    public LinkedList<Player> getPlayersForIds(LinkedList<String> players) {
        return players.stream().map(this::get).collect(Collectors.toCollection(LinkedList::new));
    }
}
