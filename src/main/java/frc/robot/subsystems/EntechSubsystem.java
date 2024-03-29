/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

// import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.logger.DataLogger;
import frc.robot.logger.DataLoggerFactory;

/**
 * Add your docs here.
 */
public abstract class EntechSubsystem extends SubsystemBase {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    protected DataLogger logger;

    public EntechSubsystem() {
        this.logger = DataLoggerFactory.getLoggerFactory().createDataLogger(this.getName());
        // done by Subsystembase:
        // CommandScheduler.getInstance().registerSubsystem(this);
    }

    public abstract void initialize();


}
