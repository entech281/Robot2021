package frc.robot.pose;

import frc.pathrecognizer.AutonomousPath;

public class FieldPose {

    private ColorWheel colorWheel = new ColorWheel();
    private AutonomousPath autonomousPath;

    public FieldPose(ColorWheel colorWheel) {
        this.colorWheel = colorWheel;
    }

    public ColorWheel getColorWheel() {
        return colorWheel;
    }

    public AutonomousPath getAutonomousPath() {
        return autonomousPath;
    }

    public void setAutonomousPath(AutonomousPath autonomousPath) {
        this.autonomousPath = autonomousPath;
    }


}
