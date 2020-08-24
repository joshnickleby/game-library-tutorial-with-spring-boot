package com.astontech.gamelibrary.services;

import com.astontech.gamelibrary.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class GameServiceImpl implements GameService {
  private final GameRepository gameRepository;

  @Autowired
  public GameServiceImpl(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }


  //region CRUD Methods

  @Override
  public Game save(Game game) {
    return this.gameRepository.save(game);
  }

  @Override
  public Stream<Game> saveAll(List<Game> games) {
    Iterable<Game> gameIterable = this.gameRepository.saveAll(games);

    return StreamSupport.stream(gameIterable.spliterator(), false);
  }

  @Override
  public Game getOne(UUID id) {
    return this.gameRepository.findById(id)
                              .orElseGet(Game::new);
  }

  @Override
  public Stream<Game> getAll() {
    Iterable<Game> gameIterable = this.gameRepository.findAll();

    return StreamSupport.stream(gameIterable.spliterator(), false);
  }

  @Override
  public boolean delete(UUID id) {
    this.gameRepository.deleteById(id);

    return !this.gameRepository.existsById(id);
  }

  //endregion CRUD Methods

}
