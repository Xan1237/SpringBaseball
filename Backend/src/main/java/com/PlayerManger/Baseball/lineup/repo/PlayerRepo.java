package com.PlayerManger.Baseball.lineup.repo;

import com.PlayerManger.Baseball.lineup.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepo extends JpaRepository<Player, Long> {
    void deletePlayerById(Long id);
    Player findPlayerById(Long id);
}
