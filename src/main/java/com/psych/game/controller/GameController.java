package com.psych.game.controller;

import com.psych.game.model.Game;
import com.psych.game.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Dependency Injection -> Autowired

@RestController
@RequestMapping("/dev")
public class GameController {
	@Autowired
	private GameRepository gameRepository;

	@GetMapping("/games")
	public List<Game> getAllGames()
	{
		System.out.println("Get All Games");
		return gameRepository.findAll(); //will get JSon over network
	}



	@GetMapping("/games/{id}") //path variable
	public Game getGameById(@PathVariable(value = "id") Long id) throws Exception
	{
		return gameRepository.findById(id).orElseThrow(() -> new Exception("Something went wrong!!"));
	}

	@DeleteMapping("/games/{id}")
	public ResponseEntity<?> deleteGame(@PathVariable(value = "id") Long id) throws Exception
	{
		Game game = gameRepository.findById(id).orElseThrow(() -> new Exception("something went wrong !!"));
		gameRepository.delete(game);
		return ResponseEntity.ok().build();
	}
}
