package frc.pathrecogonizer;

public class AutonomousPath{   

    private VisionPathName pathName; 

    public AutonomousPath(VisionPathName pathName) {
      this.pathName = pathName;
    }

    public VisionPathName getPathName() {
      return pathName;
    }


}