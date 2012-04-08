/*
 * Copyright (c) 2012. HappyDroids LLC, All rights reserved.
 */

package com.happydroids.droidtowers.events;

import com.happydroids.droidtowers.grid.GameGrid;

public class GameGridResizeEvent {
  public final GameGrid gameGrid;

  public GameGridResizeEvent(GameGrid gameGrid) {
    if (gameGrid == null) {
      throw new RuntimeException("GameGridResizeEvent cannot be created with out a valid GameGrid");
    }

    this.gameGrid = gameGrid;
  }
}