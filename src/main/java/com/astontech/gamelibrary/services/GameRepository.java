package com.astontech.gamelibrary.services;

import com.astontech.gamelibrary.domain.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GameRepository extends CrudRepository<Game, UUID> {
}
