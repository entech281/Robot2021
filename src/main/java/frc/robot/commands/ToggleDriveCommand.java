package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.DriveSubsystem;

public class ToggleDriveCommand extends EntechCommandBase {

    private DriveSubsystem drive;
    private Joystick driveStick;
    public ToggleDriveCommand(DriveSubsystem drive, Joystick driveStick) {
        super(drive);
        this.driveStick = driveStick;
        this.drive = drive;
    }

    @Override
    public void initialize(){
    }
    
    @Override
    public void execute() {
        drive.toggle_drive(-driveStick.getY(), driveStick.getX());        
    }
    
    @Override
    public boolean isFinished(){
        return false;
    }
}
