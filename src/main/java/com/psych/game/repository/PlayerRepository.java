package com.psych.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psych.game.model.Player;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
