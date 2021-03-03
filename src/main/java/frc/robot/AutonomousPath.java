package frc.robot;

public class AutonomousPath{

   

    private VisionPathName pathName; // I am thinking PathRedA,PathBlueA,PathRedB,PathBlueB
    // I can add anything else we will need in the output here

    public VisionPathName getPathName() {
      return pathName;
    }

    public void setPathName(VisionPathName pathName) {
      this.pathName = pathName;
    }


}