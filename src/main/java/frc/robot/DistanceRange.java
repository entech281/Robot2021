package frc.robot;

public class DistanceRange implements DistanceRangeInterface{

  private double lowerbound = 0;
  private double upperbound = 0;
  private VisionPathName path;
  
  public DistanceRange ( double lowerbound, double upperbound, VisionPathName path ){
    
    this.lowerbound = lowerbound;
    this.upperbound= upperbound;
    this.path = path;  
  
  }
  
  public VisionPathName isInRange(double value ){

    if (( value > lowerbound ) && (value < upperbound )){
      return this.path;
    }
    else{
      return VisionPathName.CouldNotDeterminePath;
    }

  }

  
}