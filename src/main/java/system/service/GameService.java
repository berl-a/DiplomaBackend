package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.GameDao;
import system.model.games.Game;

import java.util.LinkedList;

@Service
public class GameService {

    private static final boolean STORE_GAMES_LOCALLY = true;

    @Autowired
    GameDao gameDao;

    private LinkedList<Game> cachedGames = new LinkedList<>();

    public void updateCachedGames() {
        cachedGames = gameDao.getGames();
    }

    public LinkedList<Game> getGames() {
        if(!STORE_GAMES_LOCALLY)
            updateCachedGames();
        return cachedGames;
    }

}
