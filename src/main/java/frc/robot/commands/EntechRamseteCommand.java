package frc.robot.commands;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import frc.robot.RobotConstants;
import frc.robot.pose.PoseSource;
import frc.robot.subsystems.DriveSubsystem;

public class EntechRamseteCommand extends EntechCommandBase{

    private DriveSubsystem drive;
    private PoseSource poseSource;
    private DifferentialDriveVoltageConstraint autoVoltageConstraint;
    private TrajectoryConfig config;
    private Trajectory trajectory = new Trajectory();
    private RamseteCommand ramsete;


    public EntechRamseteCommand(DriveSubsystem drive, PoseSource poseSource){
        super(drive);
        this.drive = drive;
        this.poseSource = poseSource;
        autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
                                    new SimpleMotorFeedforward(RobotConstants.CHARACTERIZATION.ksVolts,
                                    RobotConstants.CHARACTERIZATION.kvVoltSecondsPerMeter,
                                    RobotConstants.CHARACTERIZATION.kaVoltSecondsSquaredPerMeter),
                                    RobotConstants.CHARACTERIZATION.kDriveKinematics,
                                12);
        config = new TrajectoryConfig(RobotConstants.CHARACTERIZATION.kMaxSpeedMetersPerSecond,
                                      RobotConstants.CHARACTERIZATION.kMaxAccelerationMetersPerSecondSquared)
                                        // Add kinematics to ensure max speed is actually obeyed
                                        .setKinematics(RobotConstants.CHARACTERIZATION.kDriveKinematics)
                                        // Apply the voltage constraint
                                        .addConstraint(autoVoltageConstraint);
        String trajectoryJSON = "paths/Slalom.wpilib.json";
        try {
          Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
          trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        } catch (IOException ex) {
          DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
        }
                        
    }

    @Override
    public void execute() {

    }

    @Override
    public void initialize() {
      ramsete =  new RamseteCommand(
        trajectory,
          poseSource::getPose,
          new RamseteController(RobotConstants.CHARACTERIZATION.kRamseteB, RobotConstants.CHARACTERIZATION.kRamseteZeta),
          new SimpleMotorFeedforward(RobotConstants.CHARACTERIZATION.ksVolts,
          RobotConstants.CHARACTERIZATION.kvVoltSecondsPerMeter,
          RobotConstants.CHARACTERIZATION.kaVoltSecondsSquaredPerMeter),
          RobotConstants.CHARACTERIZATION.kDriveKinematics,
          drive::getWheelSpeeds,
          new PIDController(RobotConstants.CHARACTERIZATION.kPDriveVel, 0, 0),
          new PIDController(RobotConstants.CHARACTERIZATION.kPDriveVel, 0, 0),
          // RamseteCommand passes volts to the callback
          drive::tankDriveVolts,
          drive
      );
      poseSource.resetOdometry(trajectory.getInitialPose());
      ramsete.andThen(() -> drive.tankDriveVolts(0, 0));
    }
    
    
}
