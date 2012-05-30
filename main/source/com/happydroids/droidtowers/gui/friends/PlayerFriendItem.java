/*
 * Copyright (c) 2012. HappyDroids LLC, All rights reserved.
 */

package com.happydroids.droidtowers.gui.friends;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.badlogic.gdx.utils.Scaling;
import com.happydroids.droidtowers.gamestate.server.PlayerProfile;
import com.happydroids.droidtowers.gui.HorizontalRule;

import static com.happydroids.droidtowers.Colors.DARK_GRAY;
import static com.happydroids.droidtowers.gui.FontManager.Roboto18;
import static com.happydroids.droidtowers.platform.Display.scale;

public class PlayerFriendItem extends Table {
  private PlayerProfile profile;

  public PlayerFriendItem(PlayerProfile profile) {
    this.profile = profile;
  }

  public PlayerFriendItem() {
  }

  protected String getPlayerName() {
    return profile.getFullName();
  }

  public void createChildren(TextureAtlas.AtlasRegion facebookIcon) {
    clear();
    defaults().pad(scale(4));

    row().fill();
    add(new Image(facebookIcon, Scaling.none)).spaceRight(scale(10));
    add(Roboto18.makeLabel(getPlayerName())).expandX();
    add(makeActionButton());

    row().fill();
    add(new HorizontalRule(DARK_GRAY, 1)).colspan(3);
  }

  protected TextButton makeActionButton() {
    return Roboto18.makeTextButton("Add Neighbor");
  }

  public boolean playerNameMatches(String text) {
    return getPlayerName().toLowerCase().contains(text);
  }
}