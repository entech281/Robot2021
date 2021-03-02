package frc.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.AutonomousPath.VisionPathName;


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

     
    
    public VisionFieldLayoutRecognizer() {
      
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

      
      objectAutonomousPath = new AutonomousPath();
      objectDistanceRange = new DistanceRange();

      // This is the input array that will hold the values provided by the pixie cam 
      // regarding the pixels from each ball seen
      // I am commenting the below code on 02/27/2021
      // I am going to calculate the pixels seen by multiplying
      // the height and width that the pixie camera returns to us
      // when we make the call to getFieldVisionInput above
      //// double[] dblArrPixels = {5.0,13.46,10.31};
      
  
      // Just for refernce the formula to calculate the focal length
      //dblFocalLength = (dblPixels*dblDistance)/dblWidth;

      if (arraylistFieldVisionInput.size() >  0 )   
      {
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
      SmartDashboard.putNumber("Distance: ", dblDistance);
      // Finding the average of the distance
      dblAverageDistance = dblDistance/3;
      SmartDashboard.putNumber("AvgDist", dblAverageDistance);
    
      // I am comparing the above calculated value with the averages i calculated using the given 
      // galactic search measurements.   
    
      objectAutonomousPath.pathName =  objectDistanceRange.getPathForCalculatedDistance(dblAverageDistance);
    
      SmartDashboard.putString("Path", objectAutonomousPath.pathName.toString());
      return objectAutonomousPath;

    }

 }