package com.astontech.gamelibrary.services;

import com.astontech.gamelibrary.domain.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class GameServiceTest {
  GameRepository gameRepo = Mockito.mock(GameRepository.class);
  GameService gameService = new GameServiceImpl(gameRepo);

  @Test
  public void saveGameTest() {
    Game gameToSave = new Game("Test 1");

    Mockito.when(gameRepo.save(Mockito.any(Game.class)))
           .thenAnswer(AdditionalAnswers.returnsFirstArg());

    Game actualGame = gameService.save(gameToSave);

    Assertions.assertEquals(gameToSave.getTitle(), actualGame.getTitle());
  }

  @Test
  public void saveAllGamesTest() {
    List<Game> expected = Arrays.asList(
        new Game("Test 1"),
        new Game("Test 2")
    );

    Mockito.when(gameRepo.saveAll(expected))
           .thenAnswer(AdditionalAnswers.returnsFirstArg());

    List<Game> actual = gameService.saveAll(expected).collect(Collectors.toList());

    for (int i = 0; i < 2; i++) {
      String expectedTitle = expected.get(i).getTitle();
      String actualTitle = actual.get(i).getTitle();

      Assertions.assertEquals(expectedTitle, actualTitle);
    }
  }

  @Test
  public void getOneTest() {
    String expectedTitle = "Test 1";

    UUID id = UUID.randomUUID();

    Optional<Game> existingGame = Optional.of(new Game(expectedTitle));

    Mockito.when(gameRepo.findById(id)).thenReturn(existingGame);

    Game actualGame = gameService.getOne(id);

    Assertions.assertEquals(expectedTitle, actualGame.getTitle());

    Mockito.when(gameRepo.findById(Mockito.any(UUID.class))).thenReturn(Optional.empty());

    Game blankGame = gameService.getOne(UUID.randomUUID());

    Assertions.assertEquals("", blankGame.getTitle());
  }

  @Test
  public void getAllTest() {
    List<Game> expected = Arrays.asList(
        new Game("Test 1"),
        new Game("Test 2")
    );

    Mockito.when(gameRepo.findAll()).thenReturn(expected);

    List<Game> actual = gameService.getAll().collect(Collectors.toList());

    for (int i = 0; i < 2; i++) {
      String expectedTitle = expected.get(i).getTitle();
      String actualTitle = actual.get(i).getTitle();

      Assertions.assertEquals(expectedTitle, actualTitle);
    }
  }

  @Test
  public void deleteTest() {
    UUID id = UUID.randomUUID();

    Mockito.when(gameRepo.existsById(id)).thenReturn(false);

    boolean deletedCorrectId = gameService.delete(id);

    Assertions.assertTrue(deletedCorrectId);

    Mockito.when(gameRepo.existsById(Mockito.any(UUID.class))).thenReturn(true);

    boolean deletedWrongId = gameService.delete(UUID.randomUUID());

    Assertions.assertFalse(deletedWrongId);
  }
}
