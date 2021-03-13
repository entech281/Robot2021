package frc.pathrecognizer;

import java.util.List;
import java.util.ArrayList;

 public class VisionFieldLayoutRecognizer
    implements PixyFieldPoseResolverInterface{


    //Cannot instantiate the type List if I say new List<DistanceRange>, I get this error
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
  private double ComputeAverageDistance(List<FieldVisionInput> fieldVisionInputParameter){
    List<Integer> pixels = new ArrayList<Integer>();
    double focalLength = 11.33;//pixels = 3mm
    double width = 7; //inches
    double distance = 0; 

    if (fieldVisionInputParameter.size() >  0 ) {
          for(FieldVisionInput fieldVisionInput: fieldVisionInputParameter){

          pixels.add(fieldVisionInput.getObjectHeight() * 
                                  fieldVisionInput.getObjectWidth());
                                  
        }
      
        for (Integer pixarea: pixels){
        if (pixarea > 0){
         distance = distance + (width*focalLength)/pixarea;
        }
      }

      return distance/pixels.size();
    }
    else 
      return 0;
  }

  public AutonomousPath detectPose(List<FieldVisionInput> fieldVisionInputParameter) {
        
      AutonomousPath autonomousPath;

      
      double averageDistance = 0;
      
      VisionPathName visionPathName = VisionPathName.CouldNotDeterminePath;
       
      // Just for refernce the formula to calculate the focal length
      //dblFocalLength = (dblPixels*dblDistance)/dblWidth;      
      
      averageDistance = ComputeAverageDistance(fieldVisionInputParameter);
          
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