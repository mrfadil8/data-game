package com.data.game.controller;

import com.data.game.model.Game;
import com.data.game.repo.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class GameController {

    @Autowired
    GameRepository gameRepository;

    @GetMapping("/getAllGames")
    public ResponseEntity<List<Game>> getAllGames() {
        try {
            List<Game> gameList = new ArrayList<>();
            gameRepository.findAll().forEach(gameList::add);

            if (gameList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(gameList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getGameById/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        Optional<Game> gameObj = gameRepository.findById(id);
        if (gameObj.isPresent()) {
            return new ResponseEntity<>(gameObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addGame")
    public ResponseEntity<Game> addGame(@RequestBody Game game) {
        try {
            Game gameObj = gameRepository.save(game);
            return new ResponseEntity<>(gameObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateGame/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game) {
        try {
            Optional<Game> gameData = gameRepository.findById(id);
            if (gameData.isPresent()) {
                Game updatedGameData = gameData.get();
                updatedGameData.setId(game.getId());
                updatedGameData.setTanggal(game.getTanggal());
                updatedGameData.setTimSatu(game.getTimSatu());
                updatedGameData.setTimDua(game.getTimDua());
                updatedGameData.setTempat(game.getTempat());

                Game gameObj = gameRepository.save(updatedGameData);
                return new ResponseEntity<>(gameObj, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteGameById/{id}")
    public ResponseEntity<HttpStatus> deleteGame(@PathVariable Long id) {
        try {
            gameRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAllGames")
    public ResponseEntity<HttpStatus> deleteAllGames() {
        try {
            gameRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
