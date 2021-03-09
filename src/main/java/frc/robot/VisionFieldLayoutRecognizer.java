package frc.robot;

import java.util.List;
import java.util.ArrayList;

 public class VisionFieldLayoutRecognizer
    implements PixyFieldPoseResolverInterface{

    private List<Double> distanceRanges;
    
    public VisionFieldLayoutRecognizer() {
      DistanceRange();
    }

    public void DistanceRange(){
        
      this.distanceRanges = new ArrayList<Double>();

      // PathRedA Bounds
      distanceRanges.add(10.00);
      distanceRanges.add(8.50);
      
      // PathBlueA Bounds
      distanceRanges.add(16.30);
      distanceRanges.add(16.10);

      // PathRedB Bounds
      distanceRanges.add(10.40);
      distanceRanges.add(10.30);

      // PathBlueB Bounds
      distanceRanges.add(17.80);
      distanceRanges.add(17.50);
  }

  private VisionPathName getVisionPathNameByIndex(int index){

    int count = 0;

    // iterate over enums using for loop 
      for (VisionPathName s : VisionPathName.values()) { 
        if (count == index) {
            return(s);
        }
    } 

    return VisionPathName.CouldNotDeterminePath;
  }

  public AutonomousPath detectPose(List<FieldVisionInput> fieldVisionInputParameter) {
        
      AutonomousPath autonomousPath;
      FieldVisionInput fieldVisionInput;
      DistanceRange distanceRange;

      double focalLength = 11.33;//pixels = 3mm
      double width = 7; //inches
      double distance = 0; 
      double averageDistance = 0;
      double[] pixels = new double[3];
      int visionPathCount = 0;
      VisionPathName visionPathName = VisionPathName.CouldNotDeterminePath;

      
      autonomousPath = new AutonomousPath();
       
      // Just for refernce the formula to calculate the focal length
      //dblFocalLength = (dblPixels*dblDistance)/dblWidth;

      if (fieldVisionInputParameter.size() >  0 ) {
        for(int count = 0; count < fieldVisionInputParameter.size(); count++){

          fieldVisionInput = fieldVisionInputParameter.get(count);

          pixels[count] = fieldVisionInput.getObjectHeight() * 
                                  fieldVisionInput.getObjectWidth();
                                  
        }
      }
      
      // For loop that adds the distance of the three balls
      for (int i = 0; i < pixels.length; i++) {
         distance = distance + (width*focalLength)/pixels[i];
  
      }
      
      // Finding the average of the distance
      averageDistance = distance/3;
      
    
      // I am comparing the above calculated value with the averages 
      // I calculated using the given galactic search measurements.   
      if (distanceRanges.size() >  0 ) {
        for(int count = 0; count < distanceRanges.size(); count=count+2){

          distanceRange = new DistanceRange(distanceRanges.get(count),
          distanceRanges.get(count+1), getVisionPathNameByIndex(visionPathCount));

          visionPathCount++;
          
          visionPathName = distanceRange.isInRange(averageDistance);

          if (visionPathName != VisionPathName.CouldNotDeterminePath){
            break;
          }
          
        }
      }

      autonomousPath.setPathName(visionPathName);
      
      return autonomousPath;

    }

 }