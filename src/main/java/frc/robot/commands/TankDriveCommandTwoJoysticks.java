package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.DriveSubsystem;

public class TankDriveCommandTwoJoysticks extends EntechCommandBase {

    
    private DriveSubsystem drive;
    private Joystick driveStick1;
    private Joystick driveStick2;
    public TankDriveCommandTwoJoysticks(DriveSubsystem drive, Joystick driveStick1, Joystick driveStick2) {
        super(drive);
        this.driveStick1 = driveStick1;
        this.driveStick2 = driveStick2;
        this.drive = drive;
    }

    @Override
    public void initialize(){
    }
    
    @Override
    public void execute() {
        //drive.drive(-driveStick.getY(), driveStick.getX());
        drive.doubleTankDrive(-driveStick2.getY(), -driveStick1.getY());
    }
    
    @Override
    public boolean isFinished(){
        return false;
    }
}
