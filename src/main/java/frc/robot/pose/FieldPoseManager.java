package frc.robot.pose;

import frc.pathrecognizer.AutonomousPath;

public class FieldPoseManager {

    private ColorWheel colorWheel = new ColorWheel();
    private FieldPose pose = new FieldPose(colorWheel);

    public FieldPose getCurrentPose() {
        return pose;
    }

    public void setCurrentColorWheel(ColorWheel wheelColor) {
        this.colorWheel = wheelColor;
    }

    public void setCurrentVisionFieldPath(AutonomousPath autonomousPathParameter){
        pose.setAutonomousPath(autonomousPathParameter);
    }

    public String getCurrentVisionFieldPath(){
        return pose.getAutonomousPath().getPathName().toString();
    }

}
