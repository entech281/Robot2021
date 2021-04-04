package frc.robot.utils;

public class TurretCalculations {
  
    public static double turretEncoderPosition(double distance, double lateralOffset){
        double percent = (Math.PI/2 - Math.atan(lateralOffset/distance))/(2*Math.PI);
        double encoderClicks = (percent)*2100*4 - 300;
        encoderClicks = Math.max(encoderClicks, 1100);
        return encoderClicks;
    }
}