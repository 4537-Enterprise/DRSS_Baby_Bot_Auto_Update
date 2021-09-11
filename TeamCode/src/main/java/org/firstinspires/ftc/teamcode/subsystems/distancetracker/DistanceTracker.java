package org.firstinspires.ftc.teamcode.subsystems.distancetracker;

import com.acmerobotics.roadrunner.geometry.Pose2d;

/**
 * Created by Antoine on 9/9/2021
 */
public class DistanceTracker {

   Pose2d currentPose;
   Pose2d lastPose;
   double distanceTraveledX = 0;
   double distanceTraveledY = 0;
   double totalDistanceTraveled;

   public DistanceTracker(Pose2d startPose) {
       lastPose = startPose;
       getSavedValue();
   }

   public void update(Pose2d currentPose) {
       this.currentPose = currentPose;

       Pose2d differencePose = currentPose.minus(lastPose);

       distanceTraveledX = Math.abs(differencePose.getX());
       distanceTraveledY = Math.abs(differencePose.getY());

       totalDistanceTraveled += Math.sqrt((Math.pow(distanceTraveledX, 2) + Math.pow(distanceTraveledY, 2)));

       lastPose = currentPose;
   }

   public void getSavedValue() {

   }
}
