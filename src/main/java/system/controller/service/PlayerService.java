package system.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.model.dao.PlayerDao;
import system.model.classes.games.Player;

import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    @Autowired
    PlayerDao dao;

    public LinkedList<Player> getAll() {
        return dao.getPlayers();
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
