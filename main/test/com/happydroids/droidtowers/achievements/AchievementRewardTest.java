/*
 * Copyright (c) 2012. HappyDroids LLC, All rights reserved.
 */

package com.happydroids.droidtowers.achievements;

import com.happydroids.droidtowers.NonGLTestRunner;
import com.happydroids.droidtowers.types.ServiceRoomTypeFactory;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.happydroids.droidtowers.Expect.expect;

@RunWith(NonGLTestRunner.class)
public class AchievementRewardTest {
  @Test
  public void getRewardString_shouldWork_whenRewardIsMoney() {
    AchievementReward reward = new AchievementReward(RewardType.GIVE, AchievementThing.MONEY, 100);
    expect(reward.getRewardString()).toEqual("Awarded $100");
  }

  @Test
  public void getRewardString_shouldWork_whenRewardIsObjectType() {
    ServiceRoomTypeFactory.instance();

    AchievementReward reward = new AchievementReward(RewardType.UNLOCK, AchievementThing.OBJECT_TYPE, "MAIDS_CLOSET");
    expect(reward.getRewardString()).toEqual("Unlocked Maids Closet");
  }
}
