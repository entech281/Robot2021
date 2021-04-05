/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;

/**
 *
 * @author aryan
 */
public class ToggleBrakeModeCommand extends EntechCommandBase{
    private DriveSubsystem drive;

    public ToggleBrakeModeCommand(DriveSubsystem drive){
        super(drive);
        this.drive = drive;
    }
    
    @Override
    public void initialize() {
    }
    
    @Override
    public void execute() {
        drive.toggleBrakeMode();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
