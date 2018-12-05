package system.model.dao;

import org.springframework.stereotype.Repository;
import system.model.games.Player;

import java.util.LinkedList;
import java.util.Optional;

@Repository
public class PlayerDao {

    private LinkedList<Player> players = new LinkedList<>();

    public void addPlayer(Player p) {
        players.add(p);
    }

    public Player getPlayer(String id) {
        Optional<Player> foundPlayer = players.stream().filter(p -> id.equals(p.getId())).findAny();
        return foundPlayer.orElse(null);
    }

    public void removePlayer(String id) {
        players.removeIf(p -> id.equals(p.getId()));
    }

    public LinkedList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(LinkedList<Player> players) {
        this.players = players;
    }
}
