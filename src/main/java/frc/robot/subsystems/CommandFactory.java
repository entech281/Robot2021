package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.PerpetualCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.PerpetualCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.RobotConstants;
import frc.robot.RobotConstants.AUTONOMOUS;
import frc.robot.commands.AdjustHoodBackwardCommand;
import frc.robot.commands.AdjustRaiseHoodCommand;
import frc.robot.commands.AutoHoodShooterAdjust;
import frc.robot.pose.PoseSource;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.BooleanSupplier;


/**
 *
 * @author dcowden
 */
public class CommandFactory {
    
    public static final int DEFAULT_TIMEOUT_SECONDS = 1;
    public static final double TINY_TIMEOUT_SECONDS=0.4;
    public static final double ELEVEATOR_SLOW_SPEED = 0.7;
    
    public static final double FULL_SPEED_FWD = 1;
    public static final double HALF_SPEED_FWD = 0.5;
    public static final double FULL_SPEED_BWD = -0.5;
    public static final double STOP_SPEED = 0;
    public static final double ELEVATOR_INTAKE_SPEED = 0.3;
    public static final double INTAKE_REVERSE = -0.2;
    
    private final SubsystemManager sm;
    public CommandFactory(SubsystemManager subsystemManager){
        this.sm = subsystemManager;
    }
    
    // public Command toggleIntakeArms(){
    //     return new SequentialCommandGroup(
    //         new InstantCommand(() -> sm.getIntakeSubsystem().toggleIntakeArms()),
    //         new ParallelRaceGroup(
    //             new PerpetualCommand(new InstantCommand(() -> sm.getIntakeSubsystem().updateSolenoidPosition())),
    //             new WaitCommand(0.25)
    //             )
    //         ).andThen(new PrintCommand("Toggling Arms"));
    // }

    // public Command deployIntakeArms(){
    //     return new InstantCommand( sm.getIntakeSubsystem()::deployIntakeArms, sm.getIntakeSubsystem())
    //             .andThen(new PrintCommand("Deploying Arms"));
    // }


    public Command getAutonomousCommand() {

        // Create a voltage constraint to ensure we don't accelerate too fast
        var autoVoltageConstraint = RobotConstants.AUTONOMOUS.autoVoltageConstraint;
    
        // Create config for trajectory
        TrajectoryConfig config = RobotConstants.AUTONOMOUS.config;
    
        String trajectoryJSON = "paths/Slalom.wpilib.json";
        // Trajectory trajectory = new Trajectory();
        // try {
        //   Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
        //   trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        // } catch (IOException ex) {
        //   DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
        // }

        Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
        ),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(3, 0, new Rotation2d(0)),
        // Pass config
        config
    );


        RamseteCommand ramsete =  new RamseteCommand(
            trajectory,
              sm.getDriveSubsystem()::getPose,
              new RamseteController(RobotConstants.CHARACTERIZATION.kRamseteB, RobotConstants.CHARACTERIZATION.kRamseteZeta),
              new SimpleMotorFeedforward(RobotConstants.CHARACTERIZATION.ksVolts,
              RobotConstants.CHARACTERIZATION.kvVoltSecondsPerMeter,
              RobotConstants.CHARACTERIZATION.kaVoltSecondsSquaredPerMeter),
              RobotConstants.CHARACTERIZATION.kDriveKinematics,
              sm.getDriveSubsystem()::getWheelSpeeds,
              new PIDController(RobotConstants.CHARACTERIZATION.kPDriveVel, 0, 0),
              new PIDController(RobotConstants.CHARACTERIZATION.kPDriveVel, 0, 0),
              // RamseteCommand passes volts to the callback
              sm.getDriveSubsystem()::tankDriveVolts,
              sm.getDriveSubsystem()
          );
    
    
        // Reset odometry to the starting pose of the trajectory.
        sm.getDriveSubsystem().resetOdometry(trajectory.getInitialPose());
    
        // Run path following command, then stop at the end.
        return ramsete.andThen(() -> sm.getDriveSubsystem().tankDriveVolts(0, 0));
      }
    

    // public Command raiseIntakeArms(){
    //     return new InstantCommand( sm.getIntakeSubsystem()::raiseIntakeArms, sm.getIntakeSubsystem())
    //             .andThen(new PrintCommand("Raising Arms"));
    // }
        
    // public Command spinIntake(){
    //     return new InstantCommand ( sm.getIntakeSubsystem()::intakeOn, sm.getIntakeSubsystem());
    // }
    
    // public Command intake3Balls(){
    //     return new SequentialCommandGroup(
    //             deployIntakeArms(),
    //             intakeOnCommand(),
    //             intakeOnCommand(),
    //             intakeOnCommand()
    //     ).withTimeout(6);
    // }
    
    // public Command deployAndStartIntake(){
    //     return new SequentialCommandGroup(deployIntakeArms(), intakeOnCommand());
    // }

    // public Command raiseAndStopIntake(){
    //     return new SequentialCommandGroup(raiseIntakeArms(), stopIntake());
    // }
    
    // public Command stopSpinningIntake(){
    //     return new InstantCommand ( sm.getIntakeSubsystem()::intakeOff, sm.getIntakeSubsystem());
    // }    
    
    public Command zeroYawOfNavX(boolean inverted){
        return new InstantCommand ( () -> sm.getNavXSubsystem().zeroYawMethod(inverted));
    }
    
    
    public Command stopDriving(){
        return new InstantCommand(() -> sm.getDriveSubsystem().tankDriveVolts(0, 0), sm.getDriveSubsystem());
    }
        
    // public Command snapAndShootCommand(){
    //     return fireCommand();
    // }
    
    
    // public Command hoodAdjustToAngleCommand(double angle){
    //     return new InstantCommand ( () -> sm.getHoodSubsystem().setHoodPosition(angle) , sm.getHoodSubsystem());
    // }
        
    // public Command intakeOnCommand(){
    //     double DELAY1 = 0.5;
    //     double DELAY2 = 0.15;    
    //     return new SequentialCommandGroup(
    //         setIntakeSpeed(1),
    //         setElevatorSpeed(0),
    //         new WaitUntilCommand ( sm.getIntakeSubsystem()::isBallAtIntake),
    //         setIntakeSpeed(0.4),
    //         setElevatorSpeed(0.3),
    //         new WaitCommand( DELAY1),
    //         setIntakeSpeed(0.0),
    //         setElevatorSpeed(0.5),  
    //         new WaitCommand(DELAY2)
    //     );
    // }

//     public Command fireCommand(){
//         return new SequentialCommandGroup(
//             new WaitUntilCommand ( () ->  
//                     sm.getShooterSubsystem().atShootSpeed() &&
//                     sm.getHoodSubsystem().atHoodPosition() ),
// //            snapToTargetVision(),
//             new InstantCommand(() -> sm.getIntakeSubsystem().setElevatorSpeed(ELEVEATOR_SLOW_SPEED) )        
//         );
//     }
    
    // public Command enableAutoShooterAndHood(){
    //     BooleanSupplier shooterOn = () -> sm.getShooterSubsystem().isShooterOn();
    //     return new ConditionalCommand(new AutoHoodShooterAdjust(sm.getShooterSubsystem(), sm.getHoodSubsystem(), sm), stopShooter(), shooterOn);
    // }
    
    // public Command disableAutoShooterAndHood(){
    //     BooleanSupplier shooterOn = () -> sm.getShooterSubsystem().isShooterOn();
    //     return new ConditionalCommand(startShooter(), stopShooter(), shooterOn);        
    // }
    
    // public Command nudgeHoodForward(){
    //     return new AdjustRaiseHoodCommand(sm.getHoodSubsystem());
    // }
    
    // public Command hoodHomeCommand(){
    //     return new SequentialCommandGroup(
    //     new InstantCommand(() -> sm.getHoodSubsystem().goToHomePosition()),
    //     new WaitUntilCommand(() -> sm.getHoodSubsystem().isUpperLimitHit()),
    //     new InstantCommand(() -> sm.getHoodSubsystem().reset()),
    //     new InstantCommand(() -> sm.getHoodSubsystem().adjustHoodForward()),
    //     new WaitUntilCommand(() -> sm.getHoodSubsystem().atHoodPosition())
    //     );                      
    // }
    
    public Command delay(double seconds){
        return new WaitCommand(seconds);
    }
    
    // public Command nudgeHoodBackward(){
    //     return new AdjustHoodBackwardCommand(sm.getHoodSubsystem());
    // }    

    // private Command startShooterNoShift(){
    //     return new InstantCommand(() ->  sm.getShooterSubsystem().startShooterPresetSpeed(), sm.getShooterSubsystem());
    // }
    
    // public Command hoodStartingLinePreset(){
    //     return new InstantCommand( () -> sm.getHoodSubsystem().startinfLinePreset(), sm.getHoodSubsystem());
        
    // }
    
    // public Command hoodTrenchPreset(){
    //     return new InstantCommand( () -> sm.getHoodSubsystem().trenchPreset(), sm.getHoodSubsystem());
    // }

    // public Command hoodUpAgainstTargetPreset(){
    //     return new InstantCommand(() -> sm.getHoodSubsystem().upAgainstTargetPreset(), sm.getHoodSubsystem());
    // }
    
    // public Command startShooter() {
    //     return shiftElevatorBack()
    //             .andThen(new InstantCommand(() ->  sm.getShooterSubsystem().startShooterPresetSpeed(), sm.getShooterSubsystem()
    //         ));
    // }
    // public Command stopShooter() {
    //     return new InstantCommand(
    //         () ->  sm.getShooterSubsystem().stopShooter(), sm.getShooterSubsystem()
    //     ).alongWith(parkHood());
    // }
    
    // public Command parkHood(){
    //     return new InstantCommand(()-> sm.getHoodSubsystem().park(), sm.getHoodSubsystem());
    // }

    // public Command setElevatorSpeed(double desiredSpeed) {
    //     return new InstantCommand(
    //         () ->    sm.getIntakeSubsystem().setElevatorSpeed(desiredSpeed), sm.getIntakeSubsystem()
    //     ).withTimeout(TINY_TIMEOUT_SECONDS);
    // } 
    
    // public Command setIntakeSpeed( double desiredSpeed){
    //     return new InstantCommand(
    //         () ->    sm.getIntakeSubsystem().setIntakeMotorSpeed(desiredSpeed), sm.getIntakeSubsystem()
    //     ).withTimeout(TINY_TIMEOUT_SECONDS);        
    // }
    
    // public Command stopIntake() {
    //     return setIntakeSpeed(STOP_SPEED);
    // }    
    // public Command stopElevator(){
    //     return setElevatorSpeed(STOP_SPEED);
    // }
    // public Command stopIntakeAndElevator(){
    //     return new SequentialCommandGroup ( stopIntake(), stopElevator() );
    // }

    // public Command reverse() {
    //     return new SequentialCommandGroup(
    //         new InstantCommand ( () ->    sm.getIntakeSubsystem().setElevatorSpeed(FULL_SPEED_BWD)  ),
    //         new InstantCommand ( () ->    sm.getIntakeSubsystem().setIntakeMotorSpeed(FULL_SPEED_BWD)  )
    //     ).withTimeout(TINY_TIMEOUT_SECONDS);  
    // }    

    // public Command stopEverything() {
    //     return new SequentialCommandGroup(
    //         new InstantCommand ( () ->    sm.getIntakeSubsystem().setElevatorSpeed(STOP_SPEED)  ),
    //         new InstantCommand ( () ->    sm.getIntakeSubsystem().setIntakeMotorSpeed(STOP_SPEED)  )
    //     ).withTimeout(TINY_TIMEOUT_SECONDS);
    // }         
     
    // public Command shiftElevatorBack() {
    //     return new SequentialCommandGroup(
    //         new InstantCommand ( () ->    sm.getIntakeSubsystem().setElevatorSpeed(-0.2)  ),
    //         new WaitCommand( 0.5 ),
    //         new InstantCommand ( () ->    sm.getIntakeSubsystem().setElevatorSpeed(0)  )
    //     ).withTimeout(1.0);  
    // }      
}
