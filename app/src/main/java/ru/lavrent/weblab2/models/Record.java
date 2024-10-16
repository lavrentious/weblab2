package ru.lavrent.weblab2.models;

import java.io.Serializable;

public class Record implements Serializable {
  private final long createdAt;
  private final float x;
  private final float y;
  private final float r;
  private final boolean isHit;
  private final long scriptTime;

  public Record(float x, float y, float r, long scriptTime) {
    this.createdAt = System.currentTimeMillis();
    this.x = x;
    this.y = y;
    this.r = r;
    this.isHit = checkHit(x, y, r);
    this.scriptTime = scriptTime;
  }

  public static boolean checkHit(float x, float y, float r) {
    if (x >= 0 && y <= 0) {
      return Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r / 2, 2);
    }
    if (x <= 0 && y <= 0) {
      return x >= -r && y >= -r / 2;
    }
    if (x >= 0 && y >= 0) {
      return y <= -2 * x + r;
    }
    return false;
  }

  public float getR() {
    return r;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public boolean isHit() {
    return isHit;
  }

  public long getCreatedAt() {
    return createdAt;
  }

  public long getScriptTime() {
    return scriptTime;
  }
}
