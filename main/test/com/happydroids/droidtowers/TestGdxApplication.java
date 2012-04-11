/*
 * Copyright (c) 2012. HappyDroids LLC, All rights reserved.
 */

package com.happydroids.droidtowers;

import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglGraphics;

public class TestGdxApplication implements Application {

  private GdxTestPreferences preferences;

  public TestGdxApplication(ApplicationListener listener, LwjglApplicationConfiguration config) {
    preferences = new GdxTestPreferences();
  }

  public LwjglGraphics getGraphics() {
    return null;
  }

  public Audio getAudio() {
    return null;
  }

  public Input getInput() {
    return null;
  }

  public Files getFiles() {
    return null;
  }

  public void log(String tag, String message) {
  }

  public void log(String tag, String message, Exception exception) {
  }

  public void error(String tag, String message) {
  }

  public void error(String tag, String message, Throwable exception) {
  }

  public void debug(String tag, String message) {
  }

  public void debug(String tag, String message, Throwable exception) {
  }

  public void setLogLevel(int logLevel) {
  }

  public ApplicationType getType() {
    return null;
  }

  public int getVersion() {
    return 0;
  }

  public long getJavaHeap() {
    return 0;
  }

  public long getNativeHeap() {
    return 0;
  }

  public Preferences getPreferences(String name) {
    return preferences;
  }

  public void postRunnable(Runnable runnable) {
  }

  public void exit() {
  }
}
