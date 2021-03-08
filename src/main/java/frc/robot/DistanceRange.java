package frc.robot;



public class DistanceRange implements DistanceRangeInterface{

  

  public DistanceRange ( double lowerbound, double upperbound, VisionPathName path ){
  
    
  
  }
  
  public VisionPathName isInRange(double value ){
        // Will return the value based on the range it falls in
        // since I'am trying to eleminate as much code as i can
        // i am returning CouldNotDeterminePath 
         return VisionPathName.CouldNotDeterminePath;
      
  }
}