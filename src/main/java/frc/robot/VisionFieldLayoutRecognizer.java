package frc.robot;

import java.util.ArrayList;
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

    private  PixyCameraConnector objectPixyCameraConnector = new PixyCameraConnector();    

    public AutonomousPath detectPose(FieldVisionInput pixyBlock) {

      AutonomousPath objectAutonomousPath;
      FieldVisionInput objectFieldVisionInput;
      ArrayList<FieldVisionInput> arraylistFieldVisionInput;

      double dblFocalLength = 11.33;//pixels = 3mm
      double dblWidth = 7; //inches
      double dblDistance = 0; 
      double dblAverageDistance = 0;
      double[] dblArrPixels = new double[4];

      arraylistFieldVisionInput = objectPixyCameraConnector.getFieldVisionInput();
      objectAutonomousPath = new AutonomousPath();

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
          dblArrPixels[count] = objectFieldVisionInput.objectHeight * 
                                  objectFieldVisionInput.objectWidth;
        }
      }
      
      // For loop that adds the distance of the three balls
      for (int i = 0; i < dblArrPixels.length; i++) {
         dblDistance = dblDistance + (dblWidth*dblFocalLength)/dblArrPixels[i];
  
      }
      
      // Finding the average of the distance
      dblAverageDistance = dblDistance/3;
  
    // I am comparing the above calculated value with the averages i calculated using the given 
    // galactic search measurements.
   
    if (dblAverageDistance > 8.50 && dblAverageDistance < 10.0){
      objectAutonomousPath.pathName = frc.robot.AutonomousPath.VisionPathName.PathRedA;
    }
    else if (dblAverageDistance > 16.10 && dblAverageDistance < 16.30){
      objectAutonomousPath.pathName = frc.robot.AutonomousPath.VisionPathName.PathBlueA;
    }
    else if (dblAverageDistance > 10.30 && dblAverageDistance < 10.40){
      objectAutonomousPath.pathName = frc.robot.AutonomousPath.VisionPathName.PathRedA;
    }
    else if (dblAverageDistance > 17.50 && dblAverageDistance < 17.80){
      objectAutonomousPath.pathName = frc.robot.AutonomousPath.VisionPathName.PathRedB;
    }

      return objectAutonomousPath;

    }

 }