package frc.pathrecognizer;

import java.util.List;
import java.util.ArrayList;

 public class VisionFieldLayoutRecognizer
    implements PixyFieldPoseResolverInterface{


    //Cannot instantiate the type List if I say new List<DistanceRange>, I get this error
    private List<DistanceRange> distanceRanges;
    public static final double FOCAL_LENGTH = 11.33;
    public static final double WIDTH = 7; //inches
    public static double DISTANCE = 0; 
    
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
  private double computeAverageDistance(List<FieldVisionInput> fieldVisionInputParameter){
    
    List<Integer> pixels = new ArrayList<Integer>();
    
          for(FieldVisionInput fieldVisionInput: fieldVisionInputParameter){

          pixels.add(fieldVisionInput.getObjectHeight() * 
                                  fieldVisionInput.getObjectWidth());
                                  
        }
      
        for (Integer pixarea: pixels){
        if (pixarea > 0){
         DISTANCE = DISTANCE + (WIDTH*FOCAL_LENGTH)/pixarea;
        }
      }

      return DISTANCE/pixels.size();

  }

  public AutonomousPath detectPose(List<FieldVisionInput> fieldVisionInputParameter) {
        
      AutonomousPath autonomousPath;
      
      VisionPathName visionPathName = VisionPathName.CouldNotDeterminePath;
       
      // Just for refernce the formula to calculate the focal length
      //dblFocalLength = (dblPixels*dblDistance)/dblWidth;      
      
      double averageDistance = computeAverageDistance(fieldVisionInputParameter);
         
        for(DistanceRange dr:distanceRanges){

          if (dr.isInRange(averageDistance)){
            visionPathName = dr.getPath();
          }
          
        }
      
      autonomousPath = new AutonomousPath(visionPathName);

      return autonomousPath;

    }

 }