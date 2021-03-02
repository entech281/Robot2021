package frc.robot;

import java.util.HashMap;

import frc.robot.AutonomousPath.VisionPathName;

public class DistanceRange 
  implements DistanceRangeInterface{

    HashMap<String, Double> distanceRanges;

    public DistanceRange(){
        
        this.distanceRanges = new HashMap<String, Double>();

        distanceRanges.put("PathRedAUpperBound", 10.00);
        distanceRanges.put("PathRedALowerBound", 8.50);
        
        distanceRanges.put("PathBlueAUpperBound", 16.30);
        distanceRanges.put("PathBlueALowerBound", 16.10);

        distanceRanges.put("PathRedBUpperBound", 10.40);
        distanceRanges.put("PathRedBLowerBound", 10.30);

        distanceRanges.put("PathBlueBUpperBound", 17.80);
        distanceRanges.put("PathBlueBLowerBound", 17.50);
    }

    public VisionPathName getPathForCalculatedDistance(double distanceCalculated){

        AutonomousPath objectAutonomousPath = new AutonomousPath();

        if (distanceCalculated >= getDistanceRanges().get("PathRedALowerBound") && 
              distanceCalculated <= getDistanceRanges().get("PathRedAUpperBound")){
            objectAutonomousPath.pathName = frc.robot.AutonomousPath.VisionPathName.PathRedA;
          }
          else if (distanceCalculated >= getDistanceRanges().get("PathBlueALowerBound") && 
                    distanceCalculated <= getDistanceRanges().get("PathBlueAUpperBound")){
            objectAutonomousPath.pathName = frc.robot.AutonomousPath.VisionPathName.PathBlueA;
          }
          else if (distanceCalculated >= getDistanceRanges().get("PathRedBLowerBound") && 
                      distanceCalculated <= getDistanceRanges().get("PathRedBUperBound")){
            objectAutonomousPath.pathName = frc.robot.AutonomousPath.VisionPathName.PathRedA;
          }
          else if (distanceCalculated >= getDistanceRanges().get("PathBlueBLowerBound") && 
                    distanceCalculated <= getDistanceRanges().get("PathBlueBLowerBound")){
            objectAutonomousPath.pathName = frc.robot.AutonomousPath.VisionPathName.PathRedB;
          }
          else{
            objectAutonomousPath.pathName  = VisionPathName.CouldNotDeterminePath;
          }

          return objectAutonomousPath.pathName;

    }

    public HashMap<String, Double> getDistanceRanges() {
      return distanceRanges;
    }

    // public void setDistanceRanges(HashMap<String, Double> distanceRanges) {
    //   this.distanceRanges = distanceRanges;
    // }

}