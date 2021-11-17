// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ElevatorSubsystem.ElevatorMode;

public class ElevatorDownCommand extends EntechCommandBase {
    private ElevatorSubsystem m_elevator;

    /** Creates a new IntakeBallPickupCommand. */
    public ElevatorDownCommand(ElevatorSubsystem elevator) {
        super(elevator);
        m_elevator = elevator;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_elevator.setMode(ElevatorMode.down);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }

    // Returns true if command runs when disabled
    @Override
    public boolean runsWhenDisabled() {
        return true;
    }
}
