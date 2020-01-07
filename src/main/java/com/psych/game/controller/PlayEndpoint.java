package com.psych.game.controller;

import com.psych.game.Utils;
import com.psych.game.exceptions.IllegalGameException;
import com.psych.game.exceptions.InsufficientPlayersException;
import com.psych.game.exceptions.InvalidActionForGameStateException;
import com.psych.game.exceptions.InvalidInputException;
import com.psych.game.model.*;
import com.psych.game.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/play")
public class PlayEndpoint
{
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlayerAnswerRepository playerAnswerRepository;

    @Autowired
    RoundRepository roundRepository;

    @GetMapping("/create-game/{pid}/{gm}/{nr}/{ellen}")
    public String createGame(@PathVariable(value = "pid") Long playerId,
                             @PathVariable(value = "gm") int gameMode,
                             @PathVariable(value = "nr") int numRounds,
                             @PathVariable(value = "ellen") int hasEllen) throws Exception {
//        Player player = playerRepository.findById(playerId)
//                .orElseThrow(() -> new Exception("Player doesn't exist!!"));

        Player player = playerRepository.findById(playerId).orElseThrow();

        Game game = new Game.Builder()
                .hasEllen(hasEllen == 1)
                .numRounds(numRounds)
                .gameMode(GameMode.fromValue(gameMode))
                .leader(player)
                .build();

        gameRepository.save(game);
        return "Created game: " + game.getId() + ". Code: " + Utils.getSecretCodeFromId(game.getId());

    }

    @GetMapping("/join-game/{pid}/{gc}")
    public String joinGame(@PathVariable(value = "pid") Long playerId,
                           @PathVariable(value = "gc") String gameCode) throws InvalidActionForGameStateException {


        Game game = gameRepository.findById(Utils.getGameIdFromSecretCode(gameCode)).orElseThrow();
        Player player = playerRepository.findById(playerId).orElseThrow();
        game.addPlayer(player);
        gameRepository.save(game);

        return "Successfully joined!!";
    }

    @GetMapping("/start-game/{pid}/{gid}")
    public String startGame(@PathVariable(value = "pid") Long playerId,
                            @PathVariable(value = "gid") Long gameId) throws IllegalGameException, InsufficientPlayersException, InvalidActionForGameStateException {
        Game game = gameRepository.findById(gameId).orElseThrow();
        Player player = playerRepository.findById(playerId).orElseThrow();

        if (!game.getLeader().equals(player))
            throw new IllegalGameException("Player hasn't joined any such game");

        if (game.getPlayers().size() < 2)
            throw new InsufficientPlayersException("Cannot start a game without any friends");

        game.start();
        gameRepository.save(game);

        return "Game started!!";

    }


    @GetMapping("/select-answer/{pid}/{gid}/{pans}")
    public String selectAnswer(@PathVariable(value = "pid") Long playerId,
                               @PathVariable(value = "gid") Long gameId,
                               @PathVariable(value = "pans") Long playerAnswerId) throws IllegalGameException, InvalidActionForGameStateException, InvalidInputException {
        Game game = gameRepository.findById(gameId).orElseThrow();
        Player player = playerRepository.findById(playerId).orElseThrow();
        PlayerAnswer playerAnswer = playerAnswerRepository.findById(playerAnswerId).orElseThrow();

        if (!game.hasPlayer(player))
            throw new IllegalGameException("Player has not joined the game yet!!");

        game.selectAnswer(player, playerAnswer);
        gameRepository.save(game);

        return "Selected answer!!";
    }


    @GetMapping("/get-ready/{pid}/{gid}")
    public String getReady(@PathVariable(value = "pid") Long playerId,
                           @PathVariable(value = "gid") Long gameId) throws IllegalGameException {
        Game game = gameRepository.findById(gameId).orElseThrow();
        Player player = playerRepository.findById(playerId).orElseThrow();

        if(!game.hasPlayer(player))
            throw new IllegalGameException("Player has not joined the game yet!!");

        game.getReady(player);
        gameRepository.save(game);

        return "Player Ready!!";
    }

    @GetMapping("/game-state/{gid}")
    public String gameState(@PathVariable(value = "gid") Long gameId)
    {
        Game game = gameRepository.findById(gameId).orElseThrow();

        return game.getState();
    }

    @GetMapping("/end-game/{pid}/{gid}")
    public String endGame(@PathVariable(value = "pid") Long playerId,
                          @PathVariable(value = "gid") Long gameId)
    {
        //make sure that u are the leader of the game.
        return "Game ended!!";
    }

    @GetMapping("/leave-game/{pid}/{gid}")
    public String leaveGame(@PathVariable(value = "pid") Long playerId,
                          @PathVariable(value = "gid") Long gameId)
    {

        //update player stats
        return "Game ended!!";
    }


}
