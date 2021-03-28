package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.DriveSubsystem;

public class TankDriveCurvatureCommand extends EntechCommandBase {

    private DriveSubsystem drive;
    private Joystick driveStick;
    private JoystickButton tightButton;
    public TankDriveCurvatureCommand(DriveSubsystem drive, Joystick driveStick, JoystickButton tightButton) {
        super(drive);
        this.driveStick = driveStick;
        this.drive = drive;
        this.tightButton = tightButton;
    }

    @Override
    public void initialize(){
        drive.switchToBrakeMode();
    }
    
    @Override
    public void execute() {
        //drive.drive(-driveStick.getY(), driveStick.getX());
        double forward = driveStick.getY();
        if(forward < 0){
            forward = -Math.pow(forward, 2);
        } else {
            forward = Math.pow(forward, 2);
        }
        
        if(tightButton.get()){
            drive.drive(0.0, driveStick.getTwist());
        } else {
            drive.curveDrive(forward, driveStick.getX(), false);
        }
    }
    
    @Override
    public boolean isFinished(){
        return false;
    }
}
