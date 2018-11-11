package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.PlayerDao;
import system.model.games.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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

    public Player getPlayer(String id) {
        Optional<Player> foundPlayer = dao.getPlayers().stream().filter(p -> id.equals(p.getId())).findAny();
        return foundPlayer.orElse(null);
    }

    public void removePlayer(String id) {
        dao.getPlayers().removeIf(p -> id.equals(p.getId()));
    }

}
