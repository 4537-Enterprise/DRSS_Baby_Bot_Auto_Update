package org.firstinspires.ftc.teamcode.subsystems.distancetracker;

import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Transform2d;

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
   }

   public void update(Pose2d currentPose) {
       this.currentPose = currentPose;

       Transform2d differencePose = currentPose.minus(lastPose);

       distanceTraveledX = Math.abs(differencePose.getTranslation().getX());
       distanceTraveledY = Math.abs(differencePose.getTranslation().getY());

       totalDistanceTraveled += Math.sqrt((Math.pow(distanceTraveledX, 2) + Math.pow(distanceTraveledY, 2)));

       lastPose = currentPose;
   }

}
