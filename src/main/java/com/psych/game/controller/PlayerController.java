package com.psych.game.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.psych.game.model.Player;
import com.psych.game.repository.PlayerRepository;

//Dependency Injection -> Autowired

@RestController
@RequestMapping("/dev")
public class PlayerController {
	@Autowired
	private PlayerRepository playerRepository;

	@GetMapping("/players")
	public List<Player> getAllplayers()
	{
		System.out.println("Get All Players");
		return playerRepository.findAll(); //will get JSon over network
	}

	@PostMapping("/players")
	public Player createPlayer(@Valid @RequestBody Player player)
	{
		System.out.println("Save Player");
		return playerRepository.save(player);
	}


	@GetMapping("/players/{id}") //path variable
	public Player getPlayerById(@PathVariable(value = "id") Long id) throws Exception
	{
		System.out.println("Get Player By Id");
		return playerRepository.findById(id).orElseThrow(() -> new Exception("Something went wrong!!"));
	}

	@PutMapping("/players/{id}")
	public Player updatePlayer(@PathVariable(value = "id") Long id, @Valid @RequestBody Player player) throws Exception
	{
		System.out.println("Update Player");
		Player p= playerRepository.findById(id).orElseThrow(() -> new Exception("something went wrong !!"));
		p.setName(player.getName());
		return playerRepository.save(p);
	}

	@DeleteMapping("/players/{id}")
	public ResponseEntity<?> deletePlayer(@PathVariable(value = "id") Long id) throws Exception
	{
		System.out.println("Delete Player");
		Player p = playerRepository.findById(id).orElseThrow(() -> new Exception("something went wrong !!"));
		playerRepository.delete(p);
		return ResponseEntity.ok().build();
	}
}
