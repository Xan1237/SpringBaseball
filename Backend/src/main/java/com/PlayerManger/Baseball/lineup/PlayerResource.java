package com.PlayerManger.Baseball.lineup;

import com.PlayerManger.Baseball.lineup.model.Player;
import com.PlayerManger.Baseball.lineup.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayerResource {
    private final PlayerService playerService;

    public PlayerResource(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Player>> getAllPlayers () {
        List<Player> players = playerService.findAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Player> getPlayerById (@PathVariable("id") Long id) {
        Player player = playerService.findPlayerById(id);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Player> addPlayer(@RequestBody Player player){
        Player newPlayer = playerService.addPlayer(player);
        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Player> updateEmployee(@RequestBody Player player) {
        Player updatePlayer = playerService.updatePlayer(player);
        return new ResponseEntity<>(updatePlayer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable("id") Long id){
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
