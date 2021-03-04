package frc.robot;

import java.util.ArrayList;
import java.util.HashMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/*
 * VisionFieldLayoutRecognizer
 * 
 * Version 1.0
 *
 * 02/27/2021
 * 
 * EnTech 
 */

 public class VisionFieldLayoutRecognizer
    implements PixyFieldPoseResolver{

    private ArrayList<Double> distanceRanges;
    
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

  public AutonomousPath detectPose(ArrayList<FieldVisionInput> arraylistFieldVisionInput) {
        
      AutonomousPath objectAutonomousPath;
      FieldVisionInput objectFieldVisionInput;
      DistanceRange objectDistanceRange;

      double dblFocalLength = 11.33;//pixels = 3mm
      double dblWidth = 7; //inches
      double dblDistance = 0; 
      double dblAverageDistance = 0;
      double[] dblArrPixels = new double[3];
      int visionPathCount = 0;
      VisionPathName strVisionPathName = VisionPathName.CouldNotDeterminePath;

      
      objectAutonomousPath = new AutonomousPath();
       
      // Just for refernce the formula to calculate the focal length
      //dblFocalLength = (dblPixels*dblDistance)/dblWidth;

      if (arraylistFieldVisionInput.size() >  0 ) {
        for(int count = 0; count < arraylistFieldVisionInput.size(); count++){

          objectFieldVisionInput = arraylistFieldVisionInput.get(count);

          dblArrPixels[count] = objectFieldVisionInput.getObjectHeight() * 
                                  objectFieldVisionInput.getObjectWidth();
                                  
        }
      }
      
      // For loop that adds the distance of the three balls
      for (int i = 0; i < dblArrPixels.length; i++) {
         dblDistance = dblDistance + (dblWidth*dblFocalLength)/dblArrPixels[i];
  
      }
      
      // Finding the average of the distance
      dblAverageDistance = dblDistance/3;
      
    
      // I am comparing the above calculated value with the averages 
      // I calculated using the given galactic search measurements.   
      if (distanceRanges.size() >  0 ) {
        for(int count = 0; count < distanceRanges.size(); count=count+2){

          objectDistanceRange = new DistanceRange(distanceRanges.get(count),
          distanceRanges.get(count+1), getVisionPathNameByIndex(visionPathCount));

          visionPathCount++;
          
          strVisionPathName = objectDistanceRange.isInRange(dblAverageDistance);

          if (strVisionPathName != VisionPathName.CouldNotDeterminePath){
            break;
          }
          
        }
      }

      objectAutonomousPath.setPathName(strVisionPathName);
      
      return objectAutonomousPath;

    }

 }