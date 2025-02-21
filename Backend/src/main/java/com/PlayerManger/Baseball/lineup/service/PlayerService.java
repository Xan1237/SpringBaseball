package com.PlayerManger.Baseball.lineup.service;

import com.PlayerManger.Baseball.lineup.exception.UserNotFoundException;
import com.PlayerManger.Baseball.lineup.model.Player;
import com.PlayerManger.Baseball.lineup.repo.PlayerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PlayerService {
    @Autowired
    private final PlayerRepo playerRepo;
    public PlayerService(PlayerRepo playerRepo){
        this.playerRepo = playerRepo;
    }

    public Player addPlayer(Player player){
        player.setPlayerCode(UUID.randomUUID().toString());
        return playerRepo.save(player);
    }

    public List<Player> findAllPlayers(){
        return playerRepo.findAll();
    }

    public Player updatePlayer(Player player){
        return playerRepo.save(player);
    }

    public Player findPlayerById(Long id){
        Player player =  playerRepo.findPlayerById(id);
            if(player == null){
                throw new UserNotFoundException("User by id" + id + " was not found");
            }
        return player;
    }

    public void deletePlayer(Long id){
        playerRepo.deletePlayerById(id);
    }
}
