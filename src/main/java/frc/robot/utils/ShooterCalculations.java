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
        encoderClicks = Math.min(encoderClicks, 1100);
        // 963 for long distance (over 112)
        // 323 at zero
        if (distance > 112.) {
            return 963;
        }
        encoderClicks = 963. - (1.0 - distance/112.) * (963. - 323.);
        return encoderClicks;
    }

}

