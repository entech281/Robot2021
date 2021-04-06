package frc.robot.subsystems;

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
import frc.robot.commands.AdjustHoodBackwardCommand;
import frc.robot.commands.AdjustRaiseHoodCommand;
import frc.robot.commands.AdjustTurretLeftCommand;
import frc.robot.commands.SpinTurretSpeedCommand;
import frc.robot.commands.AutoHoodShooterAdjust;
import frc.robot.commands.AutoTurretAdjust;
import frc.robot.commands.DriveDistancePIDCommand;
import frc.robot.commands.DriveToPositionCommand;
import frc.robot.commands.SnapToVisionTargetCommand;
import frc.robot.commands.SnapToYawCommand;
import frc.robot.commands.ToggleBrakeModeCommand;
import frc.robot.commands.ToggleCurvatureDriveCommand;
import frc.robot.commands.StartDriveLoggingCommand;
import frc.robot.commands.EndDriveLoggingCommand;
import frc.robot.commands.StartDriveReplayCommand;
import frc.robot.pose.PoseSource;
import java.util.function.BooleanSupplier;
import frc.robot.path.Position;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

    public Command autonomousSlalomPathCommand() {
        return new SequentialCommandGroup(
            driveForward(2*30.0).withTimeout(5.0),
            turnToDirection(90.0).withTimeout(5.0),
            driveForward(2*30.0).withTimeout(5.0),
            turnToDirection(0.0).withTimeout(5.0),
            driveForwardSpeedMode(6*30.0,0.25).withTimeout(10.0),
            turnToDirection(-90.0).withTimeout(5.0),
            driveForward(2*30.0).withTimeout(5.0),
            turnToDirection(0.0).withTimeout(5.0),
            driveForward(2*30.0).withTimeout(5.0),
            turnToDirection(90.0).withTimeout(5.0),
            driveForward(2*30.0).withTimeout(5.0),
            turnToDirection(180.0).withTimeout(5.0),
            driveForward(2*30.0).withTimeout(5.0),
            turnToDirection(-90.0).withTimeout(5.0),
            driveForward(2*30.0).withTimeout(5.0),
            turnToDirection(180.0).withTimeout(5.0),
            driveForwardSpeedMode(6*30.0,0.25).withTimeout(10.0),
            turnToDirection(90.0).withTimeout(5.0),
            driveForward(2*30.0).withTimeout(5.0),
            turnToDirection(180.0).withTimeout(5.0),
            driveForward(2*30.0).withTimeout(5.0)
            );
    }

    public Command toggleIntakeArms(){
        return new SequentialCommandGroup(
            new InstantCommand(() -> sm.getIntakeSubsystem().toggleIntakeArms()),
            new ParallelRaceGroup(
                new PerpetualCommand(new InstantCommand(() -> sm.getIntakeSubsystem().updateIntakeSolenoidPosition())),
                new WaitCommand(0.25)
                )
            ).andThen(new PrintCommand("Toggling Arms"));
    }

    public Command deployIntakeArms(){
        return new InstantCommand( sm.getIntakeSubsystem()::deployIntakeArms, sm.getIntakeSubsystem())
                .andThen(new PrintCommand("Deploying Arms"));
    }

    public Command raiseIntakeArms(){
        return new InstantCommand( sm.getIntakeSubsystem()::raiseIntakeArms, sm.getIntakeSubsystem())
                .andThen(new PrintCommand("Raising Arms"));
    }

    public Command snapToTargetVision(){
        return new SnapToVisionTargetCommand(sm.getTurretSubsystem(), sm);
    }

    public Command autoTurretAdjust() {
        return new AutoTurretAdjust(sm.getTurretSubsystem(), sm);
    }

    public Command spinIntake(){
        return new InstantCommand ( sm.getIntakeSubsystem()::intakeOn, sm.getIntakeSubsystem());
    }

    public Command intake3Balls(){
        return new SequentialCommandGroup(
                deployIntakeArms(),
                intakeOnCommand(),
                intakeOnCommand(),
                intakeOnCommand()
        ).withTimeout(6);
    }

    public Command deployAndStartIntake(){
        return new SequentialCommandGroup(deployIntakeArms(), intakeOnCommand());
    }

    public Command raiseAndStopIntake(){
        return new SequentialCommandGroup(raiseIntakeArms(), stopIntake());
    }

    public Command stopSpinningIntake(){
        return new InstantCommand ( sm.getIntakeSubsystem()::intakeOff, sm.getIntakeSubsystem());
    }

    public Command zeroYawOfNavX(boolean inverted){
        return new InstantCommand ( () -> sm.getNavXSubsystem().zeroYawMethod(inverted));
    }
    public Command middleSixBallAuto(){
        return new SequentialCommandGroup(
                new ParallelCommandGroup(
                        zeroYawOfNavX(false),
                        hoodStartingLinePreset(),
                        startShooter()
                ),
                fireCommand().withTimeout(1),
                new WaitCommand(1.5),
                new ParallelCommandGroup(
                        driveForwardSpeedMode(-60, 1),
                        stopElevator(),
                        stopShooter()
                ),
                turnToDirection(90),
                driveForwardSpeedMode(65, 1), //
                new ParallelCommandGroup(
                    intake3Balls(),
                    new SequentialCommandGroup(
                        turnToDirection(180).withTimeout(2),
                        new ParallelCommandGroup(
                                startShooter(),
                                driveForwardSpeedMode(100, 0.5), //
                                hoodTrenchPreset().withTimeout(0.01)
                        )
                    )
                ),
                raiseAndStopIntake().withTimeout(0.01),
                turnRight(160),
                new ParallelCommandGroup(
                    fireCommand(),
                    stopDriving()
                ),
                new WaitCommand(2.5),
                new ParallelCommandGroup(
                        stopElevator(),
                        stopShooter()
                )
        );
    }

    public Command leftEightBallAuto(){
        throw new UnsupportedOperationException("Not yet Implemented");
    }

    public Command doNothing(){
        return new PrintCommand("Doing Nothing Skipper!");
    }
    //66.91 inches
    public Command simpleForwardShoot3Auto(){
        return new SequentialCommandGroup(
                zeroYawOfNavX(false),
                new ParallelCommandGroup(
                        driveForwardSpeedMode(126.0, 0.75).withTimeout(5),
                        startShooter(),
                        hoodUpAgainstTargetPreset()
                ),
                fireCommand(),
                new WaitCommand(3),
                new ParallelCommandGroup(
                        stopElevator(),
                        stopShooter())
        );
    }

    public Command driveForward(double inches){
        return new DriveToPositionCommand(sm.getDriveSubsystem(), inches);
    }

    public Command driveForward(Position position){
        return new DriveToPositionCommand(sm.getDriveSubsystem(), position);
    }


    public Command driveForwardSpeedMode(double distance){
        return new DriveDistancePIDCommand(sm.getDriveSubsystem(), distance);
    }

    public Command driveForwardSpeedMode(double distance, double speed){
        return new DriveDistancePIDCommand(sm.getDriveSubsystem(), distance, speed);
    }


    public Command turnRight(double degrees){
        return new SnapToYawCommand(sm.getDriveSubsystem(), degrees, true, sm);
    }

    public Command turnLeft(double degrees){
        return new SnapToYawCommand(sm.getDriveSubsystem(), -degrees, true, sm);
    }

    public Command turnToDirection(double degrees){
        return new SnapToYawCommand(sm.getDriveSubsystem(), degrees, false, sm);
    }

    public Command stopDriving(){
        return new InstantCommand(() -> sm.getDriveSubsystem().stopDriving(), sm.getDriveSubsystem());
    }

    public Command snapAndShootCommand(){
        return snapToVisionTargetCommand()
                .alongWith(fireCommand());
    }
    public Command snapToVisionTargetCommand(){
        return new SnapToVisionTargetCommand(sm.getTurretSubsystem(),sm);
    }
    public Command snapToYawCommand(double desiredAngle, boolean relative){
        return new SnapToYawCommand(sm.getDriveSubsystem(),  desiredAngle,  relative, sm );
    }


    public Command hoodAdjustToAngleCommand(double angle){
        return new InstantCommand ( () -> sm.getHoodSubsystem().setHoodPosition(angle) , sm.getHoodSubsystem());
    }

    public Command toggleBrakeModeCommand(){
        return new ToggleBrakeModeCommand(sm.getDriveSubsystem());
    }

    public Command toggleCurvatureDriveCommand(){
        return new ToggleCurvatureDriveCommand(sm.getDriveSubsystem());
    }
    public Command startDriveLogging() {
        return new StartDriveLoggingCommand(sm.getDriveSubsystem());
    }
    public Command endDriveLogging() {
        return new EndDriveLoggingCommand(sm.getDriveSubsystem());
    }
    public Command startDriveReplay() {
        return new StartDriveReplayCommand(sm.getDriveSubsystem());
    }

    public Command intakeOnCommand(){

        double DELAY1 = SmartDashboard.getNumber("delay1", 0.25);
        double DELAY2 = SmartDashboard.getNumber("delay2", 0.05);
        return new SequentialCommandGroup(
            setIntakeSpeed(1),
            setElevatorSpeed(0),
            new WaitUntilCommand ( sm.getIntakeSubsystem()::isBallAtIntake),
            setIntakeSpeed(0.4),
            setElevatorSpeed(0.3),
            new WaitCommand( DELAY1),
            setIntakeSpeed(0.0),
            setElevatorSpeed(0.5),
            new WaitCommand(DELAY2)
        );
    }

//     public Command fireCommand(){
//         return new SequentialCommandGroup(
//             new WaitUntilCommand ( () ->
//                     sm.getShooterSubsystem().atShootSpeed() &&
//                     sm.getHoodSubsystem().atHoodPosition() ),
// //            snapToTargetVision(),
//             new InstantCommand(() -> sm.getIntakeSubsystem().fireSequenceEnable(ELEVEATOR_SLOW_SPEED) )
//         );
//     }


    public Command fireCommand_Orig(){
        return new SequentialCommandGroup(
            new InstantCommand(() -> sm.getIntakeSubsystem().fire()),
            new WaitCommand(0.5),
            new InstantCommand(() -> sm.getIntakeSubsystem().deactivate())

        );
    }

    public Command fireCommand(){
        return new SequentialCommandGroup(
            new PerpetualCommand(new InstantCommand(() -> sm.getIntakeSubsystem().fire())).withTimeout(0.5),
            new InstantCommand(() -> sm.getIntakeSubsystem().deactivate())
        );
    }

    public Command enableAutoShooterAndHood(){
        BooleanSupplier shooterOn = () -> sm.getShooterSubsystem().isShooterOn();
        return new ConditionalCommand(new AutoHoodShooterAdjust(sm.getShooterSubsystem(), sm.getHoodSubsystem(), sm), stopShooter(), shooterOn);
    }

    public Command disableAutoShooterAndHood(){
        BooleanSupplier shooterOn = () -> sm.getShooterSubsystem().isShooterOn();
        return new ConditionalCommand(startShooter(), stopShooter(), shooterOn);
    }

    public Command nudgeHoodForward(){
        return new AdjustRaiseHoodCommand(sm.getHoodSubsystem());
    }

    public Command nudgeTurretLeft() {
        return new SpinTurretSpeedCommand(sm.getTurretSubsystem(), 0.2);
    }

    public Command nudgeTurretRight() {
        return new SpinTurretSpeedCommand(sm.getTurretSubsystem(), -0.2);
    }

    public Command turretStop() {
        return new SpinTurretSpeedCommand(sm.getTurretSubsystem(), 0);
    }

    public Command hoodHomeCommand(){
        return new SequentialCommandGroup(
        new InstantCommand(() -> sm.getHoodSubsystem().goToHomePosition()),
        new WaitUntilCommand(() -> sm.getHoodSubsystem().isUpperLimitHit()),
        new InstantCommand(() -> sm.getHoodSubsystem().reset()),
        new InstantCommand(() -> sm.getHoodSubsystem().adjustHoodForward()),
        new WaitUntilCommand(() -> sm.getHoodSubsystem().atHoodPosition())
        );
    }

    public Command delay(double seconds){
        return new WaitCommand(seconds);
    }

    public Command nudgeHoodBackward(){
        return new AdjustHoodBackwardCommand(sm.getHoodSubsystem());
    }

    public Command startShooterNoShift(){
        return new InstantCommand(() ->  sm.getShooterSubsystem().startShooterPresetSpeed(), sm.getShooterSubsystem());
    }

    public Command hoodStartingLinePreset(){
        return new InstantCommand( () -> sm.getHoodSubsystem().startinfLinePreset(), sm.getHoodSubsystem());

    }

    public Command hoodTrenchPreset(){
        return new InstantCommand( () -> sm.getHoodSubsystem().trenchPreset(), sm.getHoodSubsystem());
    }

    public Command hoodUpAgainstTargetPreset(){
        return new InstantCommand(() -> sm.getHoodSubsystem().upAgainstTargetPreset(), sm.getHoodSubsystem());
    }

    public Command startShooter() {
        return shiftElevatorBack()
                .andThen(new InstantCommand(() ->  sm.getShooterSubsystem().startShooterPresetSpeed(), sm.getShooterSubsystem()
            ));
    }
    public Command stopShooter() {
        return new InstantCommand(
            () ->  sm.getShooterSubsystem().stopShooter(), sm.getShooterSubsystem()
        ).alongWith(parkHood());
    }

    public Command parkHood(){
        return new InstantCommand(()-> sm.getHoodSubsystem().park(), sm.getHoodSubsystem());
    }

    public Command setElevatorSpeed(double desiredSpeed) {
        return new InstantCommand(
            () ->    sm.getIntakeSubsystem().setElevatorSpeed(desiredSpeed), sm.getIntakeSubsystem()
        ).withTimeout(TINY_TIMEOUT_SECONDS);
    }

    public Command setIntakeSpeed( double desiredSpeed){
        return new InstantCommand(
            () ->    sm.getIntakeSubsystem().setIntakeMotorSpeed(desiredSpeed), sm.getIntakeSubsystem()
        ).withTimeout(TINY_TIMEOUT_SECONDS);
    }

    public Command stopIntake() {
        return setIntakeSpeed(STOP_SPEED);
    }
    public Command stopElevator(){
        return setElevatorSpeed(STOP_SPEED);
    }
    public Command stopIntakeAndElevator(){
        return new SequentialCommandGroup ( stopIntake(), stopElevator() );
    }

    public Command reverse() {
        return new SequentialCommandGroup(
            new InstantCommand ( () ->    sm.getIntakeSubsystem().setElevatorSpeed(FULL_SPEED_BWD)  ),
            new InstantCommand ( () ->    sm.getIntakeSubsystem().setIntakeMotorSpeed(FULL_SPEED_BWD)  )
        ).withTimeout(TINY_TIMEOUT_SECONDS);
    }

    public Command stopEverything() {
        return new SequentialCommandGroup(
            new InstantCommand ( () ->    sm.getIntakeSubsystem().setElevatorSpeed(STOP_SPEED)  ),
            new InstantCommand ( () ->    sm.getIntakeSubsystem().setIntakeMotorSpeed(STOP_SPEED)  )
        ).withTimeout(TINY_TIMEOUT_SECONDS);
    }

    public Command shiftElevatorBack() {
        return new SequentialCommandGroup(
            new InstantCommand ( () ->    sm.getIntakeSubsystem().setElevatorSpeed(-0.2)  ),
            new WaitCommand( 0.5 ),
            new InstantCommand ( () ->    sm.getIntakeSubsystem().setElevatorSpeed(0)  )
        ).withTimeout(1.0);
    }
}
