/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.utils;

/**
 *
 * @author aryan
 * @modifiedby rohit
 */
public class ShooterCalculations {
    public static final double SLOPE = 4.722222222;
    public static final double OFFSET = 3933.333333;
    public static int calculateAutoShooterSpeed(double distance){
        int desiredSpeed = (int)Math.max(SLOPE*distance + OFFSET, 4500);
        desiredSpeed = Math.min(desiredSpeed, 5400);
        return desiredSpeed;
    }
    public static double hoodEncoderPosition(double distance){
        double targetHeight = 86;
        double percent = (Math.PI/2 - Math.atan(targetHeight/distance))/(2*Math.PI);
        double encoderClicks = (percent)*2100*4 - 300;
        encoderClicks = Math.max(encoderClicks, 1100);
        return encoderClicks;
    }

    /*When it can see the target*/
    public static double turretEncoderPosition(double distance, double lateralOffset){
        double percent = (Math.PI/2 - Math.atan(lateralOffset/distance))/(2*Math.PI);
        double encoderClicks = (percent)*2100*4 - 300;
        encoderClicks = Math.max(encoderClicks, 1100);
        return encoderClicks;
    }
}
