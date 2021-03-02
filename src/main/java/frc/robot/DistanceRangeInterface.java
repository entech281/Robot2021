package frc.robot;

import frc.robot.AutonomousPath.VisionPathName;

public interface DistanceRangeInterface {

    public VisionPathName getPathForCalculatedDistance(double distanceCalculated);

}