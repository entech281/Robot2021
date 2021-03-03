package frc.robot;

import java.util.HashMap;

public class DistanceRange 
  implements DistanceRangeInterface{

    private HashMap<String, Double> distanceRanges;

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
            objectAutonomousPath.setPathName(frc.robot.VisionPathName.PathRedA);
          }
          else if (distanceCalculated >= getDistanceRanges().get("PathBlueALowerBound") && 
                    distanceCalculated <= getDistanceRanges().get("PathBlueAUpperBound")){
            objectAutonomousPath.setPathName(frc.robot.VisionPathName.PathBlueA);
          }
          else if (distanceCalculated >= getDistanceRanges().get("PathRedBLowerBound") && 
                      distanceCalculated <= getDistanceRanges().get("PathRedBUperBound")){
            objectAutonomousPath.setPathName(frc.robot.VisionPathName.PathRedA);
          }
          else if (distanceCalculated >= getDistanceRanges().get("PathBlueBLowerBound") && 
                    distanceCalculated <= getDistanceRanges().get("PathBlueBLowerBound")){
            objectAutonomousPath.setPathName(frc.robot.VisionPathName.PathRedB);
          }
          else{
            objectAutonomousPath.setPathName(VisionPathName.CouldNotDeterminePath);
          }

          return objectAutonomousPath.getPathName();

    }

    public HashMap<String, Double> getDistanceRanges() {
      return distanceRanges;
    }

    // public void setDistanceRanges(HashMap<String, Double> distanceRanges) {
    //   this.distanceRanges = distanceRanges;
    // }

}