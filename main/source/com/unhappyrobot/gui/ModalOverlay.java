package com.unhappyrobot.gui;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.unhappyrobot.TowerGame;

public class ModalOverlay extends WidgetGroup {
  public static final float TARGET_OPACITY = 0.5f;
  private HeadsUpDisplay headsUpDisplay;
  private Image background;

  private static ModalOverlay instance;

  public static void initialize(HeadsUpDisplay headsUpDisplay) {
    if (instance == null) {
      instance = new ModalOverlay(headsUpDisplay);
    }
  }

  public static ModalOverlay instance() {
    if (instance == null) {
      throw new RuntimeException("Must call initialize first!");
    }

    return instance;
  }

  private ModalOverlay(HeadsUpDisplay headsUpDisplay) {
    this.headsUpDisplay = headsUpDisplay;

    Pixmap pixmap = new Pixmap(2, 2, Pixmap.Format.RGBA4444);
    pixmap.setColor(new Color(0, 0, 0, 1));
    pixmap.fill();

    final Texture texture = new Texture(Gdx.files.internal("hud/modal-noise.png"));
    texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

    background = new TiledImage(texture);
  }

  public float getPrefWidth() {
    return Gdx.graphics.getWidth();
  }

  public float getPrefHeight() {
    return Gdx.graphics.getHeight();
  }

  public void show() {
    headsUpDisplay.addActor(background);
    background.width = Gdx.graphics.getWidth();
    background.height = Gdx.graphics.getHeight();

    Timeline.createSequence()
            .push(Tween.set(background, WidgetAccessor.OPACITY).target(0f))
            .push(Tween.to(background, WidgetAccessor.OPACITY, 200).target(TARGET_OPACITY))
            .start(TowerGame.getTweenManager());
  }

  public void hide() {
    Timeline.createSequence()
            .push(Tween.set(background, WidgetAccessor.OPACITY).target(TARGET_OPACITY))
            .push(Tween.to(background, WidgetAccessor.OPACITY, 200).target(0f))
            .addCallback(TweenCallback.EventType.COMPLETE, new TweenCallback() {
              public void onEvent(EventType eventType, BaseTween source) {
                headsUpDisplay.removeActor(background);
              }
            })
            .start(TowerGame.getTweenManager());
  }

  @Override
  public boolean touchDown(float x, float y, int pointer) {
    return true;
  }

  @Override
  public boolean touchMoved(float x, float y) {
    return true;
  }
}
