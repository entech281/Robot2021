package frc.pathrecogonizer;

import java.util.List;
import java.util.ArrayList;

 public class VisionFieldLayoutRecognizer
    implements PixyFieldPoseResolverInterface{

    private List<DistanceRange> distanceRanges;
    
    public VisionFieldLayoutRecognizer() {
      setupInitialDistanceRanges();
    }

    public void setupInitialDistanceRanges(){
        
      this.distanceRanges = List.of(new DistanceRange(10.0,8.50, VisionPathName.PathRedA),
      new DistanceRange(16.30,16.10, VisionPathName.PathRedB),
      new DistanceRange(10.40,10.30, VisionPathName.PathBlueA),
      new DistanceRange(17.80, 17.50, VisionPathName.PathBlueB)
      );

  }

  public AutonomousPath detectPose(List<FieldVisionInput> fieldVisionInputParameter) {
        
      AutonomousPath autonomousPath;
      FieldVisionInput fieldVisionInput;

      double focalLength = 11.33;//pixels = 3mm
      double width = 7; //inches
      double distance = 0; 
      double averageDistance = 0;
      List<Double> pixels = new ArrayList<Double>();
      VisionPathName visionPathName = VisionPathName.CouldNotDeterminePath;
       
      // Just for refernce the formula to calculate the focal length
      //dblFocalLength = (dblPixels*dblDistance)/dblWidth;

      if (fieldVisionInputParameter.size() >  0 ) {
        for(int count = 0; count < fieldVisionInputParameter.size(); count++){

          fieldVisionInput = fieldVisionInputParameter.get(count);

          pixels.add(Double.valueOf(fieldVisionInput.getObjectHeight() * 
                                  fieldVisionInput.getObjectWidth()));
                                  
        }
      }
      
      for (int i = 0; i < pixels.size(); i++) {
        if (pixels.get(i) > 0){
         distance = distance + (width*focalLength)/pixels.get(i);
        }
      }
      
      averageDistance = distance/3;
      
      if (distanceRanges.size() >  0 ) {
        
        for(DistanceRange dr:distanceRanges){

          if (dr.isInRange(averageDistance)){
            visionPathName = dr.getPath();
          }
          
        }
      }

      autonomousPath = new AutonomousPath(visionPathName);

      return autonomousPath;

    }

 }