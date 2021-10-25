// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;

public class IntakeBallPickupCommand extends EntechCommandBase {
    private Timer m_timer = new Timer();
    private IntakeSubsystem m_intake;
    private ElevatorSubsystem m_elevator;
    private double DELAY1 = 0.3;
    private double DELAY2 = 0.1;

    /** Creates a new IntakeBallPickupCommand. */
    public IntakeBallPickupCommand(IntakeSubsystem intake, ElevatorSubsystem elevator) {
        super(intake,elevator);
        m_intake = intake;
        m_elevator = elevator;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_timer.stop();
        m_timer.reset();
        m_timer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (m_timer.get() <= DELAY1) {
            m_intake.setIntakeMotorSpeed(0.4);
            m_elevator.setElevatorSpeed(0.3);
        } else if (m_timer.get() <= (DELAY1+DELAY2)) {
            m_intake.setIntakeMotorSpeed(0.0);
            m_elevator.setElevatorSpeed(0.5);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_intake.setIntakeMotorSpeed(0.0);
        m_elevator.setElevatorSpeed(0.0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return (m_timer.get() > (DELAY1+DELAY2));
    }
}
