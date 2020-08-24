package com.astontech.gamelibrary.services;

import com.astontech.gamelibrary.domain.Game;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public interface GameService {
  Game save(Game game);
  Stream<Game> saveAll(List<Game> games);

  Game getOne(UUID id);
  Stream<Game> getAll();

  boolean delete(UUID id);
}
