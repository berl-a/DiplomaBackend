package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import system.model.games.Game;

import java.util.LinkedList;

@Component
public class GameArchiver {

    @Autowired
    GameService gameService;
    @Autowired
    ResultService resultService;

    @Scheduled(fixedRate = 5000)
    public void archiveFinishedGames() {
        LinkedList<Game> finishedGames = gameService.getFinishedGames();
        gameService.removeFinishedGames();
        resultService.archiveGames(finishedGames);
    }

}
