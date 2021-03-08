package frc.robot;

import java.util.ArrayList;
import java.util.HashMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



 public class VisionFieldLayoutRecognizer
    implements PixyFieldPoseResolver{

    private ArrayList<Double> distanceRanges;
    
    public VisionFieldLayoutRecognizer() {
      DistanceRange();
    }

    public void DistanceRange(){
        
      
  }

  private VisionPathName getVisionPathNameByIndex(int index){

    return VisionPathName.CouldNotDeterminePath;
  }

  public AutonomousPath detectPose(ArrayList<FieldVisionInput> arraylistFieldVisionInput) {
        
    return null;
  }
 }