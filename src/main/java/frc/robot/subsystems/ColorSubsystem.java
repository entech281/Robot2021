package frc.robot.subsystems;

import frc.robot.pose.WheelColorValue;

public class ColorSubsystem extends EntechSubsystem {

    public ColorSubsystem() {
    }

    @Override
    public void initialize() {

    }

    public WheelColorValue getRobotColorSensorReading() {
        return WheelColorValue.BLUE;
//        throw new UnsupportedOperationException("Not Implemented Yet");
    }

}
