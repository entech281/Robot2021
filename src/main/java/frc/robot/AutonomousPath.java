package frc.robot;

public class AutonomousPath{

    // enum
    public enum VisionPathName{
        PathRedA, 
        PathBlueA, 
        PathRedB, 
        PathBlueB,
        CouldNotDeterminePath
      }

    public VisionPathName pathName; // I am thinking PathRedA,PathBlueA,PathRedB,PathBlueB
    // I can add anything else we will need in the output here


}