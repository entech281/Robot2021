package frc.pathrecognizer;

import java.util.List;
import java.util.ArrayList;

 public class VisionFieldLayoutRecognizer
    implements PixyFieldPoseResolverInterface{


    //Cannot instantiate the type List if I say new List<DistanceRange>, I get this error
    private List<DistanceRange> distanceRanges;
    public static final double FOCAL_LENGTH = 11.33;
    public static final double WIDTH = 7; //inches
     
    
    public VisionFieldLayoutRecognizer() {
      setupInitialDistanceRanges();
    }

    public void setupInitialDistanceRanges(){
        
      this.distanceRanges = List.of(new DistanceRange(8.50, 10.0, VisionPathName.PathRedA),
      new DistanceRange(16.10, 16.30, VisionPathName.PathRedB),
      new DistanceRange(10.30, 10.40, VisionPathName.PathBlueA),
      new DistanceRange(17.50, 17.80, VisionPathName.PathBlueB)
      );

  }
  private double computeAverageDistance(List<FieldVisionInput> fieldVisionInputParameter){
    
    double DISTANCE = 0;
    List<Double> pixels = new ArrayList<>();
    
          for(FieldVisionInput fieldVisionInput: fieldVisionInputParameter){

          pixels.add(fieldVisionInput.getObjectHeight() * 
                                  fieldVisionInput.getObjectWidth());
                            
        }
      
        for (Double pixarea: pixels){
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
            break;
          }
          
        }
        
      autonomousPath = new AutonomousPath(visionPathName);

      return autonomousPath;

    }

 }